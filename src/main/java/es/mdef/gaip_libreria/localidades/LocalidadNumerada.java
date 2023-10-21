package es.mdef.gaip_libreria.localidades;

import es.mdef.gaip_libreria.zonas.ZonaNumerada;

/**
 * Representa una localidad numerada dentro de una {@link ZonaNumerada}.
 * <p>
 * Una localidad numerada es un punto específico o asiento dentro de una zona que tiene un número asignado. Esta interfaz define los métodos
 * para obtener y establecer el número de la localidad.
 * </p>
 * <p>
 * Las localidades numeradas son útiles para representar y gestionar asientos numerados en contextos como teatros, estadios, auditorios,
 * entre otros, donde cada asiento tiene un número único para identificación.
 * </p>
 */
public interface LocalidadNumerada extends Localidad {

    /**
     * Obtiene el número asignado a esta localidad numerada.
     *
     * @return el número de la localidad.
     */
    int getNumero();

    /**
     * Establece el número para esta localidad numerada.
     *
     * @param numero el número a asignar a la localidad.
     */
    void setNumero(int numero);
}