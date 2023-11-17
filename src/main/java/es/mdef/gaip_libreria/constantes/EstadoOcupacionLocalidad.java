package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa los estados de ocupación de una localidad.
 */
public enum EstadoOcupacionLocalidad implements EstadoDeUnaLocalidad {
    /**
     * Indica que la localidad está libre y disponible para ser ocupada.
     */
    LIBRE,

    /**
     * Indica que la localidad está actualmente ocupada.
     */
    OCUPADA
}