package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.localidades.LocalidadNumerada;
import es.mdef.gaip_libreria.utilidades.HibernateProxyHelper;

/**
 * Clase de utilidad para operaciones relacionadas con {@link LocalidadConfigurada}.
 * Esta clase proporciona métodos estáticos para facilitar la manipulación y comparación de localidades configuradas.
 * No está destinada a ser instanciada.
 */
public class LocalidadesConfiguradasHelper {
    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private LocalidadesConfiguradasHelper() {
    }

    /**
     * Compara dos localidades configuradas por su número.
     * <p>
     * Este método intenta obtener las entidades {@link LocalidadNumerada} asociadas con las localidades configuradas
     * y compara sus números. Se espera que las localidades configuradas sean instancias de {@link LocalidadNumerada}.
     * </p>
     *
     * @param localidad1 la primera localidad configurada para comparar.
     * @param localidad2 la segunda localidad configurada para comparar.
     * @return un valor negativo si {@code localidad1} es menor que {@code localidad2},
     * un valor positivo si es mayor, y 0 si son iguales.
     * Retorna -1 si ocurre una excepción, indicando un error en la comparación.
     * @throws ClassCastException si las localidades configuradas no son instancias de {@link LocalidadNumerada}.
     */
    public static int compararLocalidadesPorNumero(LocalidadConfigurada localidad1, LocalidadConfigurada localidad2) {
        try {
            LocalidadNumerada localidadNumerada1 = (LocalidadNumerada) HibernateProxyHelper.getEntity(localidad1.getLocalidad());
            LocalidadNumerada localidadNumerada2 = (LocalidadNumerada) HibernateProxyHelper.getEntity(localidad2.getLocalidad());

            return Integer.compare(localidadNumerada1.getNumero(), localidadNumerada2.getNumero());
        } catch (Exception ignored) {
        }
        return -1;
    }
}