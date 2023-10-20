package es.mdef.gaip_libreria.zonas;

/**
 * Representa una zona numerada dentro de una instalación.
 * <p>
 * Una ZonaNumerada es una especialización de {@link Zona} que tiene un punto de inicio para la numeración de sus localidades.
 * Esta interfaz define los métodos para obtener y establecer el inicio de la numeración, además de heredar todos los métodos de la interfaz {@link Zona}.
 * </p>
 * <p>
 * Las zonas numeradas son útiles cuando se desea tener un control más detallado sobre la numeración de las localidades dentro de una zona específica.
 * </p>
 */
public interface ZonaNumerada extends Zona<LocalidadNumerada> {

    /**
     * Obtiene el punto de inicio de la numeración de las localidades en esta zona numerada.
     *
     * @return el inicio de la numeración.
     */
    int getInicioNumeracion();

    /**
     * Establece el punto de inicio de la numeración de las localidades en esta zona numerada.
     *
     * @param inicioNumeracion el inicio de la numeración a establecer.
     */
    void setInicioNumeracion(int inicioNumeracion);
}