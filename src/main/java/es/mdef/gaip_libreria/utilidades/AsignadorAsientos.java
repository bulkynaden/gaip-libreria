package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;
import es.mdef.gaip_libreria.invitados.Invitacion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas.LocalidadNumerada;
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
            List<Invitado> invitados = obtenerInvitados(acto, anfitrion);

            boolean invitadosSentados = false;

            if (numeroInvitados > 0) {
                List<ZonaConfigurada> zonasOrdenadas = ordenarZonasPorPrioridad(
                        anfitrion.getUnidadDeFormacion(),
                        acto.getZonas());

                for (ZonaConfigurada zona : zonasOrdenadas) {
                    for (LocalidadConfigurada localidad : zona.getLocalidades()) {
                        if (localidad instanceof LocalidadNumerada localidadNumerada) {
                            System.out.println(zona.getZona().getNombre() + " " + localidadNumerada.getNumero());
                        }
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
            Invitacion a = anfitrion.getInvitacionesPorActo().stream().filter(e -> e.getActo() == acto).findFirst().get().getInvitaciones().stream().filter(e -> e.getTipoDeZona() == TRIBUNA).findFirst().get();
        }
    }


    private static List<Invitado> obtenerInvitados(Acto acto, Anfitrion anfitrion) {
        return anfitrion.getInvitacionesPorActo().stream().filter(e -> e.getActo() == acto)
                .flatMap(e -> e.getInvitaciones().stream())
                .filter(e1 -> e1.getTipoDeZona() == TRIBUNA)
                .flatMap(e2 -> e2.getInvitados().stream())
                .toList();
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

    private static boolean sentar(List<Invitado> invitados, List<LocalidadConfigurada> localidadesConsecutivas) {
        if (invitados.size() != localidadesConsecutivas.size()) {
            return false;
        }
        for (int i = 0; i < invitados.size(); i++) {
            LocalidadConfigurada localidadConfigurada = localidadesConsecutivas.get(i);
            Invitado invitado = invitados.get(i);
            invitado.setLocalidad(localidadConfigurada);
        }

        return true;
    }
}