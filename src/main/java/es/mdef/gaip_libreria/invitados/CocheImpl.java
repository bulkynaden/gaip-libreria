package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import lombok.Data;

@Data
public class CocheImpl implements Coche {
    private Invitado invitado;
    private String matricula;
    private String color;
    private String modelo;
    private String marca;
    private LocalidadConfigurada localidad;
    private Invitacion invitacion;

    /**
     * Establece la localidad para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param localidad La localidad asociada a la localidad configurada. No puede ser nula.
     */
    @Override
    public void setLocalidad(LocalidadConfigurada localidad, boolean superarMaximo) {
        if (this.localidad != localidad) {
            LocalidadConfigurada oldLocalidad = this.localidad;
            this.localidad = localidad;

            if (oldLocalidad != null) {
                oldLocalidad.setAsignable(null, superarMaximo);
            }

            if (this.localidad != null && this.localidad.getAsignable() != this) {
                this.localidad.setAsignable(this, superarMaximo);
            }
        }
    }

    @Override
    public void setInvitacion(Invitacion invitacion, boolean superarMaximo) {
        if (this.invitacion != invitacion) {
            if (this.invitacion != null) {
                this.invitacion.quitarAsignable(this);
            }
            this.invitacion = invitacion;
            if (this.invitacion != null) {
                this.invitacion.quitarAsignable(this);
            }
        }
    }
}