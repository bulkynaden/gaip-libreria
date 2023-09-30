package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link Invitacion}.
 * Esta clase representa una invitación que tiene un tipo de zona específico, un número máximo de invitados permitidos,
 * y un conjunto de invitados asociados a ella. La invitación también está relacionada con un conjunto de invitaciones por acto.
 */
@Getter
@EqualsAndHashCode(of = {"tipoDeZona", "numeroMaximoInvitados"})
public class InvitacionImpl implements Invitacion {
    private final TipoDeZona tipoDeZona;
    private InvitacionesPorActo invitacionesPorActo;
    private int numeroMaximoInvitados;
    private Set<Invitado> invitados = new HashSet<>();

    /**
     * Constructor principal de la clase InvitacionImpl.
     *
     * @param tipoDeZona            Define el tipo de zona asociada a la invitación.
     * @param numeroMaximoInvitados Define el límite de invitados que pueden ser asociados a esta invitación.
     * @param invitacionesPorActo   Relación con las invitaciones por acto.
     */
    public InvitacionImpl(TipoDeZona tipoDeZona, int numeroMaximoInvitados, InvitacionesPorActo invitacionesPorActo) {
        this.tipoDeZona = tipoDeZona;
        this.numeroMaximoInvitados = numeroMaximoInvitados;
        this.invitacionesPorActo = invitacionesPorActo;
    }

    @Override
    public InvitacionesPorActo getInvitacionesPorActo() {
        return this.invitacionesPorActo;
    }

    /**
     * Establece la relación con las invitaciones por acto, manteniendo la coherencia bidireccional.
     *
     * @param invitacionesPorActo Las invitaciones por acto a asociar.
     */
    @Override
    public void setInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo) {
        if (this.invitacionesPorActo != invitacionesPorActo) {
            this.invitacionesPorActo = invitacionesPorActo;
            if (invitacionesPorActo != null && invitacionesPorActo.getInvitaciones().contains(this)) {
                invitacionesPorActo.quitarInvitacion(this);
            }
        }
    }

    /**
     * Establece los invitados para esta invitación, manteniendo la coherencia bidireccional.
     *
     * @param nuevosInvitados Conjunto de nuevos invitados.
     * @throws IllegalArgumentException Si la cantidad de invitados excede el límite actual.
     */
    @Override
    public void setInvitados(Set<Invitado> nuevosInvitados) {
        if (nuevosInvitados != null && nuevosInvitados.size() <= numeroMaximoInvitados) {
            for (Invitado invitadoActual : this.invitados) {
                invitadoActual.setInvitacion(null);
            }

            for (Invitado nuevoInvitado : nuevosInvitados) {
                nuevoInvitado.setInvitacion(this);
            }

            this.invitados = nuevosInvitados;

        } else {
            throw new IllegalArgumentException("La cantidad de invitados excede el límite actual.");
        }
    }

    /**
     * Agrega un invitado a la invitación, manteniendo la coherencia bidireccional.
     *
     * @param invitado Invitado a agregar.
     * @throws IllegalArgumentException Si el invitado es nulo o si se ha alcanzado el número máximo de invitados.
     */
    @Override
    public void agregarInvitado(Invitado invitado) {
        if (invitado != null) {
            if (this.invitados.size() < numeroMaximoInvitados) {
                this.invitados.add(invitado);
                invitado.setInvitacion(this);
            } else {
                throw new IllegalArgumentException("Se ha alcanzado el número máximo de invitados.");
            }
        } else {
            throw new IllegalArgumentException("El invitado no puede ser nulo.");
        }
    }

    /**
     * Quita un invitado de la invitación, rompiendo la relación bidireccional.
     *
     * @param invitado Invitado a quitar.
     * @throws IllegalArgumentException Si el invitado es nulo o si no está en la lista.
     */
    @Override
    public void quitarInvitado(Invitado invitado) {
        if (invitado != null) {
            if (this.invitados.contains(invitado)) {
                this.invitados.remove(invitado);
                invitado.setInvitacion(null);
            } else {
                throw new IllegalArgumentException("El invitado no está en la lista.");
            }
        } else {
            throw new IllegalArgumentException("El invitado no puede ser nulo.");
        }
    }

    /**
     * Aumenta el número máximo de invitados.
     *
     * @param cantidad Cantidad a aumentar.
     * @throws IllegalArgumentException Si la cantidad es negativa.
     */
    public void agregarNumeroMaximoInvitado(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        numeroMaximoInvitados += cantidad;
    }

    /**
     * Reduce el número máximo de invitados.
     *
     * @param cantidad Cantidad a reducir.
     * @throws IllegalArgumentException Si la cantidad es negativa o si la reducción excedería el límite actual.
     */
    public void quitarNumeroMaximoInvitado(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (getInvitados() != null && getInvitados().size() <= (getNumeroMaximoInvitados() - cantidad)) {
            this.numeroMaximoInvitados -= cantidad;
        } else {
            throw new IllegalArgumentException("No se puede reducir el número máximo de invitados ya que excedería el límite actual.");
        }
    }
}