package es.mdef.gaip_libreria.zonas_configuradas;

import java.util.List;

/**
 * Clase de utilidad para operaciones relacionadas con las zonas configuradas.
 * Esta clase proporciona métodos estáticos para facilitar la manipulación de zonas configuradas.
 * No está destinada a ser instanciada.
 */
public final class ZonasConfiguradasHelper {

    /**
     * Constructor privado para prevenir la instanciación de esta clase de utilidad.
     */
    private ZonasConfiguradasHelper() {
    }

    /**
     * Obtiene una lista de localidades configuradas ordenadas por su número.
     * Utiliza el método {@link LocalidadesConfiguradasHelper#compararLocalidadesPorNumero} para determinar el orden.
     *
     * @param zona La {@link ZonaConfigurada} de la cual se obtendrán las localidades.
     * @return Una lista de {@link LocalidadConfigurada} ordenadas según su número.
     * @throws NullPointerException si la zona proporcionada es {@code null} o si alguna de las localidades es {@code null}.
     */
    public static List<LocalidadConfigurada> getLocalidadesOrdenadasPorNumero(ZonaConfigurada zona) {
        return zona.getLocalidades().stream()
                .sorted(LocalidadesConfiguradasHelper::compararLocalidadesPorNumero)
                .toList();
    }
}