package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;

import java.util.*;

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

    private static List<Anfitrion> ordenarAnfitrionesPorNumeroDeInvitados(Acto acto, Set<Anfitrion> anfitrionesSet) {
        List<Anfitrion> anfitriones = new ArrayList<>(anfitrionesSet);
        anfitriones.sort(new ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona.TRIBUNA, acto));
        return anfitriones;
    }
}