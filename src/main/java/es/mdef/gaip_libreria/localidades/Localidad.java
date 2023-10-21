package es.mdef.gaip_libreria.localidades;

import es.mdef.gaip_libreria.herramientas.Figura;
import es.mdef.gaip_libreria.zonas.Zona;

/**
 * Representa una localidad individual dentro de una {@link Zona}.
 * <p>
 * Una localidad es un punto específico o asiento dentro de una zona. Esta interfaz define los métodos para obtener y establecer
 * la localidad siguiente, la zona a la que pertenece y si la localidad implica un salto (por ejemplo, un pasillo o un espacio entre asientos).
 * </p>
 * <p>
 * Las localidades son útiles para representar y gestionar asientos o puntos específicos dentro de una zona
 * </p>
 */
public interface Localidad extends Figura {

    /**
     * Obtiene la localidad que sigue a esta localidad en la secuencia.
     *
     * @return la siguiente localidad en la secuencia, o null si esta es la última localidad.
     */
    Localidad getSiguienteLocalidad();

    /**
     * Establece la localidad que sigue a esta localidad en la secuencia.
     *
     * @param siguienteLocalidad la localidad a establecer como siguiente.
     */
    void setSiguienteLocalidad(Localidad siguienteLocalidad);

    /**
     * Obtiene la zona a la que pertenece esta localidad.
     *
     * @return la zona asociada a esta localidad.
     */
    Zona getZona();

    /**
     * Establece la zona a la que pertenece esta localidad.
     *
     * @param zona la zona a asociar con esta localidad.
     */
    void setZona(Zona zona);

    /**
     * Indica si esta localidad implica un salto, como un pasillo o un espacio entre asientos.
     *
     * @return true si la localidad implica un salto, false en caso contrario.
     */
    Boolean getImplicaSalto();

    /**
     * Establece si esta localidad implica un salto.
     *
     * @param implicaSalto true para indicar que la localidad implica un salto, false en caso contrario.
     */
    void setImplicaSalto(Boolean implicaSalto);

    Boolean getImplicaSaltoFila();

    void setImplicaSaltoFila(Boolean implicaSaltoFila);
}