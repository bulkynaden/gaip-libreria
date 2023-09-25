package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;

import java.util.Set;

@Data
public class InstalacionImpl implements Instalacion {
    private String nombre;
    private Set<Zona> zonas;
}