package es.mdef.gaip_libreria.constantes;

import lombok.Getter;

/**
 * Enumeración que representa los posibles valores para el sexo de un paciente.
 */
@Getter
public enum Sexo {

    /**
     * Valor que representa el sexo masculino.
     */
    HOMBRE("Hombre"),

    /**
     * Valor que representa el sexo femenino.
     */
    MUJER("Mujer");

    /**
     * Valor que representa el sexo.
     */
    private final String valor;

    /**
     * Constructor de la clase.
     *
     * @param valor Valor que representa el sexo.
     */
    Sexo(String valor) {
        this.valor = valor;
    }

    /**
     * Método que devuelve el sexo correspondiente a un valor.
     *
     * @param valor Valor del sexo.
     * @return Sexo correspondiente al valor.
     */
    public static Sexo delValor(String valor) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.valor.equalsIgnoreCase(valor.trim())) {
                return sexo;
            }
        }
        return null;
    }

    /**
     * Método que devuelve el valor del sexo.
     *
     * @return Valor del sexo.
     */
    @Override
    public String toString() {
        return valor;
    }
}