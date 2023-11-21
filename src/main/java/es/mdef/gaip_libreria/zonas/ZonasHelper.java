package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.localidades.LocalidadesHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad para operaciones relacionadas con las zonas.
 * Proporciona métodos estáticos para facilitar la manipulación y consulta de zonas y sus localidades.
 * Esta clase no está destinada a ser instanciada.
 */
public final class ZonasHelper {

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private ZonasHelper() {
    }

    /**
     * Obtiene una lista de localidades ordenadas por número de una zona específica.
     * Si la zona es nula o no tiene localidades, retorna una lista vacía.
     *
     * @param zona La {@link Zona} de la cual obtener las localidades ordenadas.
     * @return Una lista de {@link Localidad} ordenadas según su número, o una lista vacía si la zona es nula o no tiene localidades.
     * @throws NullPointerException si el conjunto de localidades contiene alguna localidad nula.
     */
    public static List<Localidad> getLocalidadesOrdenadasPorNumero(Zona zona) {
        if (zona == null || zona.getLocalidades() == null) {
            return new ArrayList<>();
        }
        return zona.getLocalidades().stream()
                .sorted(LocalidadesHelper::compararLocalidadesPorNumero)
                .toList();
    }
}