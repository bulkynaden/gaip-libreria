package es.mdef.gaip_libreria.excepciones;

/**
 * Excepción lanzada cuando la cantidad de invitados supera el límite establecido para un acto.
 * Esta excepción es una {@link RuntimeException} y, por lo tanto, no necesita ser capturada o declarada en un método.
 */
public class CantidadInvitadosExcedeLimiteException extends RuntimeException {

    /**
     * Construye una nueva excepción con un mensaje de error predeterminado.
     */
    public CantidadInvitadosExcedeLimiteException() {
        super("La cantidad excede el límite actual.");
    }

    /**
     * Construye una nueva excepción con un mensaje de error personalizado.
     *
     * @param mensaje El mensaje de error que se mostrará.
     */
    public CantidadInvitadosExcedeLimiteException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye una nueva excepción con un mensaje de error personalizado y una causa.
     *
     * @param mensaje El mensaje de error que se mostrará.
     * @param causa   La causa de la excepción (otra excepción que la provocó).
     */
    public CantidadInvitadosExcedeLimiteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}