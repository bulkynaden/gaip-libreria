package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa los diferentes cuerpos de fuerzas y servicios de seguridad.
 * <p>
 * Las opciones incluyen:
 * <ul>
 *     <li>AIRE - Representa el Ejército del Aire y del Espacio.</li>
 *     <li>TIERRA - Representa el Ejército de Tierra.</li>
 *     <li>ARMADA - Representa La Armada.</li>
 *     <li>CNP - Representa la Policía Nacional.</li>
 * </ul>
 * </p>
 */
public enum CuerpoFcse {
    AIRE, TIERRA, ARMADA, CNP;

    /**
     * Convierte una cadena de texto al valor enum correspondiente.
     * La comparación es insensible a mayúsculas y minúsculas.
     *
     * @param valor La cadena de texto que se desea convertir.
     * @return El valor de {@link CuerpoFcse} correspondiente o null si no hay correspondencia.
     */
    public static CuerpoFcse delValor(String valor) {
        return CuerpoFcse.valueOf(valor.toUpperCase().trim());
    }
}