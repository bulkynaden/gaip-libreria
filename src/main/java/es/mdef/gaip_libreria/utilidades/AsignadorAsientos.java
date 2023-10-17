package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
import es.mdef.gaip_libreria.invitados.Anfitrion;
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
import static es.mdef.gaip_libreria.constantes.TipoDeZona.TRIBUNA;

public final class AsignadorAsientos {

    public static void sentarInvitados(Acto acto) {
        Set<Anfitrion> anfitriones = acto.getAnfitriones();
        List<Anfitrion> anfitrionesOrdenados = new ArrayList<>(anfitriones.stream().toList());
        anfitrionesOrdenados.sort(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA));
        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            int numeroInvitados = (int) anfitrion.getNumeroInvitadosPorZona(TRIBUNA);
            List<Invitado> invitados = anfitrion.getInvitacionesPorActo().stream()
                    .flatMap(e -> e.getInvitaciones().stream())
                    .filter(e1 -> e1.getTipoDeZona() == TRIBUNA)
                    .flatMap(e2 -> e2.getInvitados().stream())
                    .toList();
            List<ZonaConfigurada> zonasOrdenadas = ordenarZonasPorPrioridad(anfitrion.getUnidadDeFormacion(), acto.getZonas());

            for (ZonaConfigurada zona : zonasOrdenadas) {
                for (LocalidadConfigurada fila : zona.getLocalidades()) {
                    List<LocalidadConfigurada> asientosConsecutivos = obtenerLocalidadesConsecutivas(fila, numeroInvitados);
                    if (asientosConsecutivos.size() == numeroInvitados) {
                        sentar(invitados, asientosConsecutivos);
                        break;
                    }
                }
            }
        }
    }

    private static List<ZonaConfigurada> ordenarZonasPorPrioridad(String unidad, Set<ZonaConfigurada> zonas) {
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
            if (localidad.getEstadoLocalidad() == NORMAL && localidad.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE) {
                localidadesConsecutivas.add(localidad);
                if (localidad.getLocalidad().getImplicaSaltoFila() || localidad.getLocalidad().getImplicaSalto()) {
                    if (localidadesConsecutivas.size() < numeroInvitados) {
                        localidadesConsecutivas.clear();
                    } else {
                        break;
                    }
                }
            } else {
                localidadesConsecutivas.clear();
            }
            localidad = localidad.getSiguienteLocalidad();
        }

        return localidadesConsecutivas;
    }

    private static void sentar(List<Invitado> invitados, List<LocalidadConfigurada> localidadesConsecutivas) {
        for (int i = 0; i < invitados.size(); i++) {
            LocalidadConfigurada localidadConfigurada = localidadesConsecutivas.get(i);
            Invitado invitado = invitados.get(i);
            localidadConfigurada.setInvitado(invitado);
            invitado.setLocalidad(localidadConfigurada);
        }
    }
}