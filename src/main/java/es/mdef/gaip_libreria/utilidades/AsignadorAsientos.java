package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
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

public final class AsignadorAsientos {
    private AsignadorAsientos() {
    }

    public static void sentarInvitados(Acto acto) {
        Set<Anfitrion> anfitriones = acto.getAnfitriones();
        List<Anfitrion> anfitrionesOrdenados = anfitriones.stream()
                .sorted(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA, acto))
                .toList();

        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            int numeroInvitados = (int) anfitrion.getNumeroInvitadosDeUnActoPorZona(acto, TRIBUNA);
            Set<Invitado> invitados = obtenerInvitados(acto, anfitrion);

            boolean invitadosSentados = false;

            if (numeroInvitados > 0) {
                List<ZonaConfigurada> zonasOrdenadas = ordenarZonasPorPrioridad(
                        anfitrion.getUnidadDeFormacion(),
                        acto.getZonas());

                for (ZonaConfigurada zona : zonasOrdenadas) {
                    for (LocalidadConfigurada localidad : zona.getLocalidades()) {
                        List<LocalidadConfigurada> asientosConsecutivos = obtenerLocalidadesConsecutivas(localidad, numeroInvitados);
                        if (asientosConsecutivos.size() <= invitados.size()) {
                            invitadosSentados = sentar(invitados, asientosConsecutivos);
                            if (invitadosSentados) {
                                break;
                            }
                        }
                    }

                    if (invitadosSentados) {
                        break;
                    }
                }
            }
        }
    }


    private static Set<Invitado> obtenerInvitados(Acto acto, Anfitrion anfitrion) {
        return anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, TRIBUNA);
    }

    private static List<ZonaConfigurada> ordenarZonasPorPrioridad(String unidad, List<ZonaConfigurada> zonas) {
        return zonas.stream()
                .sorted(Comparator.comparingInt(z -> z.getPrioridades().stream()
                        .filter(p -> p.getUnidad().equals(unidad))
                        .mapToInt(PrioridadPorUnidad::getPrioridad)
                        .findFirst()
                        .orElse(Integer.MAX_VALUE)))
                .collect(Collectors.toList());
    }

    private static List<LocalidadConfigurada> obtenerLocalidadesConsecutivas(LocalidadConfigurada localidad, int numeroInvitados) {
        List<LocalidadConfigurada> localidadesConsecutivas = new ArrayList<>();

        while (localidad != null && localidadesConsecutivas.size() < numeroInvitados) {
            if (localidad.getEstadoLocalidad() == NORMAL && localidad.getEstadoOcupacionLocalidad() == LIBRE) {
                localidadesConsecutivas.add(localidad);
                if (localidad.getLocalidad().getImplicaSaltoFila() || localidad.getLocalidad().getImplicaSalto()) {
                    if (localidadesConsecutivas.size() < numeroInvitados) {
                        localidadesConsecutivas.clear();
                    }
                }
            } else {
                localidadesConsecutivas.clear();
            }
            localidad = localidad.getSiguienteLocalidad();
        }

        return localidadesConsecutivas;
    }

    private static boolean sentar(Set<Invitado> invitados, List<LocalidadConfigurada> localidadesConsecutivas) {
        if (invitados.size() != localidadesConsecutivas.size()) {
            return false;
        }
        int i = 0;
        for (Invitado invitado : invitados) {
            LocalidadConfigurada localidadConfigurada = localidadesConsecutivas.get(i);
            invitado.setLocalidad(localidadConfigurada);
            i++;
        }

        return true;
    }

    public static void levantarInvitados(Acto acto) {
        acto.getInvitados().stream().filter(e -> localidadEsLibrerable(e.getLocalidad())).forEach(e -> e.setLocalidad(null));
    }

    private static boolean localidadEsLibrerable(LocalidadConfigurada localidadConfigurada) {
        if (localidadConfigurada == null) {
            return false;
        }
        return localidadConfigurada.getEstadoLocalidad() == NORMAL;
    }
}