package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

@Getter
public enum EmpleoPoliciaNacional implements Empleo {
    POLICIA("Policía"),
    OFICIAL_POLICIA("Oficial de Policía"),
    SUBINSPECTOR("Subinspector"),
    INSPECTOR("Inspector"),
    INSPECTOR_JEFE("Inspector Jefe"),
    COMISARIO("Comisario"),
    COMISARIO_PRINCIPAL("Comisario Principal"),
    JEFE_SUPERIOR("Jefe superior"),
    COMISARIO_GENERAL("Comisario General"),
    SUBDIRECTOR_GENERAL("Subdirector General"),
    DIRECTOR_ADJUNTO_OPERATIVO("Director Adjunto Operativo");

    private final String nombre;

    EmpleoPoliciaNacional(String nombre) {
        this.nombre = nombre;
    }
}