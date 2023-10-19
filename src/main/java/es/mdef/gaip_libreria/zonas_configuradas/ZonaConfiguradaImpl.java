package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Clase que representa una zona específica configurada con sus propiedades asociadas y las relaciones con otras entidades.
 * Una zona configurada puede tener múltiples localidades configuradas y prioridades por unidad.
 * Esta clase implementa la interfaz {@link ZonaConfigurada}.
 */
@Data
@EqualsAndHashCode(of = "acto")
public class ZonaConfiguradaImpl implements ZonaConfigurada {
    private final Set<LocalidadConfigurada> localidades = new LinkedHashSet<>();
    private final Set<PrioridadPorUnidad> prioridades = new HashSet<>();
    private Zona zona;
    private Acto acto;

    /**
     * Asocia un acto a la zona configurada. Si la zona ya estaba asociada a otro acto,
     * se elimina esa asociación previa. Establece la relación bidireccional entre la zona configurada y el acto.
     *
     * @param acto El acto a asociar con la zona configurada. No puede ser nulo.
     * @throws IllegalArgumentException si el acto es nulo.
     */
    public void setActo(Acto acto) {
        if (this.acto != acto) {
            if (this.acto != null) {
                this.acto.quitarZonaConfigurada(this);
            }
            this.acto = acto;
            if (acto != null) {
                if (!acto.getZonas().contains(this)) {
                    acto.agregarZonaConfigurada(this);
                }
            }
        }
    }

    /**
     * Establece las localidades configuradas para la zona y mantiene la coherencia bidireccional.
     *
     * @param localidades El conjunto de localidades configuradas para la zona.
     */
    public void setLocalidades(Set<LocalidadConfigurada> localidades) {
        if (this.localidades != localidades) {
            this.localidades.forEach(localidad -> localidad.setZonaConfigurada(null));
            this.localidades.clear();
            if (localidades != null) {
                localidades.forEach(this::agregarLocalidad);
            }
        }
    }

    /**
     * Agrega una localidad configurada a la zona y establece la relación bidireccional entre la zona y la localidad.
     *
     * @param localidad La localidad configurada a agregar a la zona. No puede ser nula.
     * @throws IllegalArgumentException si la localidad es nula.
     */
    public void agregarLocalidad(LocalidadConfigurada localidad) {
        if (localidad == null) {
            throw new IllegalArgumentException("La localidad no puede ser nula.");
        }
        if (!localidades.contains(localidad)) {
            localidades.add(localidad);
            if (localidad.getZonaConfigurada() != this) {
                localidad.setZonaConfigurada(this);
            }
        }
    }

    /**
     * Elimina una localidad configurada de la zona y rompe la relación bidireccional entre la zona y la localidad.
     *
     * @param localidad La localidad configurada a eliminar de la zona. No puede ser nula.
     * @throws IllegalArgumentException si la localidad es nula.
     */
    public void quitarLocalidad(LocalidadConfigurada localidad) {
        if (localidad == null) {
            throw new IllegalArgumentException("La localidad no puede ser nula.");
        }
        if (localidades.contains(localidad)) {
            localidades.remove(localidad);
            if (localidad.getZonaConfigurada() == this) {
                localidad.setZonaConfigurada(null);
            }
        }
    }

    /**
     * Establece las prioridades por unidad para la zona y mantiene la coherencia bidireccional.
     *
     * @param prioridades El conjunto de prioridades por unidad para la zona.
     */
    public void setPrioridades(Set<PrioridadPorUnidad> prioridades) {
        if (this.prioridades != prioridades) {

            Set<PrioridadPorUnidad> copyOfPrioridades = new HashSet<>(this.prioridades);

            copyOfPrioridades.forEach(prioridad -> prioridad.setZona(null));

            this.prioridades.clear();

            if (prioridades != null) {
                prioridades.forEach(this::agregarPrioridad);
            }
        }
    }

    /**
     * Agrega una prioridad por unidad a la zona y establece la relación bidireccional entre la zona y la prioridad.
     *
     * @param prioridad La prioridad por unidad a agregar a la zona. No puede ser nula.
     * @throws IllegalArgumentException si la prioridad es nula.
     */
    public void agregarPrioridad(PrioridadPorUnidad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad no puede ser nula.");
        }
        if (!prioridades.contains(prioridad) && prioridades.stream().noneMatch(e -> Objects.equals(e.getUnidad(), prioridad.getUnidad()))) {
            prioridades.add(prioridad);
            if (prioridad.getZona() != this) {
                prioridad.setZona(this);
            }
        }
    }

    /**
     * Elimina una prioridad por unidad de la zona y rompe la relación bidireccional entre la zona y la prioridad.
     *
     * @param prioridad La prioridad por unidad a eliminar de la zona. No puede ser nula.
     * @throws IllegalArgumentException si la prioridad es nula.
     */
    public void quitarPrioridad(PrioridadPorUnidad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad no puede ser nula.");
        }
        if (prioridades.contains(prioridad)) {
            prioridades.remove(prioridad);
            if (prioridad.getZona() == this) {
                prioridad.setZona(null);
            }
        }
    }
}