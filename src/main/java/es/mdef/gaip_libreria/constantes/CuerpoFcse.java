package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que representa los diferentes cuerpos de fuerzas y servicios de seguridad.
 * <p>
 * Las opciones incluyen:
 * <ul>
 *     <li>AIRE - Representa el Ejército del Aire y del Espacio.</li>
 *     <li>TIERRA - Representa el Ejército de Tierra.</li>
 *     <li>ARMADA - Representa La Armada.</li>
 *     <li>GC - Representa la Guardia Civil</li>
 *     <li>CUERPOS_COMUNES - Representa los Cuerpos Comunes.</li>
 *     <li>CNP - Representa la Policía Nacional.</li>
 *     <li>EJERCITO_EXTRANJERO - Representa un ejército extranjero</li>
 *     <li>OTROS</li>
 * </ul>
 * </p>
 */
public enum CuerpoFcse {
    AIRE, TIERRA, ARMADA, GC, CUERPOS_COMUNES, CNP, EJERCITO_EXTRANJERO, OTROS;

    /**
     * Convierte una cadena de texto al valor enum correspondiente.
     * La comparación es insensible a mayúsculas y minúsculas. Devuelve null si no hay correspondencia.
     *
     * @param valor La cadena de texto que se desea convertir.
     * @return El valor de {@link CuerpoFcse} correspondiente o null si no hay correspondencia.
     */
    public static CuerpoFcse delValor(String valor) {
        try {
            return CuerpoFcse.valueOf(valor.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}