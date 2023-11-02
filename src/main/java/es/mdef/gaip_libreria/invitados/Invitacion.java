package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Set;

/**
 * La interfaz <code>Invitacion</code> define las características y comportamientos específicos
 * de una invitación en el contexto de un sistema de gestión de invitados.
 *
 * <p>
 * Una invitación es un mecanismo mediante el cual un {@link Anfitrion} puede extender una solicitud
 * a uno o más {@link Asignable}s para asistir a un evento o acto específico. Cada invitación tiene
 * asociado un tipo de zona, un número máximo de asignables permitidos, y un conjunto de asignables
 * que han sido invitados a través de esa invitación específica.
 * </p>
 */
public interface Invitacion {

    /**
     * Devuelve el conjunto de {@link Asignable}s que han sido asociados a esta invitación.
     *
     * @return un conjunto inmutable de {@link Asignable}s.
     */
    Set<Asignable> getAsignables();

    /**
     * Asigna un conjunto específico de invitados a esta invitación.
     *
     * @param asignables el conjunto de {@link Asignable}s a asignar.
     */
    void setAsignables(Set<Asignable> asignables, boolean superarMaximo);

    /**
     * Devuelve el número máximo de asignables que pueden ser asociados a esta invitación.
     *
     * @return el número máximo de asignables permitidos.
     */
    int getNumeroMaximoAsignables();

    /**
     * Devuelve el tipo de zona preferencial o específica asociada a esta invitación.
     *
     * @return el {@link TipoDeZona} asociado.
     */
    TipoDeZona getTipoDeZona();

    /**
     * Devuelve las invitaciones por acto asociadas a la invitación.
     *
     * @return el objeto {@link InvitacionesPorActo} asociado.
     */
    InvitacionesPorActo getInvitacionesPorActo();

    /**
     * Asigna las invitaciones por acto a la invitación.
     *
     * @param invitacionesPorActo el objeto {@link InvitacionesPorActo} a asignar.
     */
    void setInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo);

    /**
     * Agrega un {@link Asignable} específico al conjunto de asignables de esta invitación.
     *
     * @param asignable el {@link Asignable} a agregar.
     */
    void agregarAsignable(Asignable asignable, boolean superarMaximo);

    /**
     * Elimina un {@link Asignable} específico del conjunto de asignables de esta invitación.
     *
     * @param asignable el {@link Asignable} a eliminar.
     */
    void quitarAsignable(Asignable asignable);

    /**
     * Incrementa el número máximo de asignables que pueden ser asociados a esta invitación.
     *
     * @param cantidad el número de asignables a agregar al límite actual.
     */
    void agregarNumeroMaximoAsignables(int cantidad);

    /**
     * Decrementa el número máximo de asignables que pueden ser asociados a esta invitación.
     *
     * @param cantidad el número de asignables a restar del límite actual.
     */
    void quitarNumeroMaximoAsignables(int cantidad);
}