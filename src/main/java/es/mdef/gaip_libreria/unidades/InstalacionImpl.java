package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"nombre"})
public class InstalacionImpl implements Instalacion {
    private String nombre;
    private Set<Zona> zonas;
    private Set<Acto> actos;
    private Unidad unidad;

    public InstalacionImpl() {
        zonas = new HashSet<>();
        actos = new HashSet<>();
    }

    @Override
    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }

    @Override
    public void quitarZona(Zona zona) {
        zonas.remove(zona);
    }

    @Override
    public void agregarActo(Acto acto) {
        actos.add(acto);
    }

    @Override
    public void quitarActo(Acto acto) {
        actos.remove(acto);
    }
}