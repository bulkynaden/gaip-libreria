package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;

/**
 * Define las características y comportamientos específicos de un invitado.
 * <p>
 * Esta interfaz representa a un individuo que ha sido invitado a un acto por un {@link Anfitrion}.
 * Además de las propiedades heredadas de {@link Persona}, un invitado tiene asociado un parentesco que describe su relación o conexión
 * con el anfitrión, así como una localidad específica dentro del acto.
 * </p>
 */
public interface Invitado extends Persona, Asignable {

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

    Coche getCoche();

    void setCoche(Coche coche);

    default Anfitrion getAnfitrion() {
        return getInvitacion().getInvitacionesPorActo().getAnfitrion();
    }

    default Acto getActo() {
        return getInvitacion().getInvitacionesPorActo().getActo();
    }
}