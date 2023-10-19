package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.rmi.server.UID;

@Data
@EqualsAndHashCode(of = {"internalId", "implicaSalto", "implicaSaltoFila", "x", "y", "altura", "anchura"})
public class LocalidadImpl implements Localidad {
    private UID internalId;
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
    private Boolean implicaSaltoFila;
    private double x;
    private double y;
    private double altura;
    private double anchura;

    public LocalidadImpl() {
        internalId = new UID();
    }

    public void setZona(Zona zona) {
        if (this.zona != zona) {
            if (this.zona != null) {
                this.zona.quitarLocalidad(this);
            }
            this.zona = zona;
            if (zona != null) {
                zona.agregarLocalidad(this);
            }
        }
    }
}