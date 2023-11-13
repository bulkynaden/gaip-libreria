package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"matricula"})
public class CocheImpl implements Coche {
    private Invitado invitado;
    private String matricula;
    private String color;
    private String modelo;
    private String marca;
    private LocalidadConfigurada localidad;
    private Invitacion invitacion;

    @Override
    public void setLocalidad(LocalidadConfigurada localidad, boolean superarMaximo) {
        if (this.localidad != localidad) {
            LocalidadConfigurada oldLocalidad = this.localidad;
            this.localidad = localidad;

            if (oldLocalidad != null) {
                oldLocalidad.setCoche(null, superarMaximo);
            }

            if (this.localidad != null && this.localidad.getCoche() != this) {
                this.localidad.setCoche(this, superarMaximo);
            }
        }
    }

    /**
     * Asocia una invitación al coche. Si el coche ya estaba asociado a otra invitación,
     * se elimina esa asociación previa. Establece la relación bidireccional entre el invitado y la invitación.
     *
     * @param invitacion La invitación a asociar con el invitado.
     */
    @Override
    public void setInvitacion(Invitacion invitacion, boolean superarMaximo) {
        if (this.invitacion != invitacion) {
            if (this.invitacion != null) {
                this.invitacion.quitarCoche(this);
            }
            this.invitacion = invitacion;
            if (this.invitacion != null && !this.invitacion.getCoches().contains(this)) {
                this.invitacion.agregarCoche(this, superarMaximo);
            }
        }
    }
}