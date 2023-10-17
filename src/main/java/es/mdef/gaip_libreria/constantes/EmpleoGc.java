package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

@Getter
public enum EmpleoGc implements Empleo {
    SOLDADO("Soldado"),
    SOLDADO_PRIMERA("Soldado de Primera"),
    CABO("Cabo"),
    CABO_PRIMERO("Cabo Primero"),
    CABO_MAYOR("Cabo Mayor"),
    SARGENTO("Sargento"),
    SARGENTO_PRIMERO("Sargento Primero"),
    BRIGADA("Brigada"),
    SUBTENIENTE("Subteniente"),
    SUBOFICIAL_MAYOR("Suboficial Mayor"),
    ALFEREZ("Alférez"),
    TENIENTE("Teniente"),
    CAPITAN("Capitán"),
    COMANDANTE("Comandante"),
    TENIENTE_CORONEL("Teniente Coronel"),
    CORONEL("Coronel"),
    GENERAL_DE_BRIGADA("General de Brigada"),
    GENERAL_DE_DIVISION("General de División"),
    TENIENTE_GENERAL("Teniente General"),
    GENERAL_DE_EJERCITO("General de Ejército");

    private final String nombre;

    EmpleoGc(String nombre) {
        this.nombre = nombre;
    }
}