package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

@Getter
public enum EmpleoArmada implements Empleo {
    MARINERO("Marinero"),
    MARINERO_PRIMERA("Marinero de Primera"),
    CABO("Cabo"),
    CABO_PRIMERO("Cabo Primero"),
    CABO_MAYOR("Cabo Mayor"),
    SARGENTO("Sargento"),
    SARGENTO_PRIMERO("Sargento Primero"),
    BRIGADA("Brigada"),
    SUBTENIENTE("Subteniente"),
    SUBOFICIAL_MAYOR("Suboficial Mayor"),
    ALFEREZ_FRAGATA("Alférez de fragata"),
    ALFEREZ_NAVIO("Alférez de navío"),
    TENIENTE_NAVIO("Teniente de navío"),
    CAPITAN_CORBETA("Capitán de corbeta"),
    CAPITAN_FRAGATA("Capitán de fragata"),
    CAPITAN_NAVIO("Capitán de navío"),
    CONTRALMIRANTE("Contralmirante"),
    VICEALMIRANTE("Vicealmirante"),
    ALMIRANTE("Almirante"),
    ALMIRANTE_GENERAL("Almirante General");

    private final String nombre;

    EmpleoArmada(String nombre) {
        this.nombre = nombre;
    }
}