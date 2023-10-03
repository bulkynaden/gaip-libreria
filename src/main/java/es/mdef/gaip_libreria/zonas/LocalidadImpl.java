package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "implicaSalto")
public class LocalidadImpl implements Localidad {
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
    private Boolean implicaSaltoFila;
    private double x;
    private double y;
    private double altura;
    private double anchura;
}