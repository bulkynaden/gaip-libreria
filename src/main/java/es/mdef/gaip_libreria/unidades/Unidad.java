package es.mdef.gaip_libreria.unidades;

import java.util.Set;

/**
 * Representa una unidad organizativa dentro del sistema.
 * <p>
 * Una unidad es una entidad que agrupa a varios usuarios y puede estar asociada a múltiples instalaciones.
 * Esta interfaz define las operaciones básicas que una unidad debe tener, incluyendo la gestión de usuarios e instalaciones.
 * </p>
 */
public interface Unidad {

    /**
     * Obtiene el nombre identificativo de la unidad.
     *
     * @return el nombre de la unidad.
     */
    String getNombre();

    /**
     * Establece un nuevo nombre identificativo para la unidad.
     *
     * @param nombre el nuevo nombre de la unidad.
     */
    void setNombre(String nombre);

    /**
     * Obtiene el conjunto de usuarios que están asociados a esta unidad.
     *
     * @return un conjunto de usuarios pertenecientes a la unidad.
     */
    Set<Usuario> getUsuarios();

    /**
     * Asocia un conjunto de usuarios a esta unidad.
     *
     * @param usuarios el conjunto de usuarios a asociar.
     */
    void setUsuarios(Set<Usuario> usuarios);

    /**
     * Obtiene el conjunto de instalaciones que están asociadas a esta unidad.
     *
     * @return un conjunto de instalaciones pertenecientes a la unidad.
     */
    Set<Instalacion> getInstalaciones();

    /**
     * Asocia un conjunto de instalaciones a esta unidad.
     *
     * @param instalaciones el conjunto de instalaciones a asociar.
     */
    void setInstalaciones(Set<Instalacion> instalaciones);

    /**
     * Agrega un usuario específico a la unidad.
     * <p>
     * Si el usuario ya está asociado a la unidad, no se realizará ninguna acción.
     * </p>
     *
     * @param usuario el usuario a agregar a la unidad.
     */
    void agregarUsuario(Usuario usuario);

    /**
     * Desasocia un usuario específico de la unidad.
     * <p>
     * Si el usuario no está asociado a la unidad, no se realizará ninguna acción.
     * </p>
     *
     * @param usuario el usuario a desasociar de la unidad.
     */
    void quitarUsuario(Usuario usuario);

    /**
     * Agrega una instalación específica a la unidad.
     * <p>
     * Si la instalación ya está asociada a la unidad, no se realizará ninguna acción.
     * </p>
     *
     * @param instalacion la instalación a agregar a la unidad.
     */
    void agregarInstalacion(Instalacion instalacion);

    /**
     * Desasocia una instalación específica de la unidad.
     * <p>
     * Si la instalación no está asociada a la unidad, no se realizará ninguna acción.
     * </p>
     *
     * @param instalacion la instalación a desasociar de la unidad.
     */
    void quitarInstalacion(Instalacion instalacion);
}