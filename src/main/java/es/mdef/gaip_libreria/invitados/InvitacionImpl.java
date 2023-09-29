package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa una invitación que tiene un tipo de zona, un número máximo de invitados y un conjunto de invitados.
 */
@Getter
@EqualsAndHashCode(of = {"tipoDeZona", "numeroMaximoInvitados"})
public class InvitacionImpl implements Invitacion {
    private final TipoDeZona tipoDeZona;
    private Anfitrion anfitrion;
    private int numeroMaximoInvitados;
    private Set<Invitado> invitados = new HashSet<>();

    /**
     * Constructor de la clase.
     *
     * @param tipoDeZona            Tipo de zona de la invitación.
     * @param numeroMaximoInvitados Número máximo de invitados permitidos.
     * @param anfitrion             Anfitrión que realiza la invitación.
     */
    public InvitacionImpl(TipoDeZona tipoDeZona, int numeroMaximoInvitados, Anfitrion anfitrion) {
        this.tipoDeZona = tipoDeZona;
        this.numeroMaximoInvitados = numeroMaximoInvitados;
        this.anfitrion = anfitrion;
    }

    /**
     * Establece el anfitrion asociado a esta invitación y maneja la relación bidireccional.
     * Si la invitación ya estaba asociado a un anfitrion, se elimina de ese anfitrion antes de agregarla al nuevo.
     *
     * @param anfitrion El anfitrion a asociar con esta invitación.
     */
    public void setAnfitrion(Anfitrion anfitrion) {
        if (this.anfitrion != anfitrion) {
            if (this.anfitrion != null) {
                this.anfitrion.getInvitaciones().remove(this);
            }
            this.anfitrion = anfitrion;
            this.getInvitados().forEach(this::quitarInvitado);
            if (anfitrion != null) {
                anfitrion.getInvitaciones().add(this);
            }
        }
    }

    /**
     * Establece los invitados para esta invitación.
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
     * Agrega un invitado a la invitación.
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
     * Quita un invitado de la invitación.
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