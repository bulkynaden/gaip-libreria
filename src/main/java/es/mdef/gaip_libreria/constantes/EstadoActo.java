package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa los diferentes estados por los que puede pasar un acto.
 */
public enum EstadoActo {
    
    /**
     * El acto está en fase de creación.
     */
    CREACION,

    /**
     * El acto está en fase de registro, donde se pueden registrar los invitados.
     */
    REGISTRO,

    /**
     * El acto está en la fase de asignación de asientos a los invitados.
     */
    SENTANDO_INVITADOS,

    /**
     * El acto está siendo validado.
     */
    VALIDACION,

    /**
     * El acto está en fase de control.
     */
    CONTROL,

    /**
     * El acto ha finalizado.
     */
    FINALIZADO
}