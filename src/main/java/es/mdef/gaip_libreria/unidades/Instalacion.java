package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Localidad;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.List;
import java.util.Set;

/**
 * Representa una instalación dentro del sistema.
 * <p>
 * Una instalación es una entidad que puede contener múltiples zonas y puede albergar varios actos.
 * Esta interfaz define las operaciones básicas que una instalación debe tener, incluyendo la gestión de zonas, actos y su unidad asociada.
 * </p>
 * <p>
 * Las instalaciones pueden ser utilizadas para representar patios de armas, recintos, salas, o cualquier otra estructura física donde se realicen actos.
 * </p>
 */
public interface Instalacion {

    /**
     * Obtiene el nombre identificativo de la instalación.
     *
     * @return el nombre de la instalación.
     */
    String getNombre();

    /**
     * Establece un nuevo nombre identificativo para la instalación.
     *
     * @param nombre el nuevo nombre de la instalación.
     */
    void setNombre(String nombre);

    /**
     * Obtiene el conjunto de zonas que están contenidas en esta instalación.
     *
     * @return un conjunto de zonas pertenecientes a la instalación.
     */
    List<Zona<? extends Localidad>> getZonas();

    /**
     * Asocia un conjunto de zonas a esta instalación.
     *
     * @param zonas el conjunto de zonas a asociar.
     */
    void setZonas(List<Zona<? extends Localidad>> zonas);

    /**
     * Obtiene el conjunto de actos que se llevarán a cabo o se han llevado a cabo en esta instalación.
     *
     * @return un conjunto de actos asociados a la instalación.
     */
    Set<Acto> getActos();

    /**
     * Asocia un conjunto de actos a esta instalación.
     *
     * @param actos el conjunto de actos a asociar.
     */
    void setActos(Set<Acto> actos);

    /**
     * Obtiene la unidad organizativa a la que está asociada esta instalación.
     *
     * @return la unidad asociada a la instalación.
     */
    Unidad getUnidad();

    /**
     * Asocia una unidad organizativa a esta instalación.
     *
     * @param unidad la unidad a asociar.
     */
    void setUnidad(Unidad unidad);

    /**
     * Agrega una zona específica a la instalación.
     * <p>
     * Si la zona ya está asociada a la instalación, no se realizará ninguna acción.
     * </p>
     *
     * @param zona la zona a agregar a la instalación.
     */
    void agregarZona(Zona<? extends Localidad> zona);

    /**
     * Desasocia una zona específica de la instalación.
     * <p>
     * Si la zona no está asociada a la instalación, no se realizará ninguna acción.
     * </p>
     *
     * @param zona la zona a desasociar de la instalación.
     */
    void quitarZona(Zona<? extends Localidad> zona);

    /**
     * Agrega un acto específico a la instalación.
     * <p>
     * Si el acto ya está asociado a la instalación, no se realizará ninguna acción.
     * </p>
     *
     * @param acto el acto a agregar a la instalación.
     */
    void agregarActo(Acto acto);

    /**
     * Desasocia un acto específico de la instalación.
     * <p>
     * Si el acto no está asociado a la instalación, no se realizará ninguna acción.
     * </p>
     *
     * @param acto el acto a desasociar de la instalación.
     */
    void quitarActo(Acto acto);
}