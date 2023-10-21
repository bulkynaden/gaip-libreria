package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;

import java.time.LocalDate;

/**
 * Define las características y comportamientos básicos de una persona.
 * <p>
 * Esta interfaz representa a una persona con atributos esenciales como nombre, apellidos, DNI, sexo, fecha de nacimiento, email y teléfono.
 * Proporciona métodos para obtener y establecer estos atributos.
 * </p>
 */
public interface Persona {

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona.
     */
    String getNombre();

    /**
     * Establece un nuevo nombre para la persona.
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
     * Establece un nuevo primer apellido para la persona.
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
     * Establece un nuevo segundo apellido para la persona.
     *
     * @param segundoApellido el segundo apellido a establecer.
     */
    void setSegundoApellido(String segundoApellido);

    /**
     * Obtiene el Documento Nacional de Identidad (DNI) de la persona.
     *
     * @return el DNI de la persona.
     */
    String getDni();

    /**
     * Establece un nuevo DNI para la persona.
     *
     * @param dni el DNI a establecer.
     */
    void setDni(String dni);

    /**
     * Obtiene el género o sexo de la persona.
     *
     * @return el sexo de la persona.
     */
    Sexo getSexo();

    /**
     * Establece un nuevo género o sexo para la persona.
     *
     * @param sexo el sexo a establecer.
     */
    void setSexo(Sexo sexo);

    /**
     * Obtiene la fecha de nacimiento de la persona.
     *
     * @return la fecha de nacimiento de la persona.
     */
    LocalDate getFechaNacimiento();

    /**
     * Establece una nueva fecha de nacimiento para la persona.
     *
     * @param fechaNacimiento la fecha de nacimiento a establecer.
     */
    void setFechaNacimiento(LocalDate fechaNacimiento);

    /**
     * Obtiene la dirección de correo electrónico de la persona.
     *
     * @return el email de la persona.
     */
    String getEmail();

    /**
     * Establece una nueva dirección de correo electrónico para la persona.
     *
     * @param email el email a establecer.
     */
    void setEmail(String email);

    /**
     * Obtiene el número de teléfono de contacto de la persona.
     *
     * @return el número de teléfono de la persona.
     */
    String getTelefono();

    /**
     * Establece un nuevo número de teléfono de contacto para la persona.
     *
     * @param telefono el número de teléfono a establecer.
     */
    void setTelefono(String telefono);
}