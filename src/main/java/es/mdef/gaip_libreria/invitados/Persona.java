package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;

import java.time.ZonedDateTime;

/**
 * Representa a una persona con atributos básicos como nombre, apellidos, DNI, sexo, fecha de nacimiento, email y teléfono.
 */
public interface Persona {

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona.
     */
    String getNombre();

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre el nombre a establecer.
     */
    void setNombre(String nombre);

    /**
     * Obtiene el primer apellido de la persona.
     *
     * @return el primer apellido de la persona.
     */
    String getPrimerApellido();

    /**
     * Establece el primer apellido de la persona.
     *
     * @param primerApellido el primer apellido a establecer.
     */
    void setPrimerApellido(String primerApellido);

    /**
     * Obtiene el segundo apellido de la persona.
     *
     * @return el segundo apellido de la persona.
     */
    String getSegundoApellido();

    /**
     * Establece el segundo apellido de la persona.
     *
     * @param segundoApellido el segundo apellido a establecer.
     */
    void setSegundoApellido(String segundoApellido);

    /**
     * Obtiene el DNI de la persona.
     *
     * @return el DNI de la persona.
     */
    String getDni();

    /**
     * Establece el DNI de la persona.
     *
     * @param dni el DNI a establecer.
     */
    void setDni(String dni);

    /**
     * Obtiene el sexo de la persona.
     *
     * @return el sexo de la persona.
     */
    Sexo getSexo();

    /**
     * Establece el sexo de la persona.
     *
     * @param sexo el sexo a establecer.
     */
    void setSexo(Sexo sexo);

    /**
     * Obtiene la fecha de nacimiento de la persona.
     *
     * @return la fecha de nacimiento de la persona.
     */
    ZonedDateTime getFechaNacimiento();

    /**
     * Establece la fecha de nacimiento de la persona.
     *
     * @param fechaNacimiento la fecha de nacimiento a establecer.
     */
    void setFechaNacimiento(ZonedDateTime fechaNacimiento);

    /**
     * Obtiene el email de la persona.
     *
     * @return el email de la persona.
     */
    String getEmail();

    /**
     * Establece el email de la persona.
     *
     * @param email el email a establecer.
     */
    void setEmail(String email);

    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return el número de teléfono de la persona.
     */
    String getTelefono();

    /**
     * Establece el número de teléfono de la persona.
     *
     * @param telefono el número de teléfono a establecer.
     */
    void setTelefono(String telefono);
}