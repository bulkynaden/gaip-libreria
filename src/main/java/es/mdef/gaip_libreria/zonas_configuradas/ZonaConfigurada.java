package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.EstadoDeUnaLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
import es.mdef.gaip_libreria.zonas.Localidad;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.List;
import java.util.Set;

/**
 * Representa una zona específica que ha sido configurada para un {@link Acto} particular.
 * <p>
 * Una ZonaConfigurada contiene localidades específicas y prioridades por unidad que se han establecido para un acto.
 * Esta interfaz define los métodos para obtener y establecer la zona, el acto, las localidades configuradas y las prioridades por unidad.
 * </p>
 * <p>
 * Además, proporciona métodos para agregar y quitar localidades y prioridades de la configuración.
 * </p>
 */
public interface ZonaConfigurada {

    /**
     * Obtiene la {@link Zona} asociada a esta configuración.
     *
     * @return la zona asociada.
     */
    Zona<? extends Localidad> getZona();

    /**
     * Establece la {@link Zona} asociada a esta configuración.
     *
     * @param zona la zona a asociar.
     */
    void setZona(Zona<? extends Localidad> zona);

    /**
     * Obtiene el {@link Acto} asociado a esta configuración.
     *
     * @return el acto asociado.
     */
    Acto getActo();

    /**
     * Establece el {@link Acto} asociado a esta configuración.
     *
     * @param acto el acto a asociar.
     */
    void setActo(Acto acto);

    /**
     * Obtiene el conjunto de {@link LocalidadConfigurada} asociadas a esta configuración.
     *
     * @return las localidades configuradas asociadas.
     */
    List<LocalidadConfigurada> getLocalidades();

    /**
     * Establece el conjunto de {@link LocalidadConfigurada} asociadas a esta configuración.
     *
     * @param localidades las localidades configuradas a asociar.
     */
    void setLocalidades(List<LocalidadConfigurada> localidades);

    /**
     * Obtiene el conjunto de {@link PrioridadPorUnidad} asociadas a esta configuración.
     *
     * @return las prioridades por unidad asociadas.
     */
    Set<PrioridadPorUnidad> getPrioridades();

    /**
     * Establece el conjunto de {@link PrioridadPorUnidad} asociadas a esta configuración.
     *
     * @param prioridades las prioridades por unidad a asociar.
     */
    void setPrioridades(Set<PrioridadPorUnidad> prioridades);

    /**
     * Agrega una {@link PrioridadPorUnidad} a esta configuración.
     *
     * @param prioridad la prioridad por unidad a agregar.
     */
    void agregarPrioridad(PrioridadPorUnidad prioridad);

    /**
     * Quita una {@link PrioridadPorUnidad} de esta configuración.
     *
     * @param prioridad la prioridad por unidad a quitar.
     */
    void quitarPrioridad(PrioridadPorUnidad prioridad);

    /**
     * Agrega una {@link LocalidadConfigurada} a esta configuración.
     *
     * @param localidad la localidad configurada a agregar.
     */
    void agregarLocalidad(LocalidadConfigurada localidad);

    /**
     * Quita una {@link LocalidadConfigurada} de esta configuración.
     *
     * @param localidad la localidad configurada a quitar.
     */
    void quitarLocalidad(LocalidadConfigurada localidad);

    default int getNumeroLocalidadesTotales() {
        return getLocalidades().size();
    }

    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == estado || l.getEstadoOcupacionLocalidad() == estado)
                .count();
    }

    default int getNumeroLocalidadesParaRepartir() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.NORMAL && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE)
                .count();
    }
}