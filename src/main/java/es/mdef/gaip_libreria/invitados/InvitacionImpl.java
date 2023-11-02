package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.excepciones.CantidadInvitadosExcedeLimiteException;
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
@EqualsAndHashCode(of = {"tipoDeZona", "numeroMaximoAsignables"})
public class InvitacionImpl implements Invitacion {
    private final TipoDeZona tipoDeZona;
    private final Set<Asignable> asignables = new HashSet<>();
    private int numeroMaximoAsignables;
    private InvitacionesPorActo invitacionesPorActo;

    /**
     * Constructor principal de la clase InvitacionImpl.
     *
     * @param tipoDeZona            Define el tipo de zona asociada a la invitación.
     * @param numeroMaximoInvitados Define el límite de invitados que pueden ser asociados a esta invitación.
     * @param invitacionesPorActo   Relación con las invitaciones por acto.
     */
    public InvitacionImpl(TipoDeZona tipoDeZona, int numeroMaximoInvitados, InvitacionesPorActo invitacionesPorActo) {
        this.tipoDeZona = tipoDeZona;
        this.numeroMaximoAsignables = numeroMaximoInvitados;
        setInvitacionesPorActo(invitacionesPorActo);
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
            if (invitacionesPorActo != null && !invitacionesPorActo.getInvitaciones().contains(this)) {
                invitacionesPorActo.agregarInvitacion(this);
            }
        }
    }

    /**
     * Establece los asignables para esta invitación, manteniendo la coherencia bidireccional.
     *
     * @param nuevosAsignables Conjunto de nuevos asignables.
     * @throws IllegalArgumentException Si la cantidad de asignables excede el límite actual.
     */
    @Override
    public void setAsignables(Set<Asignable> nuevosAsignables, boolean superarMaximo) {
        if (nuevosAsignables != null && nuevosAsignables.size() <= numeroMaximoAsignables) {
            for (Asignable asignable : this.asignables) {
                asignable.setInvitacion(null, superarMaximo);
            }

            for (Asignable nuevoAsignable : nuevosAsignables) {
                nuevoAsignable.setInvitacion(this, superarMaximo);
            }
            nuevosAsignables.forEach(invitado -> this.agregarAsignable(invitado, superarMaximo));

        } else {
            throw new CantidadInvitadosExcedeLimiteException();
        }
    }

    /**
     * Agrega un asignable a la invitación, manteniendo la coherencia bidireccional.
     *
     * @param asignable Asignable a agregar.
     * @throws IllegalArgumentException Si el invitado es nulo o si se ha alcanzado el número máximo de invitados.
     */
    @Override
    public void agregarAsignable(Asignable asignable, boolean superarMaximo) {
        if (asignable != null && !this.asignables.contains(asignable)) {
            if (getTipoDeZona() == TipoDeZona.TRIBUNA && this.asignables.size() >= numeroMaximoAsignables && !superarMaximo) {
                throw new IllegalArgumentException("Se ha alcanzado el número máximo de invitados.");
            }
            this.asignables.add(asignable);
            asignable.setInvitacion(this, superarMaximo);
        }
    }

    /**
     * Quita un asignable de la invitación, rompiendo la relación bidireccional.
     *
     * @param asignable Asignable a quitar.
     * @throws IllegalArgumentException Si el invitado es nulo o si no está en la lista.
     */
    @Override
    public void quitarAsignable(Asignable asignable) {
        if (this.asignables.contains(asignable)) {
            this.asignables.remove(asignable);
            if (asignable != null) {
                asignable.setInvitacion(null, false);
            }
        }
    }

    /**
     * Aumenta el número máximo de invitados.
     *
     * @param cantidad Cantidad a aumentar.
     * @throws IllegalArgumentException Si la cantidad es negativa.
     */
    public void agregarNumeroMaximoAsignables(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        numeroMaximoAsignables += cantidad;
    }

    /**
     * Reduce el número máximo de asignables.
     *
     * @param cantidad Cantidad a reducir.
     * @throws IllegalArgumentException Si la cantidad es negativa o si la reducción excedería el límite actual.
     */
    public void quitarNumeroMaximoAsignables(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (getAsignables() != null && getAsignables().size() <= (getNumeroMaximoAsignables() - cantidad)) {
            this.numeroMaximoAsignables -= cantidad;
        } else {
            throw new IllegalArgumentException("No se puede reducir el número máximo de asignables ya que excedería el límite actual.");
        }
    }
}