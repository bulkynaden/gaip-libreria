package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa un anfitrión que puede invitar a personas a un acto.
 * Un anfitrión está asociado a una unidad de formación y tiene un conjunto de invitaciones.
 */
@EqualsAndHashCode(of = {"unidadDeFormacion"}, callSuper = true)
@Getter
@Setter
public class AnfitrionImpl extends PersonaImpl implements Anfitrion {
    private String unidadDeFormacion;
    private Acto acto;
    private Set<Invitacion> invitaciones = new HashSet<>();

    /**
     * Asocia un acto a este anfitrión. Si el anfitrión ya estaba asociado a un acto,
     * se desvincula de ese acto antes de asociarse al nuevo.
     *
     * @param acto El acto a asociar con este anfitrión.
     */
    public void setActo(Acto acto) {
        if (acto == null) {
            throw new IllegalArgumentException("El acto no puede ser nulo.");
        }
        if (this.acto != acto) {
            if (this.acto != null) {
                this.acto.getAnfitriones().remove(this);
            }
            this.acto = acto;
            acto.getAnfitriones().add(this);
        }
    }

    /**
     * Agrega una invitación al conjunto de invitaciones del anfitrión y establece la relación bidireccional.
     *
     * @param invitacion Invitación a agregar.
     */
    @Override
    public void agregarInvitacion(Invitacion invitacion) {
        if (invitacion == null) {
            throw new IllegalArgumentException("La invitación no puede ser nula.");
        }
        if (!invitaciones.contains(invitacion)) {
            invitaciones.add(invitacion);
            if (invitacion.getAnfitrion() != this) {
                invitacion.setAnfitrion(this);
            }
        }
    }

    /**
     * Quita una invitación del conjunto de invitaciones del anfitrión y desvincula la relación bidireccional.
     *
     * @param invitacion Invitación a quitar.
     */
    @Override
    public void quitarInvitacion(Invitacion invitacion) {
        if (invitacion == null || !invitaciones.contains(invitacion)) {
            throw new IllegalArgumentException("La invitación no está asociada a este anfitrión o es nula.");
        }
        invitaciones.remove(invitacion);
        if (invitacion.getAnfitrion() == this) {
            invitacion.setAnfitrion(null);
        }
    }
}