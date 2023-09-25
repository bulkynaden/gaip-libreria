package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Representa a una persona en el sistema.
 */
@Data
public class PersonaImpl implements Persona {
    /**
     * Nombre de la persona.
     */
    private String nombre;

    /**
     * Primer apellido de la persona.
     */
    private String primerApellido;

    /**
     * Segundo apellido de la persona.
     */
    private String segundoApellido;

    /**
     * DNI (Documento Nacional de Identidad) de la persona.
     */
    private String dni;

    /**
     * Sexo de la persona.
     */
    private Sexo sexo;

    /**
     * Fecha de nacimiento de la persona.
     */
    private ZonedDateTime fechaNacimiento;

    /**
     * Telefono de la persona.
     */
    private String telefono;

    /**
     * Correo electrónico de la persona.
     */
    private String email;

    public PersonaImpl() {
        this("", "", "", "", Sexo.HOMBRE, ZonedDateTime.now(), "", "");
    }

    public PersonaImpl(String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, ZonedDateTime fechaNacimiento, String email, String telefono) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
    }
}