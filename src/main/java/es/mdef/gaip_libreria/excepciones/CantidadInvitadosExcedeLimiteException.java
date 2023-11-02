package es.mdef.gaip_libreria.excepciones;

public class CantidadInvitadosExcedeLimiteException extends RuntimeException {
    public CantidadInvitadosExcedeLimiteException() {
        super("La cantidad de invitados excede el límite actual.");
    }
}