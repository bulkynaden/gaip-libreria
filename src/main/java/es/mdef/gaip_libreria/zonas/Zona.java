package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.herramientas.Figura;
import es.mdef.gaip_libreria.unidades.Instalacion;

import java.util.List;

/**
 * Representa una zona dentro de una {@link Instalacion}.
 * <p>
 * Una Zona es un área específica dentro de una instalación que tiene un conjunto de localidades y un tipo específico.
 * Esta interfaz define los métodos para obtener y establecer el nombre, el número de localidades, las localidades individuales, el tipo de zona y la instalación asociada.
 * </p>
 * <p>
 * Además, proporciona métodos para agregar y quitar localidades de la zona.
 * </p>
 */
public interface Zona extends Figura {

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
    List<Localidad> getLocalidades();

    /**
     * Establece el conjunto de {@link Localidad} asociadas a esta zona.
     *
     * @param localidades las localidades a asociar.
     */
    void setLocalidades(List<Localidad> localidades);

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

    double[] getXInicioFilas();

    void setXInicioFilas(double[] xInicioFilas);

    double getYInicioColumna();

    void setYInicioColumna(double yInicioColumna);

    double getSaltoX();

    void setSaltoX(double saltoX);

    double getSaltoHuecoX();

    void setSaltoHuecoX(double saltoHuecoX);

    double getSaltoY();

    void setSaltoY(double saltoY);
}