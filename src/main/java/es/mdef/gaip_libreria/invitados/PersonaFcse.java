package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.CuerpoFcse;
import es.mdef.gaip_libreria.constantes.SituacionMilitar;

public interface PersonaFcse extends Persona {
    SituacionMilitar getSituacion();

    void setSituacion(SituacionMilitar situacion);

    CuerpoFcse getCuerpo();

    void setCuerpo(CuerpoFcse cuerpo);

    Empleo getEmpleo();

    void setEmpleo(Empleo empleo);
}