package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.herramientas.Figura;
import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.unidades.Instalacion;

import java.util.Set;

/**
 * Representa una zona dentro de una {@link Instalacion}.
 * <p>
 * Una Zona es un área específica dentro de una instalación que tiene un
 * conjunto de localidades y un tipo específico.
 * Esta interfaz define los métodos para obtener y establecer el nombre, el
 * número de localidades, las localidades individuales, el tipo de zona y la
 * instalación asociada.
 * </p>
 * <p>
 * Además, proporciona métodos para agregar y quitar localidades de la zona.
 * </p>
 */
public interface Zona extends Figura {

    /**
     * Obtiene la prioridad de la zona para el estacionamiento de militares.
     *
     * @return el valor de la prioridad para el estacionamiento de militares.
     */
    int getPrioridadParkingMilitares();

    /**
     * Establece la prioridad de la zona para el estacionamiento de militares.
     *
     * @param prioridadParkingMilitares el valor de la prioridad a establecer.
     */
    void setPrioridadParkingMilitares(int prioridadParkingMilitares);

    /**
     * Obtiene el nombre de la zona.
     *
     * @return el nombre de la zona.
     */
    String getNombre();

    /**
     * Establece el nombre de la zona.
     *
     * @param nombre el nombre a establecer.
     */
    void setNombre(String nombre);

    /**
     * Obtiene el número total de localidades en la zona.
     *
     * @return el número de localidades.
     */
    int getNumeroLocalidades();

    /**
     * Establece el número total de localidades en la zona.
     *
     * @param numeroLocalidades el número de localidades a establecer.
     */
    void setNumeroLocalidades(int numeroLocalidades);

    /**
     * Obtiene el conjunto de {@link Localidad} asociadas a esta zona.
     *
     * @return las localidades asociadas.
     */
    Set<Localidad> getLocalidades();

    /**
     * Establece el conjunto de {@link Localidad} asociadas a esta zona.
     *
     * @param localidades las localidades a asociar.
     */
    void setLocalidades(Set<Localidad> localidades);

    /**
     * Obtiene el {@link TipoDeZona} asociado a esta zona.
     *
     * @return el tipo de zona asociado.
     */
    TipoDeZona getTipoDeZona();

    /**
     * Establece el {@link TipoDeZona} asociado a esta zona.
     *
     * @param tipoDeZona el tipo de zona a establecer.
     */
    void setTipoDeZona(TipoDeZona tipoDeZona);

    /**
     * Obtiene la {@link Instalacion} a la que pertenece esta zona.
     *
     * @return la instalación asociada.
     */
    Instalacion getInstalacion();

    /**
     * Establece la {@link Instalacion} a la que pertenece esta zona.
     *
     * @param instalacion la instalación a asociar.
     */
    void setInstalacion(Instalacion instalacion);

    /**
     * Agrega una {@link Localidad} a esta zona.
     *
     * @param localidad la localidad a agregar.
     */
    void agregarLocalidad(Localidad localidad);

    /**
     * Quita una {@link Localidad} de esta zona.
     *
     * @param localidad la localidad a quitar.
     */
    void quitarLocalidad(Localidad localidad);

    /**
     * Obtiene las coordenadas X iniciales para cada fila de la zona.
     * Esto podría representar, por ejemplo, el punto de inicio en el eje X para las filas de asientos en un teatro.
     *
     * @return un array de valores double representando las coordenadas X iniciales de cada fila.
     */
    double[] getXInicioFilas();

    /**
     * Establece las coordenadas X iniciales para cada fila de la zona.
     *
     * @param xInicioFilas un array de valores double representando las coordenadas X iniciales a establecer para cada fila.
     */
    void setXInicioFilas(double[] xInicioFilas);

    /**
     * Obtiene la coordenada Y inicial para la columna de la zona.
     * Esto podría indicar, por ejemplo, el punto de inicio en el eje Y para las columnas de asientos en un teatro.
     *
     * @return el valor double de la coordenada Y inicial para la columna.
     */
    double getYInicioColumna();

    /**
     * Establece la coordenada Y inicial para la columna de la zona.
     *
     * @param yInicioColumna el valor double de la coordenada Y inicial a establecer para la columna.
     */
    void setYInicioColumna(double yInicioColumna);

    /**
     * Obtiene el salto en el eje X entre elementos consecutivos en la zona.
     * Esto podría utilizarse para determinar el espacio entre asientos en una fila.
     *
     * @return el valor double del salto en el eje X.
     */
    double getSaltoX();

    /**
     * Establece el salto en el eje X entre elementos consecutivos en la zona.
     *
     * @param saltoX el valor double del salto en el eje X a establecer.
     */
    void setSaltoX(double saltoX);

    /**
     * Obtiene el salto en el eje X para huecos específicos en la zona.
     * Esto podría referirse a espacios más grandes entre grupos de asientos o elementos en una fila.
     *
     * @return el valor double del salto para huecos en el eje X.
     */
    double getSaltoHuecoX();

    /**
     * Establece el salto en el eje X para huecos específicos en la zona.
     *
     * @param saltoHuecoX el valor double del salto para huecos en el eje X a establecer.
     */
    void setSaltoHuecoX(double saltoHuecoX);

    /**
     * Obtiene el salto en el eje Y entre elementos consecutivos o filas en la zona.
     * Esto podría ser utilizado para definir la distancia vertical entre filas de asientos.
     *
     * @return el valor double del salto en el eje Y.
     */
    double getSaltoY();

    /**
     * Establece el salto en el eje Y entre elementos consecutivos o filas en la zona.
     *
     * @param saltoY el valor double del salto en el eje Y a establecer.
     */
    void setSaltoY(double saltoY);
}