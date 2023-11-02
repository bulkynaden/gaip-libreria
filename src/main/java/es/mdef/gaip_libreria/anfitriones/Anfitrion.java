package es.mdef.gaip_libreria.anfitriones;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.*;

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
     * Establece los actos asociados al anfitrión.
     *
     * @param actos Conjunto de actos.
     */
    <T extends Acto> void setActos(Set<T> actos);

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
    <T extends InvitacionesPorActo> void setInvitacionesPorActo(Set<T> invitacionesPorActo);

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


    /**
     * Agrega un conjunto de asignables al acto especificado.
     *
     * @param acto       Acto al cual agregar los asignables.
     * @param asignables Conjunto de asignables a agregar.
     */
    default <T extends Asignable> void agregarAsignables(Acto acto, Collection<T> asignables, boolean superarMaximo) {
        asignables.forEach(asignable -> agregarAsignable(acto, asignable, superarMaximo));
    }

    /**
     * Agrega un asignable al acto especificado.
     *
     * @param acto      Acto al cual agregar el asignable.
     * @param asignable Asignable a agregar.
     */
    default void agregarAsignable(Acto acto, Asignable asignable, boolean superarMaximo) {
        if (asignable.getInvitacion() == null) {
            agregarAsignable(acto, asignable, null, superarMaximo);
        } else {
            agregarAsignable(acto, asignable, asignable.getInvitacion().getTipoDeZona(), superarMaximo);
        }
    }

    /**
     * Agrega un asignable al acto especificado.
     *
     * @param acto       Acto al cual agregar el asignable.
     * @param asignable  Asignable a agregar.
     * @param tipoDeZona Tipo de zona al que pertenece el asignable.
     */
    default void agregarAsignable(Acto acto, Asignable asignable, TipoDeZona tipoDeZona, boolean superarMaximo) {
        if (tipoDeZona == null) {
            getInvitacionPorTipoDeZona(acto, TipoDeZona.LISTA_DE_ESPERA).agregarAsignable(asignable, superarMaximo);
        }

        if (asignable instanceof InvitadoFcse invitadoFcse && invitadoFcse.getAsisteDeUniforme()) {
            if (asignable.getInvitacion() != null && asignable.getInvitacion().getTipoDeZona() != TipoDeZona.LISTA_DE_ESPERA) {
                getInvitacionPorTipoDeZona(acto, TipoDeZona.ACOTADO).agregarAsignable(asignable, superarMaximo);
            } else {
                getInvitacionPorTipoDeZona(acto, TipoDeZona.LISTA_DE_ESPERA).agregarAsignable(asignable, superarMaximo);
            }
        } else {
            getInvitacionPorTipoDeZona(acto, tipoDeZona).agregarAsignable(asignable, superarMaximo);
        }

        if (asignable instanceof Invitado invitado) {
            if (invitado.getCoche() != null) {
                agregarCoche(acto, invitado.getCoche());
            }
        }
    }

    default void agregarCoche(Acto acto, Coche coche) {
        getInvitacionPorTipoDeZona(acto, TipoDeZona.PARKING).agregarAsignable(coche, false);
    }

    default void quitarAsignable(Acto acto, Asignable asignable) {
        getInvitacionPorTipoDeZona(acto, asignable.getInvitacion().getTipoDeZona()).quitarAsignable(asignable);
    }

    /**
     * Devuelve el número de asignables de un acto en una zona específica.
     *
     * @param acto       Acto específico.
     * @param tipoDeZona Tipo de zona específico.
     * @return Número de asignables de un acto en una zona específica.
     */
    default long getNumeroAsignablesDeUnActoPorZona(Acto acto, TipoDeZona tipoDeZona) {
        return getAsignablesAUnActoPorZona(acto, tipoDeZona).size();
    }

    /**
     * Devuelve el número de asignables sin asignar de un acto en una zona específica.
     *
     * @param acto       Acto específico.
     * @param tipoDeZona Tipo de zona específico.
     * @return Número de asignables sin asignar de un acto en una zona específica.
     */
    default long getNumeroAsignablesSinAsignarDeUnActoPorZona(Acto acto, TipoDeZona tipoDeZona) {
        return getAsignablesSinAsignarDeUnActoPorZona(acto, tipoDeZona).size();
    }

    /**
     * Devuelve los asignables de un acto sin asignar.
     *
     * @param acto Acto específico.
     * @return Conjunto de asignables sin asignar de un acto.
     */
    default Set<Asignable> getAsignablesSinAsignarDeUnActo(Acto acto) {
        return getInvitacionesDeActo(acto).stream()
                .map(Invitacion::getAsignables)
                .flatMap(Set::stream)
                .filter(asignable -> asignable.getLocalidad() == null)
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve los asignables de un acto sin asignar en una zona específica.
     *
     * @param acto       Acto específico.
     * @param tipoDeZona Tipo de zona específica.
     * @return Conjunto de asignables sin asignar de un acto en una zona específica.
     */
    default Set<Asignable> getAsignablesSinAsignarDeUnActoPorZona(Acto acto, TipoDeZona tipoDeZona) {
        return getInvitacionesDeActo(acto).stream()
                .filter(e -> e.getTipoDeZona() == tipoDeZona)
                .map(Invitacion::getAsignables)
                .flatMap(Set::stream)
                .filter(asignable -> asignable.getLocalidad() == null)
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve los asignables de un acto.
     *
     * @param acto Acto específico.
     * @return Conjunto de asignables de un acto.
     */
    default Set<Asignable> getAsignablesAUnActo(Acto acto) {
        return getInvitacionesDeActo(acto).stream()
                .map(Invitacion::getAsignables)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve los asignables de un acto en una zona específica.
     *
     * @param acto       Acto específico.
     * @param tipoDeZona Tipo de zona específica.
     * @return Conjunto de asignables de un acto en una zona específica.
     */
    default Set<Asignable> getAsignablesAUnActoPorZona(Acto acto, TipoDeZona tipoDeZona) {
        return getInvitacionesDeActo(acto).stream()
                .filter(e -> e.getTipoDeZona() == tipoDeZona)
                .map(Invitacion::getAsignables)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve las invitaciones de un acto.
     *
     * @param acto Acto específico.
     * @return Conjunto de invitaciones de un acto.
     */
    private Set<Invitacion> getInvitacionesDeActo(Acto acto) {
        return this.getInvitacionesPorActo().stream()
                .filter(e -> e.getActo().equals(acto))
                .map(InvitacionesPorActo::getInvitaciones)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve la invitación por tipo de zona de un acto.
     *
     * @param acto       Acto específico.
     * @param tipoDeZona Tipo de zona específica.
     * @return Invitación por tipo de zona de un acto.
     */
    default Invitacion getInvitacionPorTipoDeZona(Acto acto, TipoDeZona tipoDeZona) {
        return getInvitacionesDeActo(acto).stream()
                .filter(e -> e.getTipoDeZona() == tipoDeZona)
                .findFirst().orElseThrow();
    }

    /**
     * Compara dos anfitriones por la cantidad de asignables de un tipo de zona en un acto específico.
     *
     * @param acto       Acto específico.
     * @param tipo       Tipo de zona específico.
     * @param anfitrion1 Primer anfitrión a comparar.
     * @param anfitrion2 Segundo anfitrión a comparar.
     * @return Valor negativo, cero o positivo si el primer anfitrión tiene menos, igual o más asignables que el segundo anfitrión, respectivamente.
     */
    int compararPorCantidadDeAsignablesDeUnTipoDeZona(Acto acto, TipoDeZona tipo, Anfitrion anfitrion1, Anfitrion anfitrion2);
}