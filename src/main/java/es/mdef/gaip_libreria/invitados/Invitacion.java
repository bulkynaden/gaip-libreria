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
 * a uno o más {@link Invitado}s para asistir a un evento o acto específico. Cada invitación tiene
 * asociado un tipo de zona, un número máximo de invitados permitidos, y un conjunto de invitados
 * que han sido invitados a través de esa invitación específica.
 * </p>
 */
public interface Invitacion {

    /**
     * Devuelve el conjunto de {@link Invitado}s que han sido asociados a esta invitación.
     *
     * @return un conjunto de {@link Invitado}s.
     */
    Set<Invitado> getInvitados();

    /**
     * Asigna un conjunto específico de invitados a esta invitación.
     *
     * @param invitados             el conjunto de {@link Invitado}s a asignar.
     * @param permitirExcederMaximo indica si se permite exceder el número máximo de invitados permitidos.
     */
    void setInvitados(Set<Invitado> invitados, boolean permitirExcederMaximo);

    /**
     * Devuelve el conjunto de {@link Coche}s que han sido asociados a esta invitación.
     *
     * @return un conjunto de {@link Coche}s.
     */
    Set<Coche> getCoches();

    /**
     * Asigna un conjunto específico de coches a esta invitación.
     *
     * @param coches                el conjunto de {@link Coche}s a asignar.
     * @param permitirExcederMaximo indica si se permite exceder el número máximo de coches permitidos.
     */
    void setCoches(Set<Coche> coches, boolean permitirExcederMaximo);

    /**
     * Devuelve el número máximo de invitados que pueden ser asociados a esta invitación.
     *
     * @return el número máximo de invitados permitidos.
     */
    int getNumeroMaximoInvitados();

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
     * Agrega un {@link Invitado} específico al conjunto de invitados de esta invitación.
     *
     * @param invitado              el {@link Invitado} a agregar.
     * @param permitirExcederMaximo indica si se permite exceder el número máximo de invitados permitidos.
     */
    void agregarInvitado(Invitado invitado, boolean permitirExcederMaximo);

    /**
     * Elimina un {@link Invitado} específico del conjunto de invitados de esta invitación.
     *
     * @param invitado el {@link Invitado} a eliminar.
     */
    void quitarInvitado(Invitado invitado);

    /**
     * Incrementa el número máximo de invitados que pueden ser asociados a esta invitación.
     *
     * @param cantidad el número de invitados a agregar al límite actual.
     */
    void agregarNumeroMaximoInvitado(int cantidad);

    /**
     * Decrementa el número máximo de invitados que pueden ser asociados a esta invitación.
     *
     * @param cantidad el número de invitados a restar del límite actual.
     */
    void quitarNumeroMaximoInvitado(int cantidad);

    /**
     * Agrega un {@link Coche} específico al conjunto de coches de esta invitación.
     *
     * @param coche                 el {@link Coche} a agregar.
     * @param permitirExcederMaximo indica si se permite exceder el número máximo de coches permitidos.
     */
    void agregarCoche(Coche coche, boolean permitirExcederMaximo);

    /**
     * Elimina un {@link Coche} específico del conjunto de coches de esta invitación.
     *
     * @param coche el {@link Coche} a eliminar.
     */
    void quitarCoche(Coche coche);
}