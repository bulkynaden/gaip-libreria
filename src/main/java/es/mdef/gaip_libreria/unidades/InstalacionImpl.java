package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación de la interfaz {@link Instalacion}.
 * Representa una instalación con sus propiedades y relaciones.
 */
@Data
@EqualsAndHashCode(of = {"nombre", "unidad"})
public class InstalacionImpl implements Instalacion {
    private String nombre;
    @Getter
    private Unidad unidad;
    @Getter
    private Set<Zona> zonas = new HashSet<>();
    @Getter
    private Set<Acto> actos = new HashSet<>();

    /**
     * Establece la unidad asociada a la instalación.
     * Si la unidad es diferente de null, agrega esta instalación a la unidad.
     *
     * @param unidad la unidad a establecer.
     */
    public void setUnidad(Unidad unidad) {
        if (this.unidad != unidad) {
            if (this.unidad != null) {
                this.unidad.quitarInstalacion(this);
            }
            this.unidad = unidad;
            if (unidad != null) {
                unidad.agregarInstalacion(this);
            }
        }
    }

    /**
     * Establece las zonas asociadas a la instalación.
     * Limpia las zonas actuales y establece las nuevas zonas.
     *
     * @param zonas el conjunto de zonas a establecer.
     */
    public void setZonas(Set<Zona> zonas) {
        if (this.zonas != null) {
            this.zonas.clear();
        }
        if (zonas != null) {
            zonas.forEach(this::agregarZona);
        }
    }

    /**
     * Establece los actos asociados a la instalación.
     * Limpia los actos actuales y establece los nuevos actos.
     *
     * @param actos el conjunto de actos a establecer.
     */
    public void setActos(Set<Acto> actos) {
        if (this.actos != null) {
            this.actos.forEach(acto -> acto.setInstalacion(null));
            this.actos.clear();
        }
        if (actos != null) {
            actos.forEach(this::agregarActo);
        }
    }

    /**
     * Agrega una zona a la instalación y establece la relación bidireccional.
     *
     * @param zona la zona a agregar.
     */
    public void agregarZona(Zona zona) {
        if (zona != null && !zonas.contains(zona)) {
            zonas.add(zona);
            if (zona.getInstalacion() != this) {
                zona.setInstalacion(this);
            }
        }
    }

    /**
     * Quita una zona de la instalación y elimina la relación bidireccional.
     *
     * @param zona la zona a quitar.
     */
    public void quitarZona(Zona zona) {
        if (zona != null && !zonas.contains(zona)) {
            zonas.remove(zona);
            if (zona.getInstalacion() != this) {
                zona.setInstalacion(this);
            }
        }
    }

    /**
     * Agrega un acto a la instalación y establece la relación bidireccional.
     *
     * @param acto el acto a agregar.
     */
    public void agregarActo(Acto acto) {
        if (acto != null && !actos.contains(acto)) {
            actos.add(acto);
            if (acto.getInstalacion() != this) {
                acto.setInstalacion(this);
            }
        }
    }

    /**
     * Quita un acto de la instalación y elimina la relación bidireccional.
     *
     * @param acto el acto a quitar.
     */
    public void quitarActo(Acto acto) {
        if (acto != null && !actos.contains(acto)) {
            actos.remove(acto);
            if (acto.getInstalacion() != this) {
                acto.setInstalacion(null);
            }
        }
    }
}