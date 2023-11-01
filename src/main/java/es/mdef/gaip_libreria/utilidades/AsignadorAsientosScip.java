package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.excepciones.SinSolucionException;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonasConfiguradasHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
import static es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad.LIBRE;
import static es.mdef.gaip_libreria.constantes.TipoDeZona.TRIBUNA;

/**
 * Clase utilitaria para gestionar la asignación de asientos a los invitados utilizando el AlgoritmoScip.
 */
final class AsignadorAsientosScip {

    /**
     * Constructor privado para evitar la instanciación.
     */
    private AsignadorAsientosScip() {
    }

    /**
     * Método principal para sentar a los invitados en el acto proporcionado.
     *
     * @param acto Acto en el que se van a sentar los invitados.
     */
    public static void sentarInvitados(Acto acto) {
        List<Anfitrion> anfitrionesOrdenados = new ArrayList<>(acto.getAnfitriones());
        anfitrionesOrdenados.sort(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA, acto));

        int[] invitadosPorAnfitrion;
        int[] capacidadAsientos;
        int[][] prioridades;

        invitadosPorAnfitrion = getInvitadosPorAnfitrion(acto);
        capacidadAsientos = getGruposDeAsientosParaDistribuir(acto);
        prioridades = getPrioridades(acto);

        AlgoritmoOrganizacionAsientos organizador = new AlgoritmoOrganizacionAsientos(invitadosPorAnfitrion, capacidadAsientos, prioridades);

        try {
            AlgoritmoOrganizacionAsientos.ResultadoOrganizacion resultado = organizador.organizar();

            List<Invitado> todosLosInvitados = getTodosLosInvitados(acto);
            List<LocalidadConfigurada> todasLasLocalidades = getTodasLasLocalidades(acto);
            resultado.asignacionInvitadoAsiento().forEach((indiceInvitado, indiceAsiento) ->
                    todosLosInvitados.get(indiceInvitado).setLocalidad(todasLasLocalidades.get(indiceAsiento), true));
        } catch (SinSolucionException e) {
            AsignadorAsientosSimple.sentarInvitados(acto);
        }
    }

    private static List<LocalidadConfigurada> getTodasLasLocalidades(Acto acto) {
        return obtenerTodosGruposAsientos(acto).stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Invitado> getTodosLosInvitados(Acto acto) {
        List<Anfitrion> anfitrionesOrdenados = getAnfitrionesOrdendos(acto);

        return anfitrionesOrdenados.stream()
                .flatMap(anfitrion -> anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, TRIBUNA).stream())
                .collect(Collectors.toList());
    }

    private static int[] getInvitadosPorAnfitrion(Acto acto) {
        return getAnfitrionesOrdendos(acto).stream()
                .mapToInt(anfitrion -> anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, TRIBUNA).size())
                .toArray();
    }

    private static List<Anfitrion> getAnfitrionesOrdendos(Acto acto) {
        return acto.getAnfitriones().stream().sorted(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA, acto)).collect(Collectors.toList());
    }

    private static int[] getGruposDeAsientosParaDistribuir(Acto acto) {
        List<Integer> capacidades = new ArrayList<>();

        for (ZonaConfigurada zonaConfigurada : acto.getZonasConfiguradasPorTipo(TRIBUNA)) {
            List<LocalidadConfigurada> localidadesOrdenadas = ZonasConfiguradasHelper.getLocalidadesOrdenadasPorNumero(zonaConfigurada);
            calcularCapacidadesPorLocalidades(localidadesOrdenadas, capacidades);
        }

        return capacidades.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void calcularCapacidadesPorLocalidades(List<LocalidadConfigurada> localidadesOrdenadas, List<Integer> capacidades) {
        List<LocalidadConfigurada> localidadesConsecutivas = new ArrayList<>();

        for (LocalidadConfigurada localidad : localidadesOrdenadas) {
            if (esAsientoNormalYLibre(localidad)) {
                localidadesConsecutivas.add(localidad);
            }

            if (debeTerminarGrupoAsientos(localidad, localidadesConsecutivas)) {
                capacidades.add(localidadesConsecutivas.size());
                localidadesConsecutivas.clear();
            }
        }
    }

    private static boolean esAsientoNormalYLibre(LocalidadConfigurada localidad) {
        return localidad.getEstadoLocalidad() == NORMAL && localidad.getEstadoOcupacionLocalidad() == LIBRE;
    }

    private static boolean debeTerminarGrupoAsientos(LocalidadConfigurada localidad, List<LocalidadConfigurada> localidadesConsecutivas) {
        return !localidadesConsecutivas.isEmpty() && (!esAsientoNormalYLibre(localidad) || localidad.getLocalidad().getImplicaSaltoFila() || localidad.getLocalidad().getImplicaSalto());
    }

    private static int[][] getPrioridades(Acto acto) {
        List<Anfitrion> anfitrionesOrdenados = getAnfitrionesOrdendos(acto);

        List<List<LocalidadConfigurada>> todosGruposAsientos = obtenerTodosGruposAsientos(acto);

        int[][] prioridades = new int[anfitrionesOrdenados.size()][todosGruposAsientos.size()];

        for (int i = 0; i < anfitrionesOrdenados.size(); i++) {
            Anfitrion anfitrion = anfitrionesOrdenados.get(i);
            String unidadAnfitrion = anfitrion.getUnidadDeFormacion();

            for (int j = 0; j < todosGruposAsientos.size(); j++) {
                List<LocalidadConfigurada> grupoAsientos = todosGruposAsientos.get(j);
                ZonaConfigurada zonaPertenece = grupoAsientos.get(0).getZonaConfigurada();
                prioridades[i][j] = obtenerPrioridadDeZonaParaUnidad(zonaPertenece, unidadAnfitrion);
            }
        }

        return prioridades;
    }

    private static List<List<LocalidadConfigurada>> obtenerTodosGruposAsientos(Acto acto) {
        List<List<LocalidadConfigurada>> todosGrupos = new ArrayList<>();

        for (ZonaConfigurada zonaConfigurada : acto.getZonasConfiguradasPorTipo(TRIBUNA)) {
            List<LocalidadConfigurada> localidadesOrdenadas = ZonasConfiguradasHelper.getLocalidadesOrdenadasPorNumero(zonaConfigurada);

            List<LocalidadConfigurada> grupoActual = new ArrayList<>();
            for (LocalidadConfigurada localidad : localidadesOrdenadas) {
                if (esAsientoNormalYLibre(localidad)) {
                    grupoActual.add(localidad);
                }
                if (debeTerminarGrupoAsientos(localidad, grupoActual) && !grupoActual.isEmpty()) {
                    todosGrupos.add(new ArrayList<>(grupoActual));
                    grupoActual.clear();
                }
            }
            if (!grupoActual.isEmpty()) {
                todosGrupos.add(grupoActual);
            }
        }

        return todosGrupos;
    }

    private static int obtenerPrioridadDeZonaParaUnidad(ZonaConfigurada zona, String unidad) {
        return zona.getPrioridades().stream()
                .filter(prioridad -> prioridad.getUnidad().equals(unidad))
                .findFirst()
                .map(prioridad -> {
                    int valor = prioridad.getPrioridad();
                    return valor == 0 ? Integer.MAX_VALUE : valor;
                })
                .orElse(Integer.MAX_VALUE);
    }
}