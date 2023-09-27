package es.mdef.gaip_libreria.unidades;

import java.util.Set;

/**
 * Interfaz que representa una unidad.
 * Define las operaciones básicas que una unidad debe tener.
 */
public interface Unidad {

    /**
     * Obtiene el nombre de la unidad.
     *
     * @return el nombre de la unidad.
     */
    String getNombre();

    /**
     * Establece el nombre de la unidad.
     *
     * @param nombre el nuevo nombre de la unidad.
     */
    void setNombre(String nombre);

    /**
     * Obtiene los usuarios asociados a la unidad.
     *
     * @return un conjunto de usuarios.
     */
    Set<Usuario> getUsuarios();

    /**
     * Establece los usuarios asociados a la unidad.
     *
     * @param usuarios el conjunto de usuarios a establecer.
     */
    void setUsuarios(Set<Usuario> usuarios);

    /**
     * Obtiene las instalaciones asociadas a la unidad.
     *
     * @return un conjunto de instalaciones.
     */
    Set<Instalacion> getInstalaciones();

    /**
     * Establece las instalaciones asociadas a la unidad.
     *
     * @param instalaciones el conjunto de instalaciones a establecer.
     */
    void setInstalaciones(Set<Instalacion> instalaciones);

    /**
     * Agrega un usuario a la unidad.
     *
     * @param usuario el usuario a agregar.
     */
    void agregarUsuario(Usuario usuario);

    /**
     * Quita un usuario de la unidad.
     *
     * @param usuario el usuario a quitar.
     */
    void quitarUsuario(Usuario usuario);

    /**
     * Agrega una instalación a la unidad.
     *
     * @param instalacion la instalación a agregar.
     */
    void agregarInstalacion(Instalacion instalacion);

    /**
     * Quita una instalación de la unidad.
     *
     * @param instalacion la instalación a quitar.
     */
    void quitarInstalacion(Instalacion instalacion);
}