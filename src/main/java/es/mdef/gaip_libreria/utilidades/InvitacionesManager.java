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
    public static Map<String, List<List<Reparticion>>> calcularReparto(Acto acto) {
        Map<String, List<List<Reparticion>>> reparticionesPorUnidad = new HashMap<>();

        int numeroAnfitriones = acto.getAnfitriones().size();
        if (numeroAnfitriones == 0) {
            return reparticionesPorUnidad;
        }

        distribuirInvitaciones(acto, reparticionesPorUnidad);

        return reparticionesPorUnidad;
    }

    /**
     * Calcula el reparto de localidades entre anfitriones de un acto basándose en una asignación personalizada.
     *
     * @param acto         El acto del cual se quieren repartir las localidades.
     * @param asignaciones Las asignaciones personalizadas por unidad de formación.
     * @return Un mapa con la distribución de localidades por unidad de formación.
     */
    public static Map<String, List<List<Reparticion>>> calcularRepartoPersonalizado(Acto acto, Map<String, Reparticion> asignaciones) {
        Map<String, List<List<Reparticion>>> reparticionesPorUnidad = new HashMap<>();
        Map<String, Integer> totalAnfitrionesPorUnidad = obtenerTotalAnfitrionesPorUnidad(acto);

        for (Map.Entry<String, Reparticion> entry : asignaciones.entrySet()) {
            String unidad = entry.getKey();
            validarUnidadConAnfitriones(unidad, totalAnfitrionesPorUnidad);

            distribuirInvitacionesPersonalizadas(entry, totalAnfitrionesPorUnidad, reparticionesPorUnidad);
        }

        return reparticionesPorUnidad;
    }

    private static void distribuirInvitacionesPersonalizadas(Map.Entry<String, Reparticion> entry, Map<String, Integer> totalAnfitrionesPorUnidad, Map<String, List<List<Reparticion>>> reparticionesPorUnidad) {
        String unidad = entry.getKey();
        Reparticion asignacionUnidad = entry.getValue();
        int totalAnfitriones = totalAnfitrionesPorUnidad.get(unidad);

        for (var tipo : TipoDeZona.values()) {
            List<Reparticion> reparticionesPorTipo = distribuirInvitacionPersonalizada(asignacionUnidad.getNumeroDeInvitaciones(), totalAnfitriones, tipo);
            List<Reparticion> repartosAnfitrion = obtenerOCrearRepartoAnfitrion(unidad, tipo, reparticionesPorUnidad);
            repartosAnfitrion.addAll(reparticionesPorTipo);
        }
    }

    private static List<Reparticion> distribuirInvitacionPersonalizada(int cantidadInvitaciones, int totalAnfitriones, TipoDeZona tipo) {
        int base = cantidadInvitaciones / totalAnfitriones;
        int remanente = cantidadInvitaciones % totalAnfitriones;

        List<Reparticion> reparticiones = new ArrayList<>();
        for (int i = 0; i < totalAnfitriones; i++) {
            int cantidadParaAnfitrion = base;
            if (remanente > 0) {
                cantidadParaAnfitrion++;
                remanente--;
            }

            Reparticion reparticion = new Reparticion(tipo, cantidadParaAnfitrion);
            reparticiones.add(reparticion);
        }

        return reparticiones;
    }

    private static void distribuirInvitaciones(Acto acto, Map<String, List<List<Reparticion>>> reparticionesPorUnidad) {
        for (TipoDeZona tipo : TipoDeZona.values()) {
            int cantidadLocalidades = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);
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

                reparticionesPorUnidad.putIfAbsent(unidad, new ArrayList<>());

                List<Reparticion> repartosAnfitrion = reparticionesPorUnidad.get(unidad).stream()
                        .filter(lista -> lista.stream().anyMatch(reparticion -> reparticion.getTipoDeZona() == tipo))
                        .findFirst()
                        .orElseGet(() -> {
                            List<Reparticion> nuevaLista = new ArrayList<>();
                            reparticionesPorUnidad.get(unidad).add(nuevaLista);
                            return nuevaLista;
                        });

                Reparticion reparticionActual = repartosAnfitrion.stream()
                        .filter(reparticion -> reparticion.getTipoDeZona() == tipo)
                        .findFirst()
                        .orElseGet(() -> {
                            Reparticion nuevaReparticion = new Reparticion(tipo, 0);
                            repartosAnfitrion.add(nuevaReparticion);
                            return nuevaReparticion;
                        });

                reparticionActual.setNumeroDeInvitaciones(reparticionActual.getNumeroDeInvitaciones() + cantidadParaAnfitrion);
            }
        }
    }

    private static List<Reparticion> distribuirInvitacionPersonalizada(int cantidadInvitaciones, int totalAnfitriones) {
        int base = cantidadInvitaciones / totalAnfitriones;
        int remanente = cantidadInvitaciones % totalAnfitriones;

        List<Reparticion> reparticiones = new ArrayList<>();
        for (int i = 0; i < totalAnfitriones; i++) {
            int cantidadParaAnfitrion = base;
            if (remanente > 0) {
                cantidadParaAnfitrion++;
                remanente--;
            }

            Reparticion reparticion = new Reparticion();
            reparticion.setNumeroDeInvitaciones(cantidadParaAnfitrion);
            reparticion.setTipoDeZona(null);
            reparticiones.add(reparticion);
        }

        return reparticiones;
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

    private static List<Reparticion> obtenerOCrearRepartoAnfitrion(String unidad, TipoDeZona tipo, Map<String, List<List<Reparticion>>> reparticionesPorUnidad) {
        reparticionesPorUnidad.putIfAbsent(unidad, new ArrayList<>());

        return reparticionesPorUnidad.get(unidad).stream()
                .filter(lista -> lista.stream().anyMatch(reparticion -> reparticion.getTipoDeZona() == tipo))
                .findFirst()
                .orElseGet(() -> {
                    List<Reparticion> nuevaLista = new ArrayList<>();
                    reparticionesPorUnidad.get(unidad).add(nuevaLista);
                    return nuevaLista;
                });
    }
}