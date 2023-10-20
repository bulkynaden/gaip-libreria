package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashSet;


@EqualsAndHashCode(of = {"nombre", "tipoDeZona", "numeroLocalidades"})
@Data
public class ZonaImpl implements Zona {
    private LinkedHashSet<Localidad> localidades = new LinkedHashSet<>();
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

    public void setLocalidades(LinkedHashSet<Localidad> localidades) {
        if (this.localidades != localidades) {
            this.localidades.forEach(localidad -> localidad.setZona(null));
            if (localidades != null) {
                localidades.forEach(localidad -> localidad.setZona(this));
            }
            this.localidades = localidades;
        }
    }

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