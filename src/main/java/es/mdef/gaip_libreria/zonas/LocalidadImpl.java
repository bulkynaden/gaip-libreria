package es.mdef.gaip_libreria.zonas;

import lombok.Data;

@Data
public class LocalidadImpl implements Localidad {
    private Zona zona;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
}