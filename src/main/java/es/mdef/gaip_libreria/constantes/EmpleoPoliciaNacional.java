package es.mdef.gaip_libreria.constantes;

import es.mdef.gaip_libreria.invitados.Empleo;
import lombok.Getter;

/**
 * Enumeración que representa los diferentes empleos en la Policía Nacional.
 */
@Getter
public enum EmpleoPoliciaNacional implements Empleo {
    POLICIA_ALUMNO("Policía alumno"),
    POLICIA_PRACTICAS("Policía en prácticas"),
    POLICIA("Policía"),
    OFICIAL_POLICIA("Oficial de Policía"),
    SUBINSPECTOR("Subinspector"),
    INSPECTOR_ALUMNO_1("Inspector alumno de primer año"),
    INSPECTOR_ALUMNO_2("Inspector alumno de segundo año"),
    INSPECTOR_ALUMNO_PRACTICAS("Inspector alumno en prácticas"),
    INSPECTOR("Inspector"),
    INSPECTOR_JEFE("Inspector Jefe"),
    COMISARIO("Comisario"),
    COMISARIO_PRINCIPAL("Comisario Principal"),
    JEFE_SUPERIOR("Jefe superior"),
    COMISARIO_GENERAL("Comisario General"),
    SUBDIRECTOR_GENERAL("Subdirector General"),
    DIRECTOR_ADJUNTO_OPERATIVO("Director Adjunto Operativo");

    private final String nombre;

    /**
     * Constructor para asignar un nombre legible a cada empleo de la Policía Nacional
     *
     * @param nombre Nombre legible del empleo.
     */
    EmpleoPoliciaNacional(String nombre) {
        this.nombre = nombre;
    }
}