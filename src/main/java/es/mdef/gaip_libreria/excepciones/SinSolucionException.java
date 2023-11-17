package es.mdef.gaip_libreria.excepciones;

/**
 * Excepción lanzada cuando no se encuentra una solución óptima o adecuada en un determinado proceso o cálculo.
 * Esta excepción es una {@link RuntimeException}, lo que implica que es una excepción no comprobada.
 */
public class SinSolucionException extends RuntimeException {

    /**
     * Construye una nueva excepción con un mensaje de error predeterminado.
     */
    public SinSolucionException() {
        super("No se ha encontrado solución óptima");
    }

    /**
     * Construye una nueva excepción con un mensaje de error personalizado.
     *
     * @param mensaje El mensaje de error que se mostrará.
     */
    public SinSolucionException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye una nueva excepción con un mensaje de error personalizado y una causa.
     *
     * @param mensaje El mensaje de error que se mostrará.
     * @param causa   La causa de la excepción (otra excepción que la provocó).
     */
    public SinSolucionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}