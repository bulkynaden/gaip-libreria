package es.mdef.gaip_libreria.zonas_configuradas;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link PrioridadPorUnidad}.
 * Esta clase representa una prioridad específica asignada a una unidad y asociada a una zona configurada.
 */
@Data
@EqualsAndHashCode(of = {"prioridad", "unidad"})
public class PrioridadPorUnidadImpl implements PrioridadPorUnidad {
    private int prioridad;
    private String unidad;
    private ZonaConfigurada zona;

    /**
     * Establece la zona configurada para la prioridad por unidad y mantiene la coherencia bidireccional.
     *
     * @param zona La zona configurada asociada a la prioridad por unidad.
     */
    public void setZona(ZonaConfigurada zona) {
        if (this.zona != zona) {
            if (this.zona != null) {
                this.zona.getPrioridades().remove(this);
            }
            this.zona = zona;
            if (this.zona != null) {
                zona.getPrioridades().add(this);
            }
        }
    }
}