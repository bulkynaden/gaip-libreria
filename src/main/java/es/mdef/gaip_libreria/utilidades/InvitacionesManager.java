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
        Map<String, List<Reparticion>> distribucionPorUnidad = new HashMap<>();

        for (TipoDeZona tipo : TipoDeZona.values()) {
            int totalInvitacionesZona = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(tipo);

            for (String unidad : acto.getUnidadesDeFormacion()) {
                List<Anfitrion> anfitrionesUnidad = acto.getAnfitriones().stream()
                        .filter(a -> unidad.equals(a.getUnidadDeFormacion()))
                        .toList();

                int totalInvitacionesUnidad = (int) (totalInvitacionesZona * ((double) anfitrionesUnidad.size() / acto.getAnfitriones().size()));

                int invitacionesPorAnfitrion = totalInvitacionesUnidad / anfitrionesUnidad.size();
                int sobras = totalInvitacionesUnidad % anfitrionesUnidad.size();

                for (int i = 0; i < anfitrionesUnidad.size(); i++) {
                    int invitacionesFinal = invitacionesPorAnfitrion;
                    if (sobras > 0) {
                        invitacionesFinal += 1;
                        sobras--;
                    }
                    distribucionPorUnidad.computeIfAbsent(unidad, k -> new ArrayList<>())
                            .add(new Reparticion(tipo, invitacionesFinal));
                }
            }
        }
        return distribucionPorUnidad;
    }
}