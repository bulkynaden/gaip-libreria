package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.CuerpoFcse;
import es.mdef.gaip_libreria.constantes.Sexo;
import es.mdef.gaip_libreria.constantes.SituacionMilitar;
import es.mdef.gaip_libreria.utilidades.BusadorEmpleos;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Clase abstracta que extiende {@link InvitadoImpl} e implementa {@link InvitadoFcse},
 * representando a un invitado que pertenece a las Fuerzas y Cuerpos de Seguridad del Estado (FCSE).
 */
@EqualsAndHashCode(callSuper = true)
@Setter
public abstract class InvitadoFcseImpl extends InvitadoImpl implements InvitadoFcse {
    @Getter
    private SituacionMilitar situacion;
    private boolean asisteDeUniforme;
    private boolean requiereVestuario;
    private boolean entregaNombramiento;
    @Getter
    private Empleo empleo;
    @Getter
    private CuerpoFcse cuerpo;

    /**
     * Constructor protegido para crear un invitado FCSE con información detallada.
     *
     * @param cuerpo              El cuerpo FCSE al que pertenece el invitado.
     * @param empleo              El empleo o rango del invitado en el cuerpo FCSE.
     * @param nombre              Nombre del invitado.
     * @param primerApellido      Primer apellido del invitado.
     * @param segundoApellido     Segundo apellido del invitado.
     * @param dni                 Documento Nacional de Identidad del invitado.
     * @param sexo                Sexo del invitado.
     * @param fechaNacimiento     Fecha de nacimiento del invitado.
     * @param email               Email del invitado.
     * @param telefono            Teléfono del invitado.
     * @param parentesco          Parentesco del invitado con respecto al acto.
     * @param situacionMilitar    La situación militar del invitado.
     * @param asisteDeUniforme    Indica si el invitado asiste de uniforme.
     * @param requiereVestuario   Indica si el invitado requiere vestuario específico.
     * @param entregaNombramiento Indica si al invitado se le entrega un nombramiento.
     */
    protected InvitadoFcseImpl(CuerpoFcse cuerpo, String empleo, String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, LocalDate fechaNacimiento, String email, String telefono, String parentesco, SituacionMilitar situacionMilitar, boolean asisteDeUniforme, boolean requiereVestuario, boolean entregaNombramiento) {
        super(nombre, primerApellido, segundoApellido, dni, sexo, fechaNacimiento, email, telefono, parentesco);
        this.cuerpo = cuerpo;
        this.situacion = situacionMilitar;
        this.asisteDeUniforme = asisteDeUniforme;
        this.requiereVestuario = requiereVestuario;
        this.entregaNombramiento = entregaNombramiento;
        setEmpleo(empleo);
    }

    /**
     * Indica si el invitado asiste de uniforme.
     *
     * @return {@code true} si el invitado asiste de uniforme, {@code false} en caso contrario.
     */
    @Override
    public boolean getAsisteDeUniforme() {
        return asisteDeUniforme;
    }

    /**
     * Indica si el invitado requiere vestuario específico.
     *
     * @return {@code true} si el invitado requiere vestuario específico, {@code false} en caso contrario.
     */
    @Override
    public boolean getRequiereVestuario() {
        return requiereVestuario;
    }

    /**
     * Indica si el invitado entrega un nombramiento.
     *
     * @return {@code true} si el invitado entrega un nombramiento, {@code false} en caso contrario
     */
    @Override
    public boolean getEntregaNombramiento() {
        return entregaNombramiento;
    }

    /**
     * Valida y establece el empleo del invitado.
     * Utiliza {@link BusadorEmpleos} para encontrar y validar el empleo adecuado.
     *
     * @param empleo El empleo a establecer.
     */
    @Override
    public void setEmpleo(Empleo empleo) {
        if (validarEmpleo(empleo)) {
            this.empleo = empleo;
        }
    }

    /**
     * Establece el empleo del invitado a partir de una cadena de texto.
     * Convierte la cadena en un empleo válido utilizando {@link BusadorEmpleos}.
     *
     * @param empleo El nombre del empleo a establecer.
     */
    public void setEmpleo(String empleo) {
        setEmpleo(BusadorEmpleos.findEmpleo(empleo, cuerpo));
    }

    private boolean validarEmpleo(Empleo empleo) {
        Empleo foundEmpleo = BusadorEmpleos.findEmpleo(String.valueOf(empleo), cuerpo);
        return foundEmpleo != null;
    }
}