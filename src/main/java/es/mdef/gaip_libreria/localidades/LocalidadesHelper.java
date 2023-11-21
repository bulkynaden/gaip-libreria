package es.mdef.gaip_libreria.localidades;

/**
 * Clase de utilidad para operaciones relacionadas con localidades.
 * Proporciona métodos estáticos para facilitar la manipulación y comparación de localidades.
 * Esta clase no está diseñada para ser instanciada.
 */
public final class LocalidadesHelper {

    /**
     * Constructor privado para prevenir la instanciación de la clase.
     */
    private LocalidadesHelper() {
    }

    /**
     * Compara dos localidades por su número.
     * Asume que ambas localidades son instancias de {@link LocalidadNumerada} y compara sus números.
     * Si alguna de las localidades no es una instancia de {@link LocalidadNumerada} o si ocurre un error durante la comparación, se retorna -1.
     *
     * @param localidad1 La primera localidad para comparar.
     * @param localidad2 La segunda localidad para comparar.
     * @return un valor negativo si {@code localidad1} es menor que {@code localidad2},
     * un valor positivo si es mayor, y 0 si son iguales.
     * Retorna -1 si ocurre un error o si las localidades no son instancias de {@link LocalidadNumerada}.
     */
    public static int compararLocalidadesPorNumero(Localidad localidad1, Localidad localidad2) {
        try {
            return Integer.compare(((LocalidadNumerada) localidad1).getNumero(), ((LocalidadNumerada) localidad2).getNumero());
        } catch (Exception ignored) {
        }
        return -1;
    }
}