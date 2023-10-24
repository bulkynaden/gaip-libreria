package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
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
import static es.mdef.gaip_libreria.constantes.TipoDeZona.GENERICA;
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
            sentarEnTribuna(acto, anfitrion);
            sentarEnGenerica(acto, anfitrion);
        }
    }

    private static Set<Invitado> obtenerInvitados(Acto acto, Anfitrion anfitrion, TipoDeZona tipoDeZona) {
        return anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, tipoDeZona);
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

    private static void sentarEnTribuna(Acto acto, Anfitrion anfitrion) {
        int numeroInvitados = (int) anfitrion.getNumeroInvitadosDeUnActoPorZona(acto, TRIBUNA);
        Set<Invitado> invitados = obtenerInvitados(acto, anfitrion, TRIBUNA);
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

    private static void sentarEnGenerica(Acto acto, Anfitrion anfitrion) {
        Set<Invitado> invitados = obtenerInvitados(acto, anfitrion, GENERICA);
        for (Invitado invitado : invitados) {
            invitado.setLocalidad(obtenerLocalidadLibreDeZonaGenerica(acto));
        }
    }

    private static LocalidadConfigurada obtenerLocalidadLibreDeZonaGenerica(Acto acto) {
        if (acto == null || acto.getZonas() == null) {
            return null;
        }

        return acto.getZonas().stream()
                .filter(zona -> zona != null && zona.getZona() != null && GENERICA.equals(zona.getZona().getTipoDeZona()))
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(localidad -> localidad != null && LIBRE.equals(localidad.getEstadoOcupacionLocalidad()) && NORMAL.equals(localidad.getEstadoLocalidad()))
                .findFirst()
                .orElse(null);
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