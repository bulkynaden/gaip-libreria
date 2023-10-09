package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Anfitrion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, List<Reparticion>> reparticionesPorUnidad = new HashMap<>();

        int numeroAnfitriones = acto.getAnfitriones().size();
        if (numeroAnfitriones == 0) {
            return reparticionesPorUnidad;
        }

        distribuirInvitaciones(acto, TipoDeZona.TRIBUNA, reparticionesPorUnidad);
        distribuirInvitaciones(acto, TipoDeZona.GENERICA, reparticionesPorUnidad);

        return reparticionesPorUnidad;
    }

    /**
     * Calcula el reparto de localidades entre anfitriones de un acto basándose en una asignación personalizada.
     *
     * @param acto         El acto del cual se quieren repartir las localidades.
     * @param asignaciones Las asignaciones personalizadas por unidad de formación.
     * @return Un mapa con la distribución de localidades por unidad de formación.
     */
    public static Map<String, List<Reparticion>> calcularRepartoPersonalizado(Acto acto, HashMap<String, Reparticion> asignaciones) {
        Map<String, List<Reparticion>> reparticionesPorUnidad = new HashMap<>();
        Map<String, Integer> totalAnfitrionesPorUnidad = obtenerTotalAnfitrionesPorUnidad(acto);

        for (Map.Entry<String, Reparticion> entry : asignaciones.entrySet()) {
            String unidad = entry.getKey();
            validarUnidadConAnfitriones(unidad, totalAnfitrionesPorUnidad);

            distribuirInvitacionesPersonalizadas(entry, totalAnfitrionesPorUnidad, reparticionesPorUnidad);
        }

        return reparticionesPorUnidad;
    }

    private static void distribuirInvitaciones(Acto acto, TipoDeZona tipo, Map<String, List<Reparticion>> reparticionesPorUnidad) {
        int cantidadLocalidades = acto.getNumeroLocalidadesParaRepartirPorTipoDeZOna(tipo);
        int numeroAnfitriones = acto.getAnfitriones().size();

        int base = cantidadLocalidades / numeroAnfitriones;
        int remanente = cantidadLocalidades % numeroAnfitriones;

        for (Anfitrion anfitrion : acto.getAnfitriones()) {
            String unidad = anfitrion.getUnidadDeFormacion();
            int cantidadParaAnfitrion = base;

            if (remanente > 0) {
                cantidadParaAnfitrion++;
                remanente--;
            }

            Reparticion reparticion;
            if (tipo == TipoDeZona.TRIBUNA) {
                reparticion = new Reparticion(cantidadParaAnfitrion, 0);
            } else {
                reparticion = new Reparticion(0, cantidadParaAnfitrion);
            }

            reparticionesPorUnidad.putIfAbsent(unidad, new ArrayList<>());
            reparticionesPorUnidad.get(unidad).add(reparticion);
        }
    }

    private static void distribuirInvitacionesPersonalizadas(Map.Entry<String, Reparticion> entry, Map<String, Integer> totalAnfitrionesPorUnidad, Map<String, List<Reparticion>> reparticionesPorUnidad) {
        String unidad = entry.getKey();
        Reparticion asignacionUnidad = entry.getValue();

        int totalAnfitriones = totalAnfitrionesPorUnidad.get(unidad);
        distribuirInvitacionPersonalizada(asignacionUnidad.getInvitacionesTribuna(), totalAnfitriones, reparticionesPorUnidad, unidad, true);
        distribuirInvitacionPersonalizada(asignacionUnidad.getInvitacionesGenerica(), totalAnfitriones, reparticionesPorUnidad, unidad, false);
    }

    private static void distribuirInvitacionPersonalizada(int cantidadInvitaciones, int totalAnfitriones, Map<String, List<Reparticion>> reparticionesPorUnidad, String unidad, boolean esTribuna) {
        int base = cantidadInvitaciones / totalAnfitriones;
        int remanente = cantidadInvitaciones % totalAnfitriones;

        List<Reparticion> reparticiones = new ArrayList<>();
        for (int i = 0; i < totalAnfitriones; i++) {
            int cantidadParaAnfitrion = base;
            if (remanente > 0) {
                cantidadParaAnfitrion++;
                remanente--;
            }

            Reparticion reparticion;
            if (esTribuna) {
                reparticion = new Reparticion(cantidadParaAnfitrion, 0);
            } else {
                reparticion = new Reparticion(0, cantidadParaAnfitrion);
            }
            reparticiones.add(reparticion);
        }

        reparticionesPorUnidad.put(unidad, reparticiones);
    }

    private static Map<String, Integer> obtenerTotalAnfitrionesPorUnidad(Acto acto) {
        Map<String, Integer> totalAnfitrionesPorUnidad = new HashMap<>();
        for (Anfitrion anfitrion : acto.getAnfitriones()) {
            totalAnfitrionesPorUnidad.put(anfitrion.getUnidadDeFormacion(), totalAnfitrionesPorUnidad.getOrDefault(anfitrion.getUnidadDeFormacion(), 0) + 1);
        }
        return totalAnfitrionesPorUnidad;
    }

    private static void validarUnidadConAnfitriones(String unidad, Map<String, Integer> totalAnfitrionesPorUnidad) {
        if (!totalAnfitrionesPorUnidad.containsKey(unidad)) {
            throw new IllegalArgumentException("La unidad " + unidad + " no tiene anfitriones asignados.");
        }
    }
}