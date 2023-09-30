package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link InvitacionesPorActo}.
 * Esta clase representa las invitaciones extendidas por un anfitrión para un acto específico.
 */
@Getter
@EqualsAndHashCode(of = "")
public class InvitacionesPorActoImpl implements InvitacionesPorActo {
    private final Set<Invitacion> invitaciones = new HashSet<>();
    private Acto acto;
    private Anfitrion anfitrion;

    public InvitacionesPorActoImpl(Acto acto, Anfitrion anfitrion) {
        this.acto = acto;
        this.anfitrion = anfitrion;
    }

    @Override
    public void setActo(Acto acto) {
        if (this.acto != acto) {
            this.acto = acto;
        }
    }

    @Override
    public void setAnfitrion(Anfitrion anfitrion) {
        if (this.anfitrion != anfitrion) {
            if (this.anfitrion != null) {
                this.anfitrion.quitarInvitacionesPorActo(this);
            }
            this.anfitrion = anfitrion;
            if (anfitrion != null && !anfitrion.getInvitacionesPorActo().contains(this)) {
                anfitrion.agregarInvitacionesPorActo(this);
            }
        }
    }

    @Override
    public void setInvitaciones(Set<Invitacion> invitaciones) {
        if (this.invitaciones != invitaciones) {
            this.invitaciones.clear();
            if (invitaciones != null) {
                invitaciones.forEach(this::agregarInvitacion);
            }
        }
    }

    @Override
    public void agregarInvitacion(Invitacion invitacion) {
        if (invitacion != null && !this.invitaciones.contains(invitacion)) {
            this.invitaciones.add(invitacion);
            invitacion.setInvitacionesPorActo(this);
        }
    }

    @Override
    public void quitarInvitacion(Invitacion invitacion) {
        if (invitacion != null && this.invitaciones.contains(invitacion)) {
            this.invitaciones.remove(invitacion);
            invitacion.setInvitacionesPorActo(null);
        }
    }
}