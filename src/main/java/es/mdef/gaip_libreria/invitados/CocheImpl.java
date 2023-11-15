package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación de la interfaz {@link Coche}.
 * Esta clase representa un coche concreto con propiedades como invitado, matrícula, color,
 * modelo, marca, localidad e invitación. Utiliza Lombok para generar automáticamente los métodos
 * getters, setters, equals y hashCode.
 */
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

    /**
     * Asigna una localidad a este coche. Si el coche ya estaba asignado a otra localidad,
     * elimina esa asignación previa. Gestiona la relación bidireccional entre el coche y la localidad.
     *
     * @param localidad             La localidad a asignar al coche.
     * @param permitirExcederMaximo Indica si se permite superar el máximo de coches permitidos para el anfitrión.
     */
    @Override
    public void setLocalidad(LocalidadConfigurada localidad, boolean permitirExcederMaximo) {
        if (this.localidad != localidad) {
            LocalidadConfigurada oldLocalidad = this.localidad;
            this.localidad = localidad;

            if (oldLocalidad != null) {
                oldLocalidad.setCoche(null, permitirExcederMaximo);
            }

            if (this.localidad != null && this.localidad.getCoche() != this) {
                this.localidad.setCoche(this, permitirExcederMaximo);
            }
        }
    }

    /**
     * Asocia una invitación al coche. Si el coche ya estaba asociado a otra invitación,
     * se elimina esa asociación previa. Establece la relación bidireccional entre el coche y la invitación.
     *
     * @param invitacion            La invitación a asociar con el coche.
     * @param permitirExcederMaximo Indica si se permite superar el máximo de coches permitidos para el anfitrión.
     */
    @Override
    public void setInvitacion(Invitacion invitacion, boolean permitirExcederMaximo) {
        if (this.invitacion != invitacion) {
            if (this.invitacion != null) {
                this.invitacion.quitarCoche(this);
            }
            this.invitacion = invitacion;
            if (this.invitacion != null && !this.invitacion.getCoches().contains(this)) {
                this.invitacion.agregarCoche(this, permitirExcederMaximo);
            }
        }
    }
}