package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

/**
 * Representa las invitaciones extendidas por un anfitrión para un acto específico.
 */
public interface InvitacionesPorActo {

    /**
     * Obtiene el acto para el cual se han extendido las invitaciones.
     *
     * @return el {@link Acto} asociado a las invitaciones.
     */
    Acto getActo();

    /**
     * Asigna o establece el acto para el cual se extenderán las invitaciones.
     *
     * @param acto el {@link Acto} a asociar a las invitaciones.
     */
    void setActo(Acto acto);

    /**
     * Obtiene el anfitrión que ha extendido las invitaciones para el acto.
     *
     * @return el {@link Anfitrion} que ha extendido las invitaciones.
     */
    Anfitrion getAnfitrion();

    /**
     * Asigna o establece el anfitrión que ha extendido o extenderá las invitaciones para el acto.
     *
     * @param anfitrion el {@link Anfitrion} a asociar a las invitaciones.
     */
    void setAnfitrion(Anfitrion anfitrion);

    /**
     * Obtiene el conjunto de invitaciones que el anfitrión ha extendido para el acto.
     *
     * @return un conjunto de {@link Invitacion}s asociadas al acto y al anfitrión.
     */
    Set<Invitacion> getInvitaciones();

    /**
     * Asigna o establece un conjunto específico de invitaciones que el anfitrión ha extendido para el acto.
     *
     * @param invitaciones el conjunto de {@link Invitacion}s a asignar.
     */
    void setInvitaciones(Set<Invitacion> invitaciones);

    /**
     * Agrega una invitación específica al conjunto de invitaciones que el anfitrión ha extendido para el acto.
     *
     * @param invitacion la {@link Invitacion} a agregar.
     */
    void agregarInvitacion(Invitacion invitacion);

    /**
     * Elimina o quita una invitación específica del conjunto de invitaciones que el anfitrión ha extendido para el acto.
     *
     * @param invitacion la {@link Invitacion} a quitar.
     */
    void quitarInvitacion(Invitacion invitacion);
}