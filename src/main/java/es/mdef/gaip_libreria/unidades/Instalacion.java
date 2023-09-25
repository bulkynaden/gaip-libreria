package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.zonas.Zona;

import java.util.Set;

public interface Instalacion {
    String getNombre();

    void setNombre(String nombre);

    Set<Zona> getZonas();

    void setZonas(Set<Zona> zonas);
}