package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "implicaSalto")
public class LocalidadImpl implements Localidad {
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
}