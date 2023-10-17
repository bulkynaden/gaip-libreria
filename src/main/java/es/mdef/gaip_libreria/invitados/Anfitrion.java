package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Set;

/**
 * Define las características y comportamientos específicos de un anfitrión.
 * <p>
 * Un anfitrión es una entidad que tiene la capacidad de invitar a personas a diversos actos.
 * Está vinculado a una unidad de formación y gestiona un conjunto de invitaciones que ha extendido para cada acto.
 * Esta interfaz extiende a {@link Persona}, heredando sus propiedades y comportamientos.
 * </p>
 */
public interface Anfitrion extends Persona, Comparable<Anfitrion> {

    /**
     * Obtiene los actos asociados al anfitrión.
     *
     * @return Conjunto de actos a los que el anfitrión ha invitado o planea invitar personas.
     */
    Set<Acto> getActos();

    /**
     * Establece los actos a los que el anfitrión invitará personas.
     *
     * @param actos Conjunto de actos a asociar al anfitrión.
     */
    void setActos(Set<Acto> actos);

    /**
     * Obtiene la unidad de formación del anfitrión.
     *
     * @return Identificador o nombre de la unidad de formación a la que pertenece el anfitrión.
     */
    String getUnidadDeFormacion();

    /**
     * Define la unidad de formación a la que pertenece el anfitrión.
     *
     * @param unidadDeFormacion Identificador o nombre de la unidad de formación.
     */
    void setUnidadDeFormacion(String unidadDeFormacion);

    /**
     * Obtiene las invitaciones por acto que el anfitrión ha extendido.
     *
     * @return Conjunto de invitaciones por acto asociadas al anfitrión.
     */
    Set<InvitacionesPorActo> getInvitacionesPorActo();

    /**
     * Establece las invitaciones por acto que el anfitrión ha extendido.
     *
     * @param invitacionesPorActo Conjunto de invitaciones por acto.
     */
    void setInvitacionesPorActo(Set<InvitacionesPorActo> invitacionesPorActo);

    /**
     * Agrega una invitación por acto al conjunto de invitaciones del anfitrión.
     *
     * @param invitacionesPorActo Invitación por acto a agregar.
     */
    void agregarInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo);

    /**
     * Elimina una invitación por acto del conjunto de invitaciones del anfitrión.
     *
     * @param invitacionesPorActo Invitación por acto a eliminar.
     */
    void quitarInvitacionesPorActo(InvitacionesPorActo invitacionesPorActo);

    /**
     * Invita a una persona a un acto específico en una zona determinada.
     *
     * @param invitado   Persona a invitar.
     * @param acto       Acto al que se invita.
     * @param tipoDeZona Zona en la que se ubicará el invitado.
     */
    void agregarInvitado(Invitado invitado, Acto acto, TipoDeZona tipoDeZona);

    /**
     * Retira la invitación a una persona de un acto específico.
     *
     * @param invitado Persona a la que se le retira la invitación.
     * @param acto     Acto del que se retira la invitación.
     */
    void quitarInvitado(Invitado invitado, Acto acto);

    /**
     * Agrega un acto al conjunto de actos a los que el anfitrión invitará personas.
     *
     * @param acto Acto a agregar.
     */
    void agregarActo(Acto acto);

    /**
     * Elimina un acto del conjunto de actos a los que el anfitrión invitará personas.
     *
     * @param acto Acto a eliminar.
     */
    void quitarActo(Acto acto);

    default long getNumeroInvitadosDeUnActoPorZona(Acto acto, TipoDeZona tipoDeZona) {
        return this.getInvitacionesPorActo().stream().filter(e -> e.getActo() == acto)
                .flatMap(e -> e.getInvitaciones().stream())
                .filter(e -> e.getTipoDeZona() == tipoDeZona)
                .mapToInt(e -> e.getInvitados().size()).sum();
    }

    int compararPorCantidadDeInvitadosDeUnTipoDeZona(Acto acto, TipoDeZona tipo, Anfitrion anfitrion1, Anfitrion anfitrion2);
}