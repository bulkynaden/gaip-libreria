package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa las distintas etapas en el proceso de creación de un acto.
 */
public enum EstadoCreacion {
    
    /**
     * Etapa para el ingreso o modificación de los datos generales del acto.
     */
    DATOS_GENERALES,

    /**
     * Etapa para la carga o gestión de los anfitriones asociados al acto.
     */
    CARGA_ANFITRIONES,

    /**
     * Etapa para definir y configurar las zonas del acto.
     */
    ZONAS,

    /**
     * Etapa para establecer las prioridades.
     */
    PRIORIDADES,

    /**
     * Etapa para configurar la distribución de las invitaciones del acto.
     */
    DISTRIBUCION
}