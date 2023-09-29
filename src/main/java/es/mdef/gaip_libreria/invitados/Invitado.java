package es.mdef.gaip_libreria.invitados;

/**
 * Representa a un invitado que ha sido invitado a un acto por un {@link Anfitrion} a través de una {@link Invitacion}.
 * Un invitado tiene asociado un parentesco que describe su relación o conexión con el anfitrión o el acto.
 */
public interface Invitado extends Persona {

    /**
     * Obtiene la invitación asociada a este invitado.
     *
     * @return la invitación asociada.
     */
    Invitacion getInvitacion();

    /**
     * Establece la invitación asociada a este invitado.
     *
     * @param invitacion la invitación a establecer.
     */
    void setInvitacion(Invitacion invitacion);

    /**
     * Obtiene el parentesco del invitado, que describe su relación o conexión con el anfitrión o el acto.
     *
     * @return el parentesco del invitado.
     */
    String getParentesco();

    /**
     * Establece el parentesco del invitado.
     *
     * @param parentesco el parentesco a establecer.
     */
    void setParentesco(String parentesco);
}