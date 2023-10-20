package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

/**
 * Define las características y comportamientos específicos de un invitado.
 * <p>
 * Esta interfaz representa a un individuo que ha sido invitado a un acto por un {@link Anfitrion}.
 * Además de las propiedades heredadas de {@link Persona}, un invitado tiene asociado un parentesco que describe su relación o conexión
 * con el anfitrión, así como una localidad específica dentro del acto.
 * </p>
 */
public interface Invitado extends Persona {

    /**
     * Obtiene la invitación que ha sido asociada a este invitado.
     *
     * @return la {@link Invitacion} asociada a este invitado.
     */
    Invitacion getInvitacion();

    /**
     * Asigna una invitación específica a este invitado.
     *
     * @param invitacion la {@link Invitacion} a asignar al invitado.
     */
    void setInvitacion(Invitacion invitacion);

    /**
     * Obtiene el parentesco o relación que tiene el invitado con el anfitrión.
     *
     * @return una cadena de texto que describe el parentesco del invitado.
     */
    String getParentesco();

    /**
     * Establece o define el parentesco o relación que tiene el invitado con el anfitrión.
     *
     * @param parentesco una cadena de texto que describe el parentesco a establecer.
     */
    void setParentesco(String parentesco);

    /**
     * Obtiene la localidad configurada que ha sido asignada a este invitado dentro del acto.
     *
     * @return la {@link LocalidadConfigurada} asignada al invitado.
     */
    LocalidadConfigurada getLocalidad();

    /**
     * Asigna una localidad específica a este invitado dentro del acto.
     *
     * @param localidad la {@link LocalidadConfigurada} a asignar al invitado.
     */
    void setLocalidad(LocalidadConfigurada localidad);

    Coche getCoche();

    void setCoche(Coche coche);

    default Anfitrion getAnfitrion() {
        return getInvitacion().getInvitacionesPorActo().getAnfitrion();
    }

    default Acto getActo() {
        return getInvitacion().getInvitacionesPorActo().getActo();
    }
}