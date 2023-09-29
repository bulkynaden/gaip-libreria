package es.mdef.gaip_libreria.zonas_configuradas;


import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.Set;

public interface ZonaConfigurada {
    Zona getZona();

    void setZona(Zona zona);

    Acto getActo();

    void setActo(Acto acto);

    Set<LocalidadConfigurada> getLocalidades();

    void setLocalidades(Set<LocalidadConfigurada> localidades);

    Set<PrioridadPorUnidad> getPrioridades();

    void setPrioridades(Set<PrioridadPorUnidad> prioridades);

    void agregarPrioridad(PrioridadPorUnidad prioridad);

    void quitarPrioridad(PrioridadPorUnidad prioridad);

    void agregarLocalidad(LocalidadConfigurada localidad);

    void quitarLocalidad(LocalidadConfigurada localidad);
}