package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.Set;

public interface Instalacion {
    String getNombre();

    void setNombre(String nombre);

    Set<Zona> getZonas();

    void setZonas(Set<Zona> zonas);

    Set<Acto> getActos();

    void setActos(Set<Acto> actos);

    Unidad getUnidad();

    void setUnidad(Unidad unidad);

    void agregarZona(Zona zona);

    void quitarZona(Zona zona);

    void agregarActo(Acto acto);

    void quitarActo(Acto acto);
}