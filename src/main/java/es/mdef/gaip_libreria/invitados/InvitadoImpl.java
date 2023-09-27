package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.Sexo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InvitadoImpl extends PersonaImpl implements Invitado {
    Acto acto;
    String parentesco;
    Anfitrion anfitrion;

    public InvitadoImpl(String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, ZonedDateTime fechaNacimiento, String email, String telefono, String parentesco) {
        super(nombre, primerApellido, segundoApellido, dni, sexo, fechaNacimiento, email, telefono);
        this.parentesco = parentesco;
    }
}