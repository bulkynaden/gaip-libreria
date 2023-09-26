package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(of = {"nombre"})
public class InstalacionImpl implements Instalacion {
    private String nombre;
    private Set<Zona> zonas;

    @Override
    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }

    @Override
    public void quitarZona(Zona zona) {
        zonas.remove(zona);
    }
}