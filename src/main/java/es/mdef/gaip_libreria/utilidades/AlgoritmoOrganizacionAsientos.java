package es.mdef.gaip_libreria.utilidades;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import es.mdef.gaip_libreria.excepciones.SinSolucionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase responsable de organizar los asientos para invitados y anfitriones.
 */
public class AlgoritmoOrganizacionAsientos {
    private final int numAnfitriones;
    private final int[] invitadosPorAnfitrion;
    private final int numInvitados;
    private final int numGrupoDeAsientos;
    private final int[][] prioridades;
    private final int[] capacidadAsientos;
    private final int numAsientos;

    /**
     * Constructor de la clase.
     *
     * @param invitadosPorAnfitrion Número de invitados por anfitrión.
     * @param capacidadAsientos     Capacidad de asientos por grupo.
     * @param prioridades           Prioridades de asiento por anfitrión.
     */
    public AlgoritmoOrganizacionAsientos(int[] invitadosPorAnfitrion, int[] capacidadAsientos, int[][] prioridades) {
        this.prioridades = prioridades;
        this.invitadosPorAnfitrion = invitadosPorAnfitrion;
        this.capacidadAsientos = capacidadAsientos;

        this.numAnfitriones = invitadosPorAnfitrion.length;
        this.numGrupoDeAsientos = capacidadAsientos.length;
        this.numInvitados = sumArray(invitadosPorAnfitrion);
        this.numAsientos = sumArray(capacidadAsientos);
    }

    /**
     * Organiza los asientos de acuerdo a las prioridades y restricciones dadas.
     *
     * @return Resultado de la organización.
     * @throws SinSolucionException Si no se encuentra una solución óptima.
     */
    public ResultadoOrganizacion organizar() throws SinSolucionException {
        Loader.loadNativeLibraries();
        MPSolver solver = MPSolver.createSolver("SCIP");

        int[][] prioridadesInvitados = convertirPrioridadesPorAnfitrion();
        MPVariable[][] x = inicializarVariables(solver);

        agregarRestricciones(solver, x);
        definirFuncionObjetivo(solver, x, prioridadesInvitados);

        return resolver(solver, x);
    }

    private int[][] convertirPrioridadesPorAnfitrion() {
        int[][] prioridadesInvitados = new int[numInvitados][numAsientos];
        int invitadoIndex = 0;
        for (int a = 0; a < numAnfitriones; a++) {
            for (int i = 0; i < invitadosPorAnfitrion[a]; i++) {
                int asientoIndex = 0;
                for (int g = 0; g < numGrupoDeAsientos; g++) {
                    Arrays.fill(prioridadesInvitados[invitadoIndex], asientoIndex, asientoIndex + capacidadAsientos[g], prioridades[a][g]);
                    asientoIndex += capacidadAsientos[g];
                }
                invitadoIndex++;
            }
        }
        return prioridadesInvitados;
    }

    private MPVariable[][] inicializarVariables(MPSolver solver) {
        MPVariable[][] x = new MPVariable[numInvitados][numAsientos];
        for (int i = 0; i < numInvitados; i++) {
            for (int j = 0; j < numAsientos; j++) {
                x[i][j] = solver.makeBoolVar("x_" + i + "_" + j);
            }
        }
        return x;
    }

    private void agregarRestricciones(MPSolver solver, MPVariable[][] x) {
        // Cada invitado DEBE ocupar un asiento
        for (int i = 0; i < numInvitados; i++) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < numAsientos; j++) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }

        // Cada asiento solo puede ser ocupado por un invitado
        for (int j = 0; j < numAsientos; j++) {
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            for (int i = 0; i < numInvitados; i++) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }

        // Restricción para que los invitados de un anfitrión no se sienten en las últimas posiciones que
        // impedirían que todos los invitados del anfitrión se sienten juntos en un grupo de asientos.
        for (int a = 0; a < numAnfitriones; a++) {
            for (int g = 0; g < numGrupoDeAsientos; g++) {
                int inicio = g == 0 ? 0 : Arrays.stream(capacidadAsientos).limit(g).sum();
                int fin = inicio + capacidadAsientos[g] - invitadosPorAnfitrion[a] + 1;

                fin = Math.max(fin, inicio);

                for (int j = fin; j < inicio + capacidadAsientos[g]; j++) {
                    MPConstraint constraint = solver.makeConstraint(0, 0, "");
                    constraint.setCoefficient(x[invitadoIndex(a, 0)][j], 1);
                }
            }
        }

        // Restricción para asientos contiguos
        for (int a = 0; a < numAnfitriones; a++) {
            for (int g = 0; g < numGrupoDeAsientos; g++) {
                int inicio = g == 0 ? 0 : Arrays.stream(capacidadAsientos).limit(g).sum();
                int fin = inicio + capacidadAsientos[g] - 1;

                for (int i = 0; i < invitadosPorAnfitrion[a] - 1; i++) {
                    int actualInvitadoIndex = invitadoIndex(a, i);
                    int siguienteInvitadoIndex = invitadoIndex(a, i + 1);

                    for (int j = inicio; j < fin; j++) {
                        MPConstraint constraint = solver.makeConstraint(0, 0, "");
                        constraint.setCoefficient(x[actualInvitadoIndex][j], 1);
                        constraint.setCoefficient(x[siguienteInvitadoIndex][j + 1], -1);
                    }
                }
            }
        }
    }

    private void definirFuncionObjetivo(MPSolver solver, MPVariable[][] x, int[][] prioridadesInvitados) {
        MPObjective objective = solver.objective();
        for (int i = 0; i < numInvitados; i++) {
            for (int j = 0; j < numAsientos; j++) {
                objective.setCoefficient(x[i][j], prioridadesInvitados[i][j]);
            }
        }
        objective.setMinimization();
    }

    private ResultadoOrganizacion resolver(MPSolver solver, MPVariable[][] x) throws SinSolucionException {
        MPSolver.ResultStatus status = solver.solve();
        Map<Integer, Integer> asignacionInvitadoAsiento = new HashMap<>();
        if (status == MPSolver.ResultStatus.OPTIMAL) {
            for (int i = 0; i < numInvitados; i++) {
                for (int j = 0; j < numAsientos; j++) {
                    if (x[i][j].solutionValue() == 1) {
                        asignacionInvitadoAsiento.put(i, j);
                    }
                }
            }
        } else {
            throw new SinSolucionException();
        }
        return new ResultadoOrganizacion(asignacionInvitadoAsiento);
    }

    private int invitadoIndex(int anfitrion, int invitado) {
        return sumArray(invitadosPorAnfitrion, anfitrion) + invitado;
    }

    private int sumArray(int[] array) {
        return Arrays.stream(array).sum();
    }

    private int sumArray(int[] array, int toIndex) {
        return Arrays.stream(array, 0, toIndex).sum();
    }

    /**
     * Clase que representa el resultado de la organización.
     */
    public record ResultadoOrganizacion(Map<Integer, Integer> asignacionInvitadoAsiento) {
    }
}