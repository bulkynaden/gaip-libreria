package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

/**
 * Enumeración que representa los diferentes empleos en la Armada.
 */
@Getter
public enum EmpleoArmada implements Empleo {
    SOLDADO("Soldado"),
    MARINERO("Marinero"),
    SOLDADO_PRIMERA("Soldado Primera"),
    MARINERO_PRIMERA("Marinero Primera"),
    CABO("Cabo"),
    CABO_PRIMERO("Cabo Primero"),
    CABO_MAYOR("Cabo Mayor"),
    ALUMNO("Alumno"),
    SARGENTO_ALUMNO("Sargento Alumno"),
    SARGENTO("Sargento"),
    SARGENTO_PRIMERO("Sargento Primero"),
    BRIGADA("Brigada"),
    SUBTENIENTE("Subteniente"),
    SUBOFICIAL_MAYOR("Suboficial Mayor"),
    ASPIRANTE_1("Aspirante 1º"),
    ASPIRANTE_2("Aspirante 2º"),
    GUARDIA_MARINA_1("Guardia Marina 1º"),
    GUARDIA_MARINA_2("Guardia Marina 2º"),
    ALFEREZ_ALUMNO("Alférez Alumno"),
    ALFEREZ_FRAGATA_ALUMNO("Alférez de Fragata Alumno"),
    ALFEREZ("Alférez"),
    ALFEREZ_FRAGATA("Alférez de Fragata"),
    ALFEREZ_NAVIO("Alférez de Navío"),
    TENIENTE("Teniente"),
    TENIENTE_NAVIO("Teniente de Navío"),
    CAPITAN("Capitán"),
    CAPITAN_CORBETA("Capitán de Corbeta"),
    COMANDANTE("Comandante"),
    CAPITAN_FRAGATA("Capitán de Fragata"),
    TENIENTE_CORONEL("Teniente Coronel"),
    CAPITAN_NAVIO("Capitán de Navío"),
    CORONEL("Coronel"),
    CONTRALMIRANTE("Contralmirante"),
    GENERAL_DE_BRIGADA("General de Brigada"),
    VICEALMIRANTE("Vicealmirante"),
    GENERAL_DE_DIVISION("General de División"),
    ALMIRANTE("Almirante"),
    ALMIRANTE_GENERAL("Almirante General");

    private final String nombre;

    /**
     * Constructor para asignar un nombre legible a cada empleo en la Armada.
     *
     * @param nombre Nombre legible del empleo.
     */
    EmpleoArmada(String nombre) {
        this.nombre = nombre;
    }
}