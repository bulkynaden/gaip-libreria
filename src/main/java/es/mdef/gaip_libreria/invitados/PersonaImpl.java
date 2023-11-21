package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;
import es.mdef.gaip_libreria.utilidades.Formateador;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Representa a una persona en el sistema.
 */
@Data
@EqualsAndHashCode(of = {"nombre", "primerApellido", "segundoApellido", "dni", "sexo", "fechaNacimiento", "email", "telefono"})
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
    private LocalDate fechaNacimiento;

    /**
     * Telefono de la persona.
     */
    private String telefono;

    /**
     * Correo electrónico de la persona.
     */
    private String email;

    public PersonaImpl() {
        this("", "", "", "", Sexo.HOMBRE, LocalDate.now(), "", "");
    }

    public PersonaImpl(String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, LocalDate fechaNacimiento, String email, String telefono) {
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        this.dni = dni;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre Nombre de la persona.
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = Formateador.toNombre(nombre);
    }

    /**
     * Establece el primer apellido de la persona
     *
     * @param primerApellido el primer apellido a establecer.
     */
    @Override
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = Formateador.toNombre(primerApellido);
    }

    /**
     * Establece el segundo apellido de la persona
     *
     * @param segundoApellido el segundo apellido a establecer.
     */
    @Override
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = Formateador.toNombre(segundoApellido);
    }
}