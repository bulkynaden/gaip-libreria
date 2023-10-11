package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import lombok.Data;

@Data
public class CocheImpl implements Coche {
    private Invitado invitado;
    private String matricula;
    private String color;
    private String modelo;
    private String marca;
    private LocalidadConfigurada localidad;
}