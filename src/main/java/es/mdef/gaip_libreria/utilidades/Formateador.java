package es.mdef.gaip_libreria.utilidades;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

/**
 * Clase utilitaria Formateador para formatear nombres.
 * <p>
 * Esta clase proporciona métodos para formatear nombres de manera que las palabras en el nombre
 * estén capitalizadas adecuadamente, respetando ciertos prefijos como "del", "de la", etc.
 */
public final class Formateador {

    /**
     * Conjunto de prefijos que no deben ser capitalizados.
     */
    private static final Set<String> PREFIXES = Set.of("del", "de", "los", "las", "la", "al", "el", "ibn", "bin", "bint");

    /**
     * Constructor privado para evitar la instanciación de esta clase utilitaria.
     */
    private Formateador() {
        // Clase utilitaria no instanciable
    }

    /**
     * Formatea el nombre proporcionado, capitalizando las palabras pero manteniendo
     * ciertos prefijos en minúsculas.
     *
     * @param texto El nombre o texto a formatear.
     * @return El nombre formateado con las palabras capitalizadas.
     */
    public static String toNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        texto = texto.toLowerCase();

        StringBuilder result = new StringBuilder();

        String[] words = texto.trim().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i > 0) result.append(" ");

            String[] segments = word.split("([-'.])");
            for (int j = 0; j < segments.length; j++) {
                if (j > 0) result.append(word.charAt(segments[j - 1].length()));
                result.append(capitalize(segments[j], i + j == 0));
            }
        }
        return result.toString();
    }

    /**
     * Formatea la fecha proporcionada.
     *
     * @param fecha La fecha a formatear.
     * @return La fecha formateada.
     */
    public static String toFecha(ZonedDateTime fecha) {
        ZonedDateTime zonedDateTime = fecha.withZoneSameInstant(ZoneId.of("Europe/Madrid"));

        return DateTimeFormatter
                .ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"))
                .format(zonedDateTime);
    }

    /**
     * Capitaliza una palabra, respetando las letras con diacríticos.
     *
     * @param word La palabra a capitalizar.
     * @return La palabra capitalizada.
     */
    private static String capitalize(String word, boolean isFirstWord) {
        if (word.isEmpty()) {
            return word;
        }

        if (isFirstWord || !PREFIXES.contains(word)) {
            return word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1);
        }

        return word;
    }
}