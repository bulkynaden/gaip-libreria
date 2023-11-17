package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

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
     * @param invitacion    la {@link Invitacion} a asignar al invitado.
     * @param superarMaximo indica si se permite superar el número máximo de invitados en la invitación.
     */
    void setInvitacion(Invitacion invitacion, boolean superarMaximo);

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
     * @param localidad     la {@link LocalidadConfigurada} a asignar al invitado.
     * @param superarMaximo indica si se permite superar el número máximo de localidades asignadas.
     */
    void setLocalidad(LocalidadConfigurada localidad, boolean superarMaximo);

    /**
     * Obtiene el coche asociado a este invitado.
     *
     * @return el {@link Coche} asociado a este invitado.
     */
    Coche getCoche();

    /**
     * Asigna un coche a este invitado.
     *
     * @param coche el {@link Coche} a asignar al invitado.
     */
    void setCoche(Coche coche);

    /**
     * Obtiene el anfitrión asociado a este invitado a través de la invitación.
     * Devuelve null si el invitado no tiene una invitación.
     *
     * @return el {@link Anfitrion} asociado a este invitado, o null si no hay invitación.
     */
    default Anfitrion getAnfitrion() {
        if (getInvitacion() == null) {
            return null;
        }
        return getInvitacion().getInvitacionesPorActo().getAnfitrion();
    }

    /**
     * Obtiene el acto asociado a este invitado a través de la invitación.
     *
     * @return el {@link Acto} asociado a este invitado.
     */
    default Acto getActo() {
        return getInvitacion().getInvitacionesPorActo().getActo();
    }

    /**
     * Obtiene la zona de estacionamiento prioritaria asignada a este invitado.
     * Esta zona puede ser asignada en función de ciertos criterios o prioridades.
     *
     * @return la {@link ZonaConfigurada} que representa la zona de parking prioritaria para este invitado.
     */
    ZonaConfigurada getParkingPrioritario();
}