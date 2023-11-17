package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa los diferentes estados posibles de una localidad.
 */
public enum EstadoLocalidad implements EstadoDeUnaLocalidad {
    
    /**
     * Estado normal de una localidad, disponible para su uso o asignación.
     */
    NORMAL,

    /**
     * Estado de una localidad que ha sido reservada para uso protocolario.
     */
    RESERVADA,

    /**
     * Estado de una localidad que está bloqueada, no disponible para uso o asignación.
     */
    BLOQUEADA
}