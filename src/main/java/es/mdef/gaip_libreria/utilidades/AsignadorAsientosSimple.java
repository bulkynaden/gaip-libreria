package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.invitados.Asignable;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.PrioridadPorUnidad;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
import static es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad.LIBRE;
import static es.mdef.gaip_libreria.constantes.TipoDeZona.TRIBUNA;

/**
 * Clase utilitaria para gestionar la asignación de asientos a los invitados.
 */
final class AsignadorAsientosSimple {

    /**
     * Constructor privado para evitar la instanciación.
     */
    private AsignadorAsientosSimple() {
    }

    /**
     * Método principal para sentar a los invitados en el acto proporcionado.
     *
     * @param acto Acto en el que se van a sentar los invitados.
     */
    public static void sentarInvitados(Acto acto) {
        List<Anfitrion> anfitrionesOrdenados = new ArrayList<>(acto.getAnfitriones());
        anfitrionesOrdenados.sort(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA, acto));

        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            sentarEnTribuna(acto, anfitrion);
        }
    }

    private static void sentarEnTribuna(Acto acto, Anfitrion anfitrion) {
        int numeroInvitados = (int) anfitrion.getNumeroAsignablesDeUnActoPorZona(acto, TRIBUNA);
        Set<Asignable> asignables = anfitrion.getAsignablesSinAsignarDeUnActoPorZona(acto, TRIBUNA);

        Set<Invitado> invitados = asignables.stream().map(asignable -> (Invitado) asignable).collect(Collectors.toSet());
        List<ZonaConfigurada> zonasOrdenadas = ordenarZonasPorPrioridad(anfitrion.getUnidadDeFormacion(), acto.getZonasConfiguradasPorTipo(TRIBUNA));
        for (ZonaConfigurada zona : zonasOrdenadas) {
            if (sentarInvitadosEnZona(invitados, zona, numeroInvitados)) {
                break;
            }
        }
    }

    private static boolean sentarInvitadosEnZona(Set<Invitado> invitados, ZonaConfigurada zona, int numeroInvitados) {
        for (LocalidadConfigurada localidad : zona.getLocalidades()) {
            List<LocalidadConfigurada> asientosConsecutivos = obtenerLocalidadesConsecutivas(localidad, numeroInvitados);
            if (asientosConsecutivos.size() == invitados.size() && sentar(invitados, asientosConsecutivos)) {
                return true;
            }
        }
        return false;
    }


    private static List<ZonaConfigurada> ordenarZonasPorPrioridad(String unidad, List<ZonaConfigurada> zonas) {
        zonas.sort(Comparator.comparingInt(z -> z.getPrioridades().stream()
                .filter(p -> p.getUnidad().equals(unidad))
                .mapToInt(PrioridadPorUnidad::getPrioridad)
                .findFirst()
                .orElse(Integer.MAX_VALUE)));
        return zonas;
    }

    private static boolean sentar(Set<Invitado> invitados, List<LocalidadConfigurada> localidadesConsecutivas) {
        if (invitados.size() != localidadesConsecutivas.size()) {
            return false;
        }
        int i = 0;
        for (Invitado invitado : invitados) {
            invitado.setLocalidad(localidadesConsecutivas.get(i), true);
            i++;
        }
        return true;
    }

    private static List<LocalidadConfigurada> obtenerLocalidadesConsecutivas(LocalidadConfigurada localidad, int numeroInvitados) {
        List<LocalidadConfigurada> localidadesConsecutivas = new ArrayList<>();
        while (localidad != null && localidadesConsecutivas.size() < numeroInvitados) {
            if (localidad.getEstadoLocalidad() == NORMAL && localidad.getEstadoOcupacionLocalidad() == LIBRE) {
                localidadesConsecutivas.add(localidad);
                if (localidad.getLocalidad().getImplicaSaltoFila() || localidad.getLocalidad().getImplicaSalto() && localidadesConsecutivas.size() < numeroInvitados) {
                    localidadesConsecutivas.clear();
                }
            } else {
                localidadesConsecutivas.clear();
            }
            localidad = localidad.getSiguienteLocalidad();
        }
        return localidadesConsecutivas;
    }
}