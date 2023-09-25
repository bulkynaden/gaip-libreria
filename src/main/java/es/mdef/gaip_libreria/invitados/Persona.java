package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;

import java.time.ZonedDateTime;

public interface Persona {
    String getNombre();

    void setNombre(String nombre);

    String getPrimerApellido();

    void setPrimerApellido(String primerApellido);

    String getSegundoApellido();

    void setSegundoApellido(String segundoApellido);

    String getDni();

    void setDni(String dni);

    Sexo getSexo();

    void setSexo(Sexo sexo);

    ZonedDateTime getFechaNacimiento();

    void setFechaNacimiento(ZonedDateTime fechaNacimiento);

    String getEmail();

    void setEmail(String email);

    String getTelefono();

    void setTelefono(String telefono);
}