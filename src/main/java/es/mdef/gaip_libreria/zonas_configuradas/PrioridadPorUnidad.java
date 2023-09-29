package es.mdef.gaip_libreria.zonas_configuradas;

public interface PrioridadPorUnidad {
    int getPrioridad();

    void setPrioridad(int prioridad);

    String getUnidad();

    void setUnidad(String unidad);

    ZonaConfigurada getZona();

    void setZona(ZonaConfigurada zona);
}