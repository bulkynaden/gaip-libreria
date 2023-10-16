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
        Map<String, List<List<Reparticion>>> distribucionPorUnidad = new HashMap<>();

        int totalAnfitriones = acto.getAnfitriones().size();

        for (String unidad : acto.getUnidadesDeFormacion()) {
            List<Anfitrion> anfitrionesUnidad = acto.getAnfitriones().stream()
                    .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                    .toList();

            for (TipoDeZona tipo : TipoDeZona.values()) {
                int totalInvitacionesZona = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);
                int invitacionesUnidad = (totalInvitacionesZona * anfitrionesUnidad.size()) / totalAnfitriones;
                int invitacionesPorAnfitrion = invitacionesUnidad / anfitrionesUnidad.size();

                for (Anfitrion anfitrion : anfitrionesUnidad) {
                    List<Reparticion> reparticionesPorAnfitrion = new ArrayList<>();
                    reparticionesPorAnfitrion.add(new Reparticion(tipo, invitacionesPorAnfitrion));

                    distribucionPorUnidad.computeIfAbsent(unidad, k -> new ArrayList<>()).add(reparticionesPorAnfitrion);
                }
            }
        }

        return distribucionPorUnidad;
    }
}