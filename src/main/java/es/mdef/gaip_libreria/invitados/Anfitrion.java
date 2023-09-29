package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

/**
 * Representa un anfitrión que puede invitar a personas a un acto.
 * Un anfitrión está asociado a una unidad de formación y tiene un conjunto de invitaciones.
 */
public interface Anfitrion extends Persona {

    /**
     * Obtiene el acto asociado al anfitrión.
     *
     * @return el acto asociado al anfitrión.
     */
    Acto getActo();

    /**
     * Establece el acto asociado al anfitrión.
     *
     * @param acto el acto a asociar.
     */
    void setActo(Acto acto);

    /**
     * Obtiene la unidad de formación asociada al anfitrión.
     *
     * @return la unidad de formación del anfitrión.
     */
    String getUnidadDeFormacion();

    /**
     * Establece la unidad de formación asociada al anfitrión.
     *
     * @param unidadDeFormacion la unidad de formación a establecer.
     */
    void setUnidadDeFormacion(String unidadDeFormacion);

    /**
     * Obtiene el conjunto de invitaciones asociadas al anfitrión.
     *
     * @return un conjunto de invitaciones.
     */
    Set<Invitacion> getInvitaciones();

    /**
     * Establece el conjunto de invitaciones asociadas al anfitrión.
     *
     * @param invitaciones el conjunto de invitaciones a establecer.
     */
    void setInvitaciones(Set<Invitacion> invitaciones);

    /**
     * Agrega una invitación al conjunto de invitaciones del anfitrión.
     *
     * @param invitacion la invitación a agregar.
     */
    void agregarInvitacion(Invitacion invitacion);

    /**
     * Quita una invitación del conjunto de invitaciones del anfitrión.
     *
     * @param invitacion la invitación a quitar.
     */
    void quitarInvitacion(Invitacion invitacion);
}