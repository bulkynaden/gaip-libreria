package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link InvitacionesPorActo}.
 * Esta clase representa las invitaciones extendidas por un anfitrión para un acto específico.
 * Utiliza Lombok para generar automáticamente los métodos getters.
 */
@Getter
public class InvitacionesPorActoImpl implements InvitacionesPorActo {
    private final Set<Invitacion> invitaciones = new HashSet<>();
    private Acto acto;
    private Anfitrion anfitrion;

    /**
     * Constructor que inicializa una nueva instancia de InvitacionesPorActoImpl con un acto y anfitrión específicos.
     *
     * @param acto      El acto para el cual se extenderán las invitaciones.
     * @param anfitrion El anfitrión que extiende las invitaciones.
     */
    public InvitacionesPorActoImpl(Acto acto, Anfitrion anfitrion) {
        this.acto = acto;
        this.anfitrion = anfitrion;
    }

    /**
     * Asigna un acto a esta colección de invitaciones, actualizando la relación bidireccional
     * si es necesario.
     *
     * @param acto El acto a asociar con estas invitaciones.
     */
    @Override
    public void setActo(Acto acto) {
        if (this.acto != acto) {
            if (this.acto != null && this.acto.getInvitacionesPorActo().contains(this)) {
                this.acto.quitarInvitacionesPorActo(this);
            }
            this.acto = acto;
            if (this.acto != null && !this.acto.getInvitacionesPorActo().contains(this)) {
                this.acto.agregarInvitacionesPorActo(this);
            }
        }
    }

    /**
     * Asigna un anfitrión a esta colección de invitaciones, actualizando la relación bidireccional
     * si es necesario.
     *
     * @param anfitrion El anfitrión a asociar con estas invitaciones.
     */
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

    /**
     * Establece el conjunto de invitaciones para esta colección, actualizando cada invitación
     * para reflejar esta asociación.
     *
     * @param invitaciones El conjunto de invitaciones a asignar.
     */
    @Override
    public void setInvitaciones(Set<Invitacion> invitaciones) {
        if (this.invitaciones != invitaciones) {
            this.invitaciones.clear();
            if (invitaciones != null) {
                invitaciones.forEach(this::agregarInvitacion);
            }
        }
    }

    /**
     * Agrega una invitación al conjunto de invitaciones. Si la invitación no está ya presente, la añade
     * y actualiza su referencia de {@link InvitacionesPorActo}.
     *
     * @param invitacion La invitación a agregar.
     */
    @Override
    public void agregarInvitacion(Invitacion invitacion) {
        if (invitacion != null && !this.invitaciones.contains(invitacion)) {
            this.invitaciones.add(invitacion);
            invitacion.setInvitacionesPorActo(this);
        }
    }

    /**
     * Elimina una invitación del conjunto de invitaciones. Si la invitación está presente, la elimina
     * y actualiza su referencia de {@link InvitacionesPorActo} a null.
     *
     * @param invitacion La invitación a eliminar.
     */
    @Override
    public void quitarInvitacion(Invitacion invitacion) {
        if (invitacion != null && this.invitaciones.contains(invitacion)) {
            this.invitaciones.remove(invitacion);
            invitacion.setInvitacionesPorActo(null);
        }
    }
}