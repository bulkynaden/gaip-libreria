package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa las diferentes acciones que pueden realizarse en un acto.
 */
public enum AccionesActo {
    /**
     * Acción para agregar anfitriones a un acto.
     */
    AGREGAR_ANFITRIONES,

    /**
     * Acción para agregar invitados a un acto.
     */
    AGREGAR_INVITADOS,

    /**
     * Acción para enviar un correo electrónico con un formulario para agregar invitados.
     */
    ENVIAR_CORREO_FORMULARIO,

    /**
     * Acción para configurar las zonas de un acto.
     */
    CONFIGURAR_ZONAS,

    /**
     * Acción para configurar las prioridades dentro de un acto.
     */
    CONFIGURAR_PRIORIDADES,

    /**
     * Acción para configurar la distribución de las invitaciones de un acto.
     */
    CONFIGURAR_DISTRIBUCION,

    /**
     * Acción para borrar anfitriones de un acto.
     */
    BORRAR_ANFITRIONES,

    /**
     * Acción para asignar asientos a los invitados en un acto.
     */
    SENTAR_INVITADOS
}