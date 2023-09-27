package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.Set;

/**
 * Interfaz que representa una instalación.
 * Define las operaciones básicas que una instalación debe tener.
 */
public interface Instalacion {

    /**
     * Obtiene el nombre de la instalación.
     *
     * @return el nombre de la instalación.
     */
    String getNombre();

    /**
     * Establece el nombre de la instalación.
     *
     * @param nombre el nuevo nombre de la instalación.
     */
    void setNombre(String nombre);

    /**
     * Obtiene las zonas asociadas a la instalación.
     *
     * @return un conjunto de zonas.
     */
    Set<Zona> getZonas();

    /**
     * Establece las zonas asociadas a la instalación.
     *
     * @param zonas el conjunto de zonas a establecer.
     */
    void setZonas(Set<Zona> zonas);

    /**
     * Obtiene los actos asociados a la instalación.
     *
     * @return un conjunto de actos.
     */
    Set<Acto> getActos();

    /**
     * Establece los actos asociados a la instalación.
     *
     * @param actos el conjunto de actos a establecer.
     */
    void setActos(Set<Acto> actos);

    /**
     * Obtiene la unidad asociada a la instalación.
     *
     * @return la unidad asociada.
     */
    Unidad getUnidad();

    /**
     * Establece la unidad asociada a la instalación.
     *
     * @param unidad la unidad a establecer.
     */
    void setUnidad(Unidad unidad);

    /**
     * Agrega una zona a la instalación.
     *
     * @param zona la zona a agregar.
     */
    void agregarZona(Zona zona);

    /**
     * Quita una zona de la instalación.
     *
     * @param zona la zona a quitar.
     */
    void quitarZona(Zona zona);

    /**
     * Agrega un acto a la instalación.
     *
     * @param acto el acto a agregar.
     */
    void agregarActo(Acto acto);

    /**
     * Quita un acto de la instalación.
     *
     * @param acto el acto a quitar.
     */
    void quitarActo(Acto acto);
}