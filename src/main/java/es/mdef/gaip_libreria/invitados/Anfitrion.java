package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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

    default Set<Invitado> getInvitadosAUnActo(Acto acto) {
        return this.getInvitacionesPorActo().stream()
                .filter(e -> e.getActo().equals(acto))
                .map(InvitacionesPorActo::getInvitaciones)
                .flatMap(Set::stream)
                .map(Invitacion::getInvitados)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    int compararPorCantidadDeInvitadosDeUnTipoDeZona(Acto acto, TipoDeZona tipo, Anfitrion anfitrion1, Anfitrion anfitrion2);
    
    default void agregarInvitados(Acto acto, Collection<Invitado> invitados) {
        invitados.forEach(invitado -> agregarInvitado(acto, invitado));
    }

    default void agregarInvitado(Acto acto, Invitado invitado) {
        agregarInvitado(acto, invitado, invitado.getInvitacion().getTipoDeZona());
    }

    default void agregarInvitado(Acto acto, Invitado invitado, TipoDeZona tipoDeZona) {
        if (invitado instanceof InvitadoFcse invitadoFcse && invitadoFcse.getAsisteDeUniforme()) {
            getInvitacionPorTipoDeZona(acto, TipoDeZona.ACOTADO).agregarInvitado(invitado);
        } else {
            getInvitacionPorTipoDeZona(acto, tipoDeZona).agregarInvitado(invitado);
        }
    }

    default void quitarInvitado(Acto acto, Invitado invitado) {
        if (invitado instanceof InvitadoFcse invitadoFcse && invitadoFcse.getAsisteDeUniforme()) {
            getInvitacionPorTipoDeZona(acto, TipoDeZona.ACOTADO).quitarInvitado(invitado);
        } else {
            getInvitacionPorTipoDeZona(acto, invitado.getInvitacion().getTipoDeZona()).agregarInvitado(invitado);
        }
    }

    default Invitacion getInvitacionPorTipoDeZona(Acto acto, TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo()
                .stream()
                .filter(e -> e.getActo() == acto)
                .map(InvitacionesPorActo::getInvitaciones)
                .flatMap(Collection::stream)
                .filter(e -> e.getTipoDeZona() == tipoDeZona)
                .findFirst().orElseThrow();
    }
}