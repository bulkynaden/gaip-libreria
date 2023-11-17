package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

/**
 * Enumeración que representa los diferentes empleos en el Ejército del Aire.
 */
@Getter
public enum EmpleoAire implements Empleo {
    SOLDADO("Soldado"),
    SOLDADO_PRIMERA("Soldado de Primera"),
    CABO("Cabo"),
    CABO_PRIMERO("Cabo Primero"),
    CABO_MAYOR("Cabo Mayor"),
    CABALLERO_1("Caballero 1°"),
    DAMA_1("Dama 1°"),
    CABALLERO_2("Caballero 2°"),
    DAMA_2("Dama 2°"),
    SARGENTO_ALUMNO_3("Sargento Alumno 3°"),
    SARGENTO("Sargento"),
    SARGENTO_PRIMERO("Sargento Primero"),
    BRIGADA("Brigada"),
    SUBTENIENTE("Subteniente"),
    SUBOFICIAL_MAYOR("Suboficial Mayor"),
    CADETE_1("Cadete de 1°"),
    CADETE_2("Cadete de 2°"),
    ALFEREZ_ALUMNO_3("Alférez Alumno de 3°"),
    ALFEREZ_ALUMNO_4("Alférez Alumno de 4°"),
    ALFEREZ_ALUMNO_5("Alférez Alumno de 5°"),
    ALFEREZ("Alférez"),
    TENIENTE("Teniente"),
    CAPITAN("Capitán"),
    COMANDANTE("Comandante"),
    TENIENTE_CORONEL("Teniente Coronel"),
    CORONEL("Coronel"),
    GENERAL_DE_BRIGADA("General de Brigada"),
    GENERAL_DE_DIVISION("General de División"),
    TENIENTE_GENERAL("Teniente General"),
    GENERAL_DEL_AIRE("General del Aire");

    private final String nombre;

    /**
     * Constructor para asignar un nombre legible a cada empleo del Ejército del Aire.
     *
     * @param nombre Nombre legible del empleo.
     */
    EmpleoAire(String nombre) {
        this.nombre = nombre;
    }
}