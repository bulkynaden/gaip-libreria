package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.constantes.*;
import es.mdef.gaip_libreria.invitados.Empleo;

/**
 * Clase utilitaria final para buscar y obtener instancias de {@link Empleo}
 * basándonos en el nombre del empleo y el cuerpo al que pertenece.
 * <p>
 * Esta clase no debe ser instanciada.
 * </p>
 */
public final class EmpleoFinder {

    /**
     * Constructor privado para prevenir la instanciación de esta clase utilitaria.
     */
    private EmpleoFinder() {
    }

    /**
     * Busca y devuelve un {@link Empleo} basado en su nombre y un {@link CuerpoFcse}.
     *
     * @param empleo El nombre del empleo. Por ejemplo, "TENIENTE_GENERAL".
     * @param cuerpo El cuerpo al que pertenece el empleo.
     * @return La instancia de {@link Empleo} correspondiente.
     * @throws IllegalArgumentException Si no se encuentra el empleo para el cuerpo especificado.
     */
    public static Empleo findEmpleo(String empleo, CuerpoFcse cuerpo) {
        return findEmpleo(empleo, cuerpo.name());
    }

    /**
     * Busca y devuelve un {@link Empleo} basado en su nombre y una representación en cadena del cuerpo.
     *
     * @param empleo    El nombre del empleo. Por ejemplo, "TENIENTE_GENERAL".
     * @param cuerpoStr La representación en cadena del cuerpo. Por ejemplo, "TIERRA".
     * @return La instancia de {@link Empleo} correspondiente.
     * @throws IllegalArgumentException Si no se encuentra el empleo para el cuerpo especificado o el cuerpo no es válido.
     */
    public static Empleo findEmpleo(String empleo, String cuerpoStr) {
        CuerpoFcse cuerpo = CuerpoFcse.delValor(cuerpoStr);
        
        return switch (cuerpo) {
            case AIRE -> findEmpleoInAire(empleo);
            case TIERRA -> findEmpleoInTierra(empleo);
            case ARMADA -> findEmpleoInArmada(empleo);
            case CNP -> findEmpleoInCNP(empleo);
        };
    }

    private static Empleo findEmpleoInAire(String empleo) {
        for (EmpleoAire e : EmpleoAire.values()) {
            if (e.name().equals(empleo)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Empleo no encontrado en Aire: " + empleo);
    }

    private static Empleo findEmpleoInTierra(String empleo) {
        for (EmpleoTierra e : EmpleoTierra.values()) {
            if (e.name().equals(empleo)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Empleo no encontrado en Tierra: " + empleo);
    }

    private static Empleo findEmpleoInArmada(String empleo) {
        for (EmpleoArmada e : EmpleoArmada.values()) {
            if (e.name().equals(empleo)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Empleo no encontrado en Armada: " + empleo);
    }

    private static Empleo findEmpleoInCNP(String empleo) {
        for (EmpleoPoliciaNacional e : EmpleoPoliciaNacional.values()) {
            if (e.name().equals(empleo)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Empleo no encontrado en CNP: " + empleo);
    }
}