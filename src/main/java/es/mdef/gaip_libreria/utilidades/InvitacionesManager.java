package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.*;
import es.mdef.gaip_libreria.zonas.LocalidadNumerada;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Gestiona el proceso de distribución de invitaciones para un acto específico.
 * Esta clase es inmutable y no debe ser instanciada.
 */
public final class InvitacionesManager {

    /**
     * Constructor privado para evitar la instanciación.
     */
    private InvitacionesManager() {
    }

    /**
     * Calcula el reparto equitativo de localidades entre anfitriones de un acto.
     *
     * @param acto El acto del cual se quieren repartir las localidades.
     * @return Un mapa con la distribución de localidades por unidad de formación.
     */
    public static Map<String, List<Reparticion>> calcularReparto(Acto acto) {
        Map<String, List<Reparticion>> distribucionPorUnidad = new HashMap<>();
        Map<String, Integer> cantidadAnfitrionesPorUnidad = new HashMap<>();

        for (String unidad : acto.getUnidadesDeFormacion()) {
            int count = (int) acto.getAnfitriones().stream()
                    .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                    .count();
            cantidadAnfitrionesPorUnidad.put(unidad, count);
        }

        for (TipoDeZona tipo : TipoDeZona.values()) {
            int totalInvitacionesZona = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);
            Map<String, Integer> invitacionesPorUnidad = new HashMap<>();

            for (String unidad : acto.getUnidadesDeFormacion()) {
                int base = totalInvitacionesZona * cantidadAnfitrionesPorUnidad.get(unidad) / acto.getAnfitriones().size();
                invitacionesPorUnidad.put(unidad, base);
            }

            int sobras = totalInvitacionesZona - invitacionesPorUnidad.values().stream().mapToInt(Integer::intValue).sum();
            while (sobras > 0) {
                for (String unidad : cantidadAnfitrionesPorUnidad.keySet().stream()
                        .sorted((u1, u2) -> Integer.compare(cantidadAnfitrionesPorUnidad.get(u2), cantidadAnfitrionesPorUnidad.get(u1)))
                        .toList()) {
                    if (sobras > 0) {
                        invitacionesPorUnidad.put(unidad, invitacionesPorUnidad.get(unidad) + 1);
                        sobras--;
                    } else {
                        break;
                    }
                }
            }

            for (String unidad : acto.getUnidadesDeFormacion()) {
                List<Anfitrion> anfitrionesUnidad = acto.getAnfitriones().stream()
                        .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                        .toList();

                int totalInvitacionesUnidad = invitacionesPorUnidad.get(unidad);
                int basePorAnfitrion = totalInvitacionesUnidad / anfitrionesUnidad.size();
                int sobraPorAnfitrion = totalInvitacionesUnidad % anfitrionesUnidad.size();

                for (int i = 0; i < anfitrionesUnidad.size(); i++) {
                    int invitacionesFinal = (i < sobraPorAnfitrion) ? (basePorAnfitrion + 1) : basePorAnfitrion;
                    distribucionPorUnidad.computeIfAbsent(unidad, k -> new ArrayList<>())
                            .add(new Reparticion(tipo, invitacionesFinal));
                }
            }
        }
        return distribucionPorUnidad;
    }

    public static Map<String, List<Reparticion>> calcularRepartoPersonalizado(Acto acto, Map<String, List<Reparticion>> distribucionPersonalizada) {
        Map<String, List<Reparticion>> distribucionPorUnidad = new HashMap<>();

        for (TipoDeZona tipo : TipoDeZona.values()) {
            int totalSolicitado = distribucionPersonalizada.values().stream()
                    .flatMapToInt(list -> list.stream()
                            .filter(rep -> rep.getTipoDeZona() == tipo)
                            .mapToInt(Reparticion::getNumeroDeInvitaciones))
                    .sum();

            int totalDisponible = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);
            if (totalSolicitado > totalDisponible) {
                throw new IllegalArgumentException("La cantidad solicitada para el tipo de zona " + tipo + " excede el límite disponible.");
            }
        }

        for (TipoDeZona tipo : TipoDeZona.values()) {
            for (String unidad : acto.getUnidadesDeFormacion()) {
                int totalInvitacionesZona = distribucionPersonalizada.get(unidad).stream()
                        .filter(r -> r.getTipoDeZona() == tipo)
                        .mapToInt(Reparticion::getNumeroDeInvitaciones)
                        .sum();

                List<Anfitrion> anfitrionesUnidad = acto.getAnfitriones().stream()
                        .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                        .toList();

                int basePorAnfitrion = totalInvitacionesZona / anfitrionesUnidad.size();
                int sobraPorAnfitrion = totalInvitacionesZona % anfitrionesUnidad.size();

                for (int i = 0; i < anfitrionesUnidad.size(); i++) {
                    int invitacionesFinal = (i < sobraPorAnfitrion) ? (basePorAnfitrion + 1) : basePorAnfitrion;
                    distribucionPorUnidad.computeIfAbsent(unidad, k -> new ArrayList<>())
                            .add(new Reparticion(tipo, invitacionesFinal));
                }
            }
        }
        return distribucionPorUnidad;
    }

    public static void repartirInvitados(Acto acto) {
        List<Anfitrion> anfitriones = ordenarAnfitrionesPorNumeroDeInvitados(acto.getAnfitriones());
        for (ZonaConfigurada zona : acto.getZonas()) {
            List<LocalidadConfigurada> localidadesOrdenadas = ordenarLocalidadesPorNumero(zona.getLocalidades());
            for (LocalidadConfigurada localidad : localidadesOrdenadas) {
                sentarInvitadosDeAnfitrionesEnLocalidad(anfitriones, localidad, acto);
            }
        }
    }

    private static List<Anfitrion> ordenarAnfitrionesPorNumeroDeInvitados(Set<Anfitrion> anfitrionesSet) {
        List<Anfitrion> anfitriones = new ArrayList<>(anfitrionesSet);
        anfitriones.sort(new ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona.TRIBUNA));
        return anfitriones;
    }

    private static List<LocalidadConfigurada> ordenarLocalidadesPorNumero(Set<LocalidadConfigurada> localidadesSet) {
        return localidadesSet.stream()
                .sorted(Comparator.comparingInt(o -> ((LocalidadNumerada) o.getLocalidad()).getNumero()))
                .collect(Collectors.toList());
    }

    private static void sentarInvitadosDeAnfitrionesEnLocalidad(List<Anfitrion> anfitriones, LocalidadConfigurada localidadInicial, Acto acto) {
        for (Anfitrion anfitrion : anfitriones) {
            long numero = anfitrion.getNumeroInvitadosPorZona(TipoDeZona.TRIBUNA);
            if (esPosibleSentarInvitados(localidadInicial, numero)) {
                asignarInvitados(anfitrion, localidadInicial, acto, numero);
            }
        }
    }

    private static boolean esPosibleSentarInvitados(LocalidadConfigurada localidad, long numeroInvitados) {
        LocalidadConfigurada localidadActual = localidad;
        for (int i = 0; i < numeroInvitados; i++) {
            if (localidadActual == null || localidadActual.getEstadoLocalidad() != EstadoLocalidad.NORMAL ||
                    localidadActual.getEstadoOcupacionLocalidad() != EstadoOcupacionLocalidad.LIBRE ||
                    localidadActual.getLocalidad().getImplicaSalto() ||
                    localidadActual.getLocalidad().getImplicaSaltoFila()) {
                return false;
            }
            localidadActual = localidadActual.getSiguienteLocalidad();
        }
        return true;
    }

    private static void asignarInvitados(Anfitrion anfitrion, LocalidadConfigurada localidadInicial, Acto acto, long numeroInvitados) {
        Set<InvitacionesPorActo> invitacionesPorActo = anfitrion.getInvitacionesPorActo();
        InvitacionesPorActo inv = invitacionesPorActo.stream()
                .filter(e -> e.getActo() == acto && e.getAnfitrion() == anfitrion)
                .findFirst()
                .orElse(null);

        if (inv != null) {
            Set<Invitacion> invitaciones = inv.getInvitaciones().stream()
                    .filter(e -> e.getTipoDeZona() == TipoDeZona.TRIBUNA)
                    .collect(Collectors.toSet());

            LocalidadConfigurada localidadActual = localidadInicial;
            for (Invitacion invitacion : invitaciones) {
                for (Invitado invitado : invitacion.getInvitados()) {
                    invitado.setLocalidad(localidadActual);
                    localidadActual = localidadActual.getSiguienteLocalidad();
                }
            }
        }
    }
}