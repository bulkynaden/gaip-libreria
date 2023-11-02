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
@EqualsAndHashCode(of = {"tipoDeZona", "numeroMaximoInvitados"})
public class InvitacionImpl implements Invitacion {
    private final TipoDeZona tipoDeZona;
    private final Set<Invitado> invitados = new HashSet<>();
    private final Set<Coche> coches = new HashSet<>();
    private InvitacionesPorActo invitacionesPorActo;
    private int numeroMaximoInvitados;

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
     * Establece los invitados para esta invitación, manteniendo la coherencia bidireccional.
     *
     * @param nuevosInvitados Conjunto de nuevos invitados.
     */
    @Override
    public void setInvitados(Set<Invitado> nuevosInvitados, boolean superarMaximo) {
        if (nuevosInvitados != null && nuevosInvitados.size() <= numeroMaximoInvitados) {
            for (Invitado invitadoActual : this.invitados) {
                invitadoActual.setInvitacion(null, superarMaximo);
            }

            for (Invitado nuevoInvitado : nuevosInvitados) {
                nuevoInvitado.setInvitacion(this, superarMaximo);
            }
            nuevosInvitados.forEach(invitado -> this.agregarInvitado(invitado, superarMaximo));

        } else {
            throw new CantidadInvitadosExcedeLimiteException();
        }
    }

    /**
     * Establece los coches para esta invitación, manteniendo la coherencia bidireccional.
     *
     * @param nuevosCoches Conjunto de nuevos invitados.
     */
    @Override
    public void setCoches(Set<Coche> nuevosCoches, boolean superarMaximo) {
        if (nuevosCoches != null && nuevosCoches.size() <= numeroMaximoInvitados) {
            for (Coche coche : this.coches) {
                coche.setInvitacion(null, superarMaximo);
            }

            for (Coche coche : nuevosCoches) {
                coche.setInvitacion(this, superarMaximo);
            }
            coches.forEach(coche -> this.agregarCoche(coche, superarMaximo));

        } else {
            throw new CantidadInvitadosExcedeLimiteException();
        }
    }

    /**
     * Agrega un invitado a la invitación, manteniendo la coherencia bidireccional.
     *
     * @param invitado Invitado a agregar.
     * @throws IllegalArgumentException Si el invitado es nulo o si se ha alcanzado el número máximo de invitados.
     */
    @Override
    public void agregarInvitado(Invitado invitado, boolean superarMaximo) {
        if (invitado != null && !this.invitados.contains(invitado)) {
            if (getTipoDeZona() == TipoDeZona.TRIBUNA && this.invitados.size() >= numeroMaximoInvitados && !superarMaximo) {
                throw new CantidadInvitadosExcedeLimiteException();
            }
            this.invitados.add(invitado);
            invitado.setInvitacion(this, superarMaximo);
        }
    }

    /**
     * Quita un invitado de la invitación, rompiendo la relación bidireccional.
     *
     * @param invitado Invitado a quitar.
     */
    @Override
    public void quitarInvitado(Invitado invitado) {
        if (this.invitados.contains(invitado)) {
            this.invitados.remove(invitado);
            if (invitado != null) {
                invitado.setInvitacion(null, false);
            }
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

    /**
     * Agrega un coche a la invitación, manteniendo la coherencia bidireccional.
     *
     * @param coche Coche a agregar.
     */
    @Override
    public void agregarCoche(Coche coche, boolean superarMaximo) {
        if (coche != null && !this.coches.contains(coche)) {
            if (getTipoDeZona() == TipoDeZona.PARKING && this.coches.size() >= numeroMaximoInvitados && !superarMaximo) {
                throw new CantidadInvitadosExcedeLimiteException();
            }
            this.coches.add(coche);
            coche.setInvitacion(this, superarMaximo);
        }
    }

    /**
     * Quita un coche de la invitación, rompiendo la relación bidireccional.
     *
     * @param coche Coche a quitar.
     */
    @Override
    public void quitarCoche(Coche coche) {
        if (this.coches.contains(coche)) {
            this.coches.remove(coche);
            if (coche != null) {
                coche.setInvitacion(null, false);
            }
        }
    }
}