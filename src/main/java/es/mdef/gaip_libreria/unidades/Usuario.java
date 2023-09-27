package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.constantes.Rol;
import es.mdef.gaip_libreria.invitados.Persona;

/**
 * Interfaz que representa un usuario dentro del sistema.
 * Extiende de la interfaz Persona para heredar sus propiedades y comportamientos.
 * Define las operaciones básicas que un usuario debe tener, incluyendo la gestión de roles, unidad y contraseña.
 */
public interface Usuario extends Persona {

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol asignado al usuario.
     */
    Rol getRol();

    /**
     * Establece el rol del usuario.
     *
     * @param rol el nuevo rol a asignar al usuario.
     */
    void setRol(Rol rol);

    /**
     * Obtiene la unidad a la que pertenece el usuario.
     *
     * @return la unidad asociada al usuario.
     */
    Unidad getUnidad();

    /**
     * Establece la unidad a la que pertenece el usuario.
     *
     * @param unidad la nueva unidad a asociar al usuario.
     */
    void setUnidad(Unidad unidad);

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    String getPassword();

    /**
     * Establece la contraseña del usuario.
     *
     * @param password la nueva contraseña a asignar al usuario.
     */
    void setPassword(String password);
}