package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.invitados.Asignable;
import es.mdef.gaip_libreria.localidades.Localidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link LocalidadConfigurada}.
 * Esta clase representa una localidad específica que ha sido configurada con un estado y asociada a un invitado y una zona.
 */
@Data
@EqualsAndHashCode(of = {"estadoLocalidad"})
public class LocalidadConfiguradaImpl implements LocalidadConfigurada {
    private static final EstadoLocalidad ESTADO_LOCALIDAD_POR_DEFECTO = EstadoLocalidad.BLOQUEADA;
    private Asignable asignable;
    private Localidad localidad;
    private EstadoLocalidad estadoLocalidad;
    private ZonaConfigurada zonaConfigurada;

    /**
     * Constructor por defecto.
     */
    public LocalidadConfiguradaImpl() {
        setEstadoLocalidad(ESTADO_LOCALIDAD_POR_DEFECTO);
    }

    /**
     * Establece la localidad para la localidad configurada.
     *
     * @param localidad La localidad asociada a la localidad configurada.
     */
    public void setLocalidad(Localidad localidad) {
        if (this.localidad != localidad) {
            this.localidad = localidad;
        }
    }

    /**
     * Establece la zona configurada para la localidad y mantiene la coherencia bidireccional.
     *
     * @param zonaConfigurada La zona configurada para la localidad.
     */
    public void setZonaConfigurada(ZonaConfigurada zonaConfigurada) {
        if (this.zonaConfigurada != zonaConfigurada) {
            if (this.zonaConfigurada != null) {
                this.zonaConfigurada.quitarLocalidad(this);
            }
            this.zonaConfigurada = zonaConfigurada;
            if (this.zonaConfigurada != null) {
                this.zonaConfigurada.agregarLocalidad(this);
            }
        }
    }

    /**
     * Establece el asignable para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param asignable     El asignable asociado a la localidad configurada. No puede ser nulo.
     * @param superarMaximo Si es true, se puede superar el máximo de asigables.
     */
    public void setAsignable(Asignable asignable, boolean superarMaximo) {
        if (this.asignable != asignable) {
            Asignable oldAsignable = this.asignable;
            this.asignable = asignable;

            if (oldAsignable != null) {
                oldAsignable.setLocalidad(null, superarMaximo);
            }

            if (this.asignable != null && this.asignable.getLocalidad() != this) {
                this.asignable.setLocalidad(this, false);
            }
        }
    }
}