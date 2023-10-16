package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Anfitrion;

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
    public static Map<String, List<List<Reparticion>>> calcularReparto(Acto acto) {
        Map<String, List<List<Reparticion>>> distribucionPorUnidad = new HashMap<>();

        for (TipoDeZona tipo : TipoDeZona.values()) {
            int totalInvitacionesZona = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);

            for (String unidad : acto.getUnidadesDeFormacion()) {
                Set<Anfitrion> anfitrionesUnidad = acto.getAnfitriones().stream()
                        .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                        .collect(Collectors.toSet());

                int totalAnfitriones = anfitrionesUnidad.size();
                int invitacionesPorAnfitrion = totalInvitacionesZona / totalAnfitriones;
                int sobrantes = totalInvitacionesZona % totalAnfitriones;

                List<List<Reparticion>> repartosPorAnfitriones = distribucionPorUnidad.getOrDefault(unidad, new ArrayList<>());
                for (Anfitrion anfitrion : anfitrionesUnidad) {
                    List<Reparticion> reparticionAnfitrion = new ArrayList<>();
                    reparticionAnfitrion.add(new Reparticion(tipo, invitacionesPorAnfitrion));
                    repartosPorAnfitriones.add(reparticionAnfitrion);
                }
                distribucionPorUnidad.put(unidad, repartosPorAnfitriones);

                while (sobrantes > 0) {
                    for (List<Reparticion> reparticionAnfitrion : repartosPorAnfitriones) {
                        for (Reparticion r : reparticionAnfitrion) {
                            if (r.getTipoDeZona() == tipo) {
                                r.setNumeroDeInvitaciones(r.getNumeroDeInvitaciones() + 1);
                                sobrantes--;
                                if (sobrantes == 0) break;
                            }
                        }
                        if (sobrantes == 0) break;
                    }
                }
            }
        }

        for (List<List<Reparticion>> repartosPorAnfitriones : distribucionPorUnidad.values()) {
            for (List<Reparticion> reparticionAnfitrion : repartosPorAnfitriones) {
                for (TipoDeZona tipo : TipoDeZona.values()) {
                    if (reparticionAnfitrion.stream().noneMatch(r -> r.getTipoDeZona() == tipo)) {
                        reparticionAnfitrion.add(new Reparticion(tipo, 0));
                    }
                }
            }
        }

        return distribucionPorUnidad;
    }
}