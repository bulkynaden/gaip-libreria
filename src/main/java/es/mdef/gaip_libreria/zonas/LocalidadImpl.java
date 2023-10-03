package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.herramientas.Figura;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "implicaSalto")
public class LocalidadImpl implements Localidad, Figura {
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
    private double x;
    private double y;
    private double altura;
    private double anchura;
}