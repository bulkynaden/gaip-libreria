package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.CuerpoFcse;
import es.mdef.gaip_libreria.constantes.Sexo;
import es.mdef.gaip_libreria.constantes.SituacionMilitar;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Setter
public class InvitadoFcseImpl extends InvitadoImpl implements InvitadoFcse {
    @Getter
    private SituacionMilitar situacion;
    private boolean asisteDeUniforme;
    private boolean requiereVestuario;
    private boolean entregaNombramiento;
    @Getter
    private Empleo empleo;
    @Getter
    private CuerpoFcse cuerpo;

    protected InvitadoFcseImpl(CuerpoFcse cuerpo, Empleo empleo, String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, ZonedDateTime fechaNacimiento, String email, String telefono, String parentesco, Coche coche, SituacionMilitar situacionMilitar, boolean asisteDeUniforme, boolean requiereVestuario, boolean entregaNombramiento) {
        super(nombre, primerApellido, segundoApellido, dni, sexo, fechaNacimiento, email, telefono, parentesco, coche);
        this.cuerpo = cuerpo;
        this.empleo = empleo;
        this.situacion = situacionMilitar;
        this.asisteDeUniforme = asisteDeUniforme;
        this.requiereVestuario = requiereVestuario;
        this.entregaNombramiento = entregaNombramiento;
    }

    @Override
    public boolean getAsisteDeUniforme() {
        return asisteDeUniforme;
    }

    @Override
    public boolean getRequiereVestuario() {
        return requiereVestuario;
    }

    @Override
    public boolean getEntregaNombramiento() {
        return entregaNombramiento;
    }
}