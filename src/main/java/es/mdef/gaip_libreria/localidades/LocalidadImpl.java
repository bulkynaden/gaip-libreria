package es.mdef.gaip_libreria.localidades;

import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link Localidad}.
 * Esta clase representa una localidad individual dentro de una zona, con propiedades geométricas y opciones de salto.
 */
@Data
@EqualsAndHashCode(of = {"implicaSalto", "implicaSaltoFila", "x", "y", "altura", "anchura"})
public class LocalidadImpl implements Localidad {
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
    private Boolean implicaSaltoFila;
    private double x;
    private double y;
    private double altura;
    private double anchura;

    /**
     * Establece la zona a la que pertenece esta localidad.
     * Mantiene la coherencia de la relación bidireccional entre la zona y sus localidades.
     *
     * @param zona la {@link Zona} a asociar con esta localidad.
     */
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