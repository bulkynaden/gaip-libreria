package es.mdef.gaip_libreria.excepciones;

public class SinSolucionException extends RuntimeException {
    public SinSolucionException() {
        super("No se ha encontrado solución óptima");
    }
}
