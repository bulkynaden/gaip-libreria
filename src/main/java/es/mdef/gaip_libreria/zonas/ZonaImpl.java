package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(of = {"nombre", "tipoDeZona", "numeroLocalidades"})
@Data
public class ZonaImpl implements Zona {
    private List<Localidad> localidades = new ArrayList<>();
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

    public ZonaImpl() {
    }

    public void setLocalidades(List<Localidad> localidades) {
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
        if (localidad != null) {
            localidades.add(localidad);
            localidad.setZona(this);
        }
    }

    @Override
    public void quitarLocalidad(Localidad localidad) {
        localidades.remove(localidad);
        localidad.setZona(null);
    }
}