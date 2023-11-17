package es.mdef.gaip_libreria.invitados;

/**
 * Interfaz que define las características y comportamientos específicos de un invitado perteneciente a las fuerzas y cuerpos de seguridad del estado (FCSE).
 */
public interface InvitadoFcse extends PersonaFcse {

    /**
     * Determina si el invitado asiste al evento en uniforme.
     *
     * @return true si el invitado asiste en uniforme, false en caso contrario.
     */
    boolean getAsisteDeUniforme();

    /**
     * Establece si el invitado debe asistir al evento en uniforme.
     *
     * @param asiste true para indicar que el invitado asistirá en uniforme, false en caso contrario.
     */
    void setAsisteDeUniforme(boolean asiste);

    /**
     * Determina si el invitado requiere vestuario para el evento.
     *
     * @return true si el invitado requiere vestuario, false en caso contrario.
     */
    boolean getRequiereVestuario();

    /**
     * Establece si el invitado requiere vestuario para el evento.
     *
     * @param requiereVestuario true para indicar que el invitado requiere vestuario, false en caso contrario.
     */
    void setRequiereVestuario(boolean requiereVestuario);

    /**
     * Determina si al invitado entregará un nombramiento durante el evento.
     *
     * @return true si al invitado entregará un nombramiento, false en caso contrario.
     */
    boolean getEntregaNombramiento();

    /**
     * Establece si al invitado entregará un nombramiento durante el evento.
     *
     * @param entregaNombramiento true para indicar que al invitado entregará un nombramiento, false en caso contrario.
     */
    void setEntregaNombramiento(boolean entregaNombramiento);

    /**
     * Obtiene el empleo del invitado dentro de las FCSE.
     *
     * @return el empleo del invitado.
     */
    Empleo getEmpleo();

    /**
     * Establece el empleo del invitado dentro de las FCSE.
     *
     * @param empleo el empleo a asignar al invitado.
     */
    void setEmpleo(Empleo empleo);
}