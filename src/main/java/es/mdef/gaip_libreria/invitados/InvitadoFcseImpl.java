package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.CuerpoFcse;
import es.mdef.gaip_libreria.constantes.Sexo;
import es.mdef.gaip_libreria.constantes.SituacionMilitar;
import es.mdef.gaip_libreria.utilidades.BusadorEmpleos;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    protected InvitadoFcseImpl(CuerpoFcse cuerpo, String empleo, String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, LocalDate fechaNacimiento, String email, String telefono, String parentesco, Coche coche, SituacionMilitar situacionMilitar, boolean asisteDeUniforme, boolean requiereVestuario, boolean entregaNombramiento) {
        super(nombre, primerApellido, segundoApellido, dni, sexo, fechaNacimiento, email, telefono, parentesco, coche);
        this.cuerpo = cuerpo;
        this.situacion = situacionMilitar;
        this.asisteDeUniforme = asisteDeUniforme;
        this.requiereVestuario = requiereVestuario;
        this.entregaNombramiento = entregaNombramiento;
        setEmpleo(empleo);
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

    @Override
    public void setEmpleo(Empleo empleo) {
        if (validarEmpleo(empleo)) {
            this.empleo = empleo;
        }
    }

    public void setEmpleo(String empleo) {
        setEmpleo(BusadorEmpleos.findEmpleo(empleo, cuerpo));
    }

    private boolean validarEmpleo(Empleo empleo) {
        Empleo foundEmpleo = BusadorEmpleos.findEmpleo(String.valueOf(empleo), cuerpo);
        return foundEmpleo != null;
    }
}