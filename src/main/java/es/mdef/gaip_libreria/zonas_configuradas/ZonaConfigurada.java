package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.EstadoDeUnaLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
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
     * @return la zona asociada, nunca es {@code null}.
     */
    Zona getZona();

    /**
     * Establece la {@link Zona} asociada a esta configuración.
     *
     * @param zona la zona a asociar, no debe ser {@code null}.
     */
    void setZona(Zona zona);

    /**
     * Obtiene el {@link Acto} asociado a esta configuración.
     *
     * @return el acto asociado, puede ser {@code null} si no se ha establecido.
     */
    Acto getActo();

    /**
     * Establece el {@link Acto} asociado a esta configuración.
     *
     * @param acto el acto a asociar, no debe ser {@code null}.
     */
    void setActo(Acto acto);

    /**
     * Obtiene la lista de {@link LocalidadConfigurada} asociadas a esta configuración.
     *
     * @return una lista de localidades configuradas, nunca es {@code null}.
     */
    List<LocalidadConfigurada> getLocalidades();

    /**
     * Establece la lista de {@link LocalidadConfigurada} asociadas a esta configuración.
     *
     * @param localidades las localidades configuradas a asociar, no debe ser {@code null}.
     */
    void setLocalidades(List<LocalidadConfigurada> localidades);

    /**
     * Obtiene el conjunto de {@link PrioridadPorUnidad} asociadas a esta configuración.
     *
     * @return un conjunto de prioridades por unidad, nunca es {@code null}.
     */
    Set<PrioridadPorUnidad> getPrioridades();

    /**
     * Establece el conjunto de {@link PrioridadPorUnidad} asociadas a esta configuración.
     *
     * @param prioridades las prioridades por unidad a asociar, no debe ser {@code null}.
     */
    void setPrioridades(Set<PrioridadPorUnidad> prioridades);

    /**
     * Agrega una {@link PrioridadPorUnidad} a esta configuración.
     *
     * @param prioridad la prioridad por unidad a agregar, no debe ser {@code null}.
     */
    void agregarPrioridad(PrioridadPorUnidad prioridad);

    /**
     * Quita una {@link PrioridadPorUnidad} de esta configuración.
     *
     * @param prioridad la prioridad por unidad a quitar, no debe ser {@code null}.
     */
    void quitarPrioridad(PrioridadPorUnidad prioridad);

    /**
     * Agrega una {@link LocalidadConfigurada} a esta configuración.
     *
     * @param localidad la localidad configurada a agregar, no debe ser {@code null}.
     */
    void agregarLocalidad(LocalidadConfigurada localidad);

    /**
     * Quita una {@link LocalidadConfigurada} de esta configuración.
     *
     * @param localidad la localidad configurada a quitar, no debe ser {@code null}.
     */
    void quitarLocalidad(LocalidadConfigurada localidad);

    /**
     * Obtiene el número total de localidades en la zona asociada.
     *
     * @return el número total de localidades en la zona.
     */
    default int getNumeroLocalidadesTotales() {
        return getZona().getNumeroLocalidades();
    }

    /**
     * Calcula el número de localidades en un estado específico.
     *
     * @param estado el estado de una localidad para filtrar.
     * @return el número de localidades que coinciden con el estado dado.
     */
    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == estado || l.getEstadoOcupacionLocalidad() == estado)
                .count();
    }

    /**
     * Obtiene el número de localidades que están disponibles para ser repartidas.
     * Considera como disponibles aquellas localidades que están en estado normal y libre.
     *
     * @return el número de localidades disponibles para repartir.
     */
    default int getNumeroLocalidadesParaRepartir() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.NORMAL && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE)
                .count();
    }

    /**
     * Obtiene una lista de localidades que aún no han sido asignadas.
     * Considera como no asignadas aquellas localidades en estado normal y libre.
     *
     * @return una lista de localidades sin asignar.
     */
    default List<LocalidadConfigurada> getLocalidadesSinAsignar() {
        return getLocalidades()
                .stream()
                .filter(localidadConfigurada -> localidadConfigurada.getEstadoLocalidad() == EstadoLocalidad.NORMAL && localidadConfigurada.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE)
                .toList();
    }

    /**
     * Calcula el número de localidades normales y libres.
     *
     * @return el número de localidades en estado normal y libre.
     */
    default int getNumeroLocalidadesNormalesLibres() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.NORMAL
                        && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE)
                .count();
    }

    /**
     * Calcula el número de localidades reservadas y libres.
     *
     * @return el número de localidades en estado reservado y libre.
     */
    default int getNumeroLocalidadesReservadasLibres() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.RESERVADA
                        && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.LIBRE)
                .count();
    }

    /**
     * Calcula el número de localidades normales y ocupadas.
     *
     * @return el número de localidades en estado normal y ocupada.
     */
    default int getNumeroLocalidadesNormalesOcupadas() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.NORMAL
                        && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.OCUPADA)
                .count();
    }

    /**
     * Calcula el número de localidades reservadas y ocupadas.
     *
     * @return el número de localidades en estado reservado y ocupada.
     */
    default int getNumeroLocalidadesReservadasOcupadas() {
        return (int) getLocalidades().stream()
                .filter(l -> l.getEstadoLocalidad() == EstadoLocalidad.RESERVADA
                        && l.getEstadoOcupacionLocalidad() == EstadoOcupacionLocalidad.OCUPADA)
                .count();
    }
}