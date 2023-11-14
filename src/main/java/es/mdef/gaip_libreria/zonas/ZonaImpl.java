package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(of = {"nombre", "tipoDeZona", "numeroLocalidades"})
@Setter
public class ZonaImpl implements Zona {
    @Getter
    private Set<Localidad> localidades = new HashSet<>();
    @Getter 
    private String nombre;
    @Getter
    private int numeroLocalidades;
    @Getter
    private TipoDeZona tipoDeZona;
    @Getter
    private Instalacion instalacion;
    @Getter
    private double x;
    @Getter
    private double y;
    @Getter
    private double altura;
    @Getter
    private double anchura;
    @Getter 
    private double[] xInicioFilas;
    @Getter
    private double yInicioColumna;
    @Getter
    private double saltoX;
    @Getter
    private double saltoHuecoX;
    @Getter
    private double saltoY;
    private boolean paraMilitares;
    @Override
    public boolean getParaMilitares() {
        return paraMilitares;
    }

    public void setLocalidades(Set<Localidad> localidades) {
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