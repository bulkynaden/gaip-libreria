package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


/**
 * Implementación concreta de la interfaz {@link Zona}.
 * Esta clase representa una zona física dentro de una {@link Instalacion} con características geométricas y de prioridad.
 */
@EqualsAndHashCode(of = {"nombre", "tipoDeZona", "numeroLocalidades"})
@Data
public class ZonaImpl implements Zona {
    private Set<Localidad> localidades = new HashSet<>();
    private String nombre;
    private int numeroLocalidades;
    private TipoDeZona tipoDeZona;
    private Instalacion instalacion;
    private double x;
    private double y;
    private double altura;
    private double anchura;
    private double[] xInicioFilas;
    private double yInicioColumna;
    private double saltoX;
    private double saltoHuecoX;
    private double saltoY;
    private int prioridadParkingMilitares;

    /**
     * Establece el conjunto de {@link Localidad} en esta zona, actualizando la asociación bidireccional entre la zona y sus localidades.
     *
     * @param localidades un conjunto de {@link Localidad} para asociar con esta zona.
     */
    public void setLocalidades(Set<Localidad> localidades) {
        if (this.localidades != localidades) {
            this.localidades.forEach(localidad -> localidad.setZona(null));
            if (localidades != null) {
                localidades.forEach(localidad -> localidad.setZona(this));
            }
            this.localidades = localidades;
        }
    }

    /**
     * Establece la {@link Instalacion} a la que pertenece esta zona, actualizando la relación bidireccional entre la instalación y sus zonas.
     *
     * @param instalacion la {@link Instalacion} a asociar con esta zona.
     */
    public void setInstalacion(Instalacion instalacion) {
        if (this.instalacion != instalacion) {
            if (this.instalacion != null) {
                this.instalacion.quitarZona(this);
            }
            this.instalacion = instalacion;
            if (instalacion != null) {
                instalacion.agregarZona(this);
            }
        }
    }

    /**
     * Agrega una {@link Localidad} a esta zona, asegurando la coherencia en la relación bidireccional entre la zona y la localidad.
     *
     * @param localidad la {@link Localidad} a agregar.
     * @throws IllegalArgumentException si la localidad es nula.
     */
    @Override
    public void agregarLocalidad(Localidad localidad) {
        if (localidad == null) {
            throw new IllegalArgumentException("La localidad no puede ser nula.");
        }
        if (!localidades.contains(localidad)) {
            localidades.add(localidad);
            if (localidad.getZona() != this) {
                localidad.setZona(this);
            }
        }
    }

    /**
     * Quita una {@link Localidad} de esta zona, manteniendo la coherencia en la relación bidireccional entre la zona y la localidad.
     *
     * @param localidad la {@link Localidad} a quitar.
     * @throws IllegalArgumentException si la localidad es nula.
     */
    @Override
    public void quitarLocalidad(Localidad localidad) {
        if (localidad == null) {
            throw new IllegalArgumentException("La localidad no puede ser nula.");
        }
        if (localidades.contains(localidad)) {
            localidades.remove(localidad);
            if (localidad.getZona() == this) {
                localidad.setZona(null);
            }
        }
    }
}