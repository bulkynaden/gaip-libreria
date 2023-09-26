package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import lombok.Data;

@Data
public class LocalidadImpl implements Localidad {
    private Zona zona;
    private EstadoLocalidad estado;
    private Localidad siguienteLocalidad;
    private Boolean implicaSalto;
}