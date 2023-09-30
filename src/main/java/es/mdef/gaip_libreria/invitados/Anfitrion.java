package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

/**
 * Define las características y comportamientos específicos de un anfitrión.
 * <p>
 * Esta interfaz representa a un anfitrión que tiene la capacidad de invitar a personas a un acto específico.
 * Un anfitrión está vinculado a una unidad de formación y tiene un conjunto de invitaciones que ha extendido.
 * Además, hereda las propiedades y comportamientos de una {@link Persona}.
 * </p>
 */
public interface Anfitrion extends Persona {

    /**
     * Obtiene el acto específico al cual el anfitrión está invitando a las personas.
     *
     * @return el {@link Acto} asociado al anfitrión.
     */
    Acto getActo();

    /**
     * Asigna o establece un acto específico al cual el anfitrión invitará a las personas.
     *
     * @param acto el {@link Acto} a asociar al anfitrión.
     */
    void setActo(Acto acto);

    /**
     * Obtiene la unidad de formación a la que pertenece el anfitrión.
     *
     * @return el nombre o identificador de la unidad de formación del anfitrión.
     */
    String getUnidadDeFormacion();

    /**
     * Asigna o establece la unidad de formación a la que pertenece el anfitrión.
     *
     * @param unidadDeFormacion el nombre o identificador de la unidad de formación a establecer.
     */
    void setUnidadDeFormacion(String unidadDeFormacion);

    /**
     * Obtiene el conjunto de invitaciones por acto que el anfitrión ha extendido.
     *
     * @return un conjunto de {@link InvitacionesPorActo} asociadas al anfitrión.
     */
    Set<InvitacionesPorActo> getInvitacionesPorActo();

    /**
     * Asigna o establece el conjunto de invitaciones por acto que el anfitrión ha extendido.
     *
     * @param invitacionesPorActo el conjunto de {@link InvitacionesPorActo} a establecer.
     */
    void setInvitacionesPorActo(Set<InvitacionesPorActo> invitacionesPorActo);

    /**
     * Agrega una invitación por acto específica al conjunto de invitaciones del anfitrión.
     *
     * @param invitacionesPorActo la {@link InvitacionesPorActo} a agregar al anfitrión.
     */
    void agregarInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo);

    /**
     * Elimina o quita una invitación por acto específica del conjunto de invitaciones del anfitrión.
     *
     * @param invitacionesPorActo la {@link InvitacionesPorActo} a quitar del anfitrión.
     */
    void quitarInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo);
}