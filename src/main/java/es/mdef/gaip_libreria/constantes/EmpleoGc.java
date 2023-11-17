package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

/**
 * Enumeración que representa los diferentes empleos en la Guardia Civil.
 */
@Getter
public enum EmpleoGc implements Empleo {
    GUARDIA_ALUMNO("Guardia alumno"),
    GUARDIA_CIVIL("Guardia civil"),
    GUARDIA_CIVIL_PRIMERA("Guardia civil de Primera"),
    CABO("Cabo"),
    CABO_PRIMERO("Cabo Primero"),
    CABO_MAYOR("Cabo Mayor"),
    SARGENTO_ALUMNO("Sargento Alumno"),
    SARGENTO("Sargento"),
    SARGENTO_PRIMERO("Sargento Primero"),
    BRIGADA("Brigada"),
    SUBTENIENTE("Subteniente"),
    SUBOFICIAL_MAYOR("Suboficial Mayor"),
    CABALLERO_CADETE("Caballero Cadete"),
    DAMA_CADETE("Dama Cadete"),
    CABALLERO_ALFEREZ_CADETE("Caballero Alférez Cadete"),
    DAMA_ALFEREZ_CADETE("Dama Alférez Cadete"),
    ALFEREZ("Alférez"),
    TENIENTE("Teniente"),
    CAPITAN("Capitán"),
    COMANDANTE("Comandante"),
    TENIENTE_CORONEL("Teniente Coronel"),
    CORONEL("Coronel"),
    GENERAL_DE_BRIGADA("General de Brigada"),
    GENERAL_DE_DIVISION("General de División"),
    TENIENTE_GENERAL("Teniente General");

    private final String nombre;

    /**
     * Constructor para asignar un nombre legible a cada empleo de la Guardia Civil.
     *
     * @param nombre Nombre legible del empleo.
     */
    EmpleoGc(String nombre) {
        this.nombre = nombre;
    }
}