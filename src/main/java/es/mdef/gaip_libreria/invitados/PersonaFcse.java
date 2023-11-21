package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.CuerpoFcse;
import es.mdef.gaip_libreria.constantes.SituacionMilitar;

/**
 * Interfaz que representa a una persona perteneciente a las Fuerzas y Cuerpos de Seguridad del Estado (FCSE).
 * Extiende de {@link Persona} e incluye información específica relacionada con su situación militar, cuerpo y empleo.
 */
public interface PersonaFcse extends Persona {
    /**
     * Obtiene la situación militar de la persona FCSE.
     *
     * @return la situación militar actual de la persona.
     */
    SituacionMilitar getSituacion();

    /**
     * Establece la situación militar de la persona FCSE.
     *
     * @param situacion la situación militar a establecer.
     */
    void setSituacion(SituacionMilitar situacion);

    /**
     * Obtiene el cuerpo de FCSE al que pertenece la persona.
     *
     * @return el cuerpo FCSE asociado a la persona.
     */
    CuerpoFcse getCuerpo();

    /**
     * Establece el cuerpo de FCSE al que pertenece la persona.
     *
     * @param cuerpo el cuerpo FCSE a asociar con la persona.
     */
    void setCuerpo(CuerpoFcse cuerpo);

    /**
     * Obtiene el empleo o rango dentro del cuerpo FCSE de la persona.
     *
     * @return el empleo o rango actual de la persona dentro del cuerpo FCSE.
     */
    Empleo getEmpleo();

    /**
     * Establece el empleo o rango dentro del cuerpo FCSE de la persona.
     *
     * @param empleo el empleo o rango a establecer dentro del cuerpo FCSE.
     */
    void setEmpleo(Empleo empleo);
}