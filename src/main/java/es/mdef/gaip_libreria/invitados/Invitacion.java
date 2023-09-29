package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Set;

/**
 * Representa una invitación que un {@link Anfitrion} puede extender a uno o más {@link Invitado}s.
 * Una invitación tiene asociado un tipo de zona y un número máximo de invitados permitidos.
 */
public interface Invitacion {

    /**
     * Obtiene el conjunto de invitados asociados a esta invitación.
     *
     * @return un conjunto de invitados.
     */
    Set<Invitado> getInvitados();

    /**
     * Establece el conjunto de invitados asociados a esta invitación.
     *
     * @param invitados el conjunto de invitados a establecer.
     */
    void setInvitados(Set<Invitado> invitados);

    /**
     * Obtiene el número máximo de invitados permitidos para esta invitación.
     *
     * @return el número máximo de invitados.
     */
    int getNumeroMaximoInvitados();

    /**
     * Obtiene el tipo de zona asociado a esta invitación.
     *
     * @return el tipo de zona.
     */
    TipoDeZona getTipoDeZona();

    /**
     * Obtiene el anfitrión que extendió esta invitación.
     *
     * @return el anfitrión asociado.
     */
    Anfitrion getAnfitrion();

    /**
     * Establece el anfitrión que extendió esta invitación.
     *
     * @param anfitrion el anfitrión a establecer.
     */
    void setAnfitrion(Anfitrion anfitrion);

    /**
     * Agrega un invitado al conjunto de invitados de esta invitación.
     *
     * @param invitado el invitado a agregar.
     */
    void agregarInvitado(Invitado invitado);

    /**
     * Quita un invitado del conjunto de invitados de esta invitación.
     *
     * @param invitado el invitado a quitar.
     */
    void quitarInvitado(Invitado invitado);

    /**
     * Aumenta el número máximo de invitados permitidos para esta invitación.
     *
     * @param cantidad la cantidad de invitados a agregar al límite actual.
     */
    void agregarNumeroMaximoInvitado(int cantidad);

    /**
     * Disminuye el número máximo de invitados permitidos para esta invitación.
     *
     * @param cantidad la cantidad de invitados a quitar del límite actual.
     */
    void quitarNumeroMaximoInvitado(int cantidad);
}