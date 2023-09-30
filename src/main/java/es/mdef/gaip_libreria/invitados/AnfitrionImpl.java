package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link Anfitrion}.
 * <p>
 * Esta clase representa a un anfitrión que tiene la capacidad de invitar a personas a un acto específico.
 * Un anfitrión está vinculado a una unidad de formación y tiene un conjunto de invitaciones por acto que ha extendido.
 * </p>
 *
 * @author [Tu Nombre o el de tu Organización]
 * @version 1.0
 */
@EqualsAndHashCode(of = {"unidadDeFormacion"}, callSuper = true)
@Getter
@Setter
public class AnfitrionImpl extends PersonaImpl implements Anfitrion {

    private String unidadDeFormacion;
    private Acto acto;
    private Set<InvitacionesPorActo> invitacionesPorActo = new HashSet<>();

    /**
     * Asocia un acto específico al anfitrión, manteniendo la integridad de la relación bidireccional.
     *
     * @param acto El acto a asociar con este anfitrión.
     */
    @Override
    public void setActo(Acto acto) {
        if (this.acto != acto) {
            if (this.acto != null) {
                this.acto.getAnfitriones().remove(this);
            }
            this.acto = acto;
            if (acto != null && !acto.getAnfitriones().contains(this)) {
                acto.agregarAnfitrion(this);
            }
        }
    }

    /**
     * Establece las invitaciones por acto para el anfitrión y mantiene la coherencia bidireccional.
     *
     * @param invitacionesPorActo Conjunto de invitaciones por acto.
     */
    @Override
    public void setInvitacionesPorActo(Set<InvitacionesPorActo> invitacionesPorActo) {
        if (this.invitacionesPorActo != invitacionesPorActo) {
            this.invitacionesPorActo.clear();
            if (invitacionesPorActo != null) {
                invitacionesPorActo.forEach(this::agregarInvitacionesPorActo);
            }
        }
    }

    /**
     * Agrega una invitación por acto al anfitrión y establece la relación bidireccional.
     *
     * @param invitacionPorActo La invitación por acto a agregar.
     */
    @Override
    public void agregarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo) {
        if (invitacionPorActo == null) {
            throw new IllegalArgumentException("La invitación por acto no puede ser nula.");
        }
        if (!invitacionesPorActo.contains(invitacionPorActo)) {
            invitacionesPorActo.add(invitacionPorActo);
            if (invitacionPorActo.getAnfitrion() != this) {
                invitacionPorActo.setAnfitrion(this);
            }
        }
    }

    /**
     * Elimina una invitación por acto del anfitrión y rompe la relación bidireccional.
     *
     * @param invitacionPorActo La invitación por acto a eliminar.
     */
    @Override
    public void quitarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo) {
        if (invitacionPorActo == null) {
            throw new IllegalArgumentException("La invitación por acto no puede ser nula.");
        }
        if (invitacionesPorActo.contains(invitacionPorActo)) {
            invitacionesPorActo.remove(invitacionPorActo);
            if (invitacionPorActo.getAnfitrion() == this) {
                invitacionPorActo.setAnfitrion(null);
            }
        }
    }
}