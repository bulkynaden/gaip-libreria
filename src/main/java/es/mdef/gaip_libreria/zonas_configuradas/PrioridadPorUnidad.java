package es.mdef.gaip_libreria.zonas_configuradas;

/**
 * Representa la prioridad asignada a una unidad específica para una {@link ZonaConfigurada}.
 * <p>
 * Esta interfaz define los métodos para obtener y establecer la prioridad, la unidad y la zona configurada asociada.
 * La prioridad es un valor numérico que indica el nivel de prioridad de una unidad en una zona específica.
 * </p>
 */
public interface PrioridadPorUnidad {

    /**
     * Obtiene la prioridad de la unidad.
     *
     * @return el valor numérico de la prioridad.
     */
    int getPrioridad();

    /**
     * Establece la prioridad de la unidad.
     *
     * @param prioridad el valor numérico de la prioridad a establecer.
     */
    void setPrioridad(int prioridad);

    /**
     * Obtiene el nombre o identificador de la unidad.
     *
     * @return el nombre o identificador de la unidad.
     */
    String getUnidad();

    /**
     * Establece el nombre o identificador de la unidad.
     *
     * @param unidad el nombre o identificador de la unidad a establecer.
     */
    void setUnidad(String unidad);

    /**
     * Obtiene la {@link ZonaConfigurada} asociada a esta prioridad.
     *
     * @return la zona configurada asociada.
     */
    ZonaConfigurada getZona();

    /**
     * Establece la {@link ZonaConfigurada} asociada a esta prioridad.
     *
     * @param zona la zona configurada a asociar.
     */
    void setZona(ZonaConfigurada zona);
}