package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
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
    private final Set<Acto> actos = new HashSet<>();
    private final Set<InvitacionesPorActo> invitacionesPorActo = new HashSet<>();
    private String unidadDeFormacion;

    /**
     * Establece los actos asociados al anfitrión, manteniendo la integridad de la relación bidireccional.
     *
     * @param actos El conjunto de actos a asociar con este anfitrión.
     */
    @Override
    public void setActos(Set<Acto> actos) {
        this.actos.forEach(e -> e.quitarAnfitrion(this));
        this.actos.clear();
        if (actos != null) {
            actos.forEach(this::agregarActo);
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

    /**
     * Agrega un acto al anfitrión, manteniendo la integridad de la relación bidireccional.
     *
     * @param acto El acto a asociar con este anfitrión.
     */
    @Override
    public void agregarActo(Acto acto) {
        if (acto == null) {
            throw new IllegalArgumentException("El acto no puede ser nulo.");
        }
        if (!actos.contains(acto)) {
            actos.add(acto);
            if (!acto.getAnfitriones().contains(this)) {
                acto.agregarAnfitrion(this);
            }
        }
    }

    /**
     * Elimina un acto del anfitrión, manteniendo la integridad de la relación bidireccional.
     *
     * @param acto El acto a desasociar de este anfitrión.
     */
    @Override
    public void quitarActo(Acto acto) {
        if (acto == null) {
            throw new IllegalArgumentException("El acto no puede ser nulo.");
        }
        if (actos.contains(acto)) {
            actos.remove(acto);
            if (acto.getAnfitriones() != null && acto.getAnfitriones().contains(this)) {
                acto.quitarAnfitrion(this);
            }
        }
    }

    @Override
    public int compararPorCantidadDeInvitadosDeUnTipoDeZona(Acto acto, TipoDeZona tipo, Anfitrion anfitrion1, Anfitrion anfitrion2) {
        long thisCount = this.getNumeroInvitadosDeUnActoPorZona(acto, tipo);
        long otherCount = anfitrion2.getNumeroInvitadosDeUnActoPorZona(acto, tipo);
        return Long.compare(otherCount, thisCount);
    }

    @Override
    public int compareTo(Anfitrion o) {
        return this.unidadDeFormacion.compareTo(o.getUnidadDeFormacion());
    }
}