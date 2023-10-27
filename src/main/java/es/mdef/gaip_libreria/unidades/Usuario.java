package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.constantes.Role;
import es.mdef.gaip_libreria.invitados.Persona;

/**
 * Representa un usuario dentro del sistema.
 * <p>
 * Un usuario es una entidad que tiene permisos para acceder y realizar operaciones específicas dentro del sistema.
 * Esta interfaz extiende de {@link Persona} para heredar sus propiedades y comportamientos, y añade funcionalidades
 * específicas para la gestión de roles, unidad y contraseña.
 * </p>
 * <p>
 * Los usuarios pueden tener diferentes roles que determinan sus permisos y capacidades dentro del sistema. Además,
 * un usuario puede estar asociado a una unidad específica y tener una contraseña para autenticarse.
 * </p>
 */
public interface Usuario extends Persona {

    /**
     * Obtiene el rol asociado al usuario.
     * <p>
     * El rol define los permisos y capacidades del usuario dentro del sistema.
     * </p>
     *
     * @return el rol asignado al usuario.
     */
    Role getRole();

    /**
     * Establece un nuevo rol para el usuario.
     * <p>
     * Cambiar el rol de un usuario puede modificar sus permisos y capacidades dentro del sistema.
     * </p>
     *
     * @param role el nuevo rol a asignar al usuario.
     */
    void setRole(Role role);

    /**
     * Obtiene la unidad organizativa a la que está asociado el usuario.
     * <p>
     * La unidad puede representar un departamento, equipo o cualquier otra división organizativa.
     * </p>
     *
     * @return la unidad asociada al usuario.
     */
    Unidad getUnidad();

    /**
     * Asocia al usuario con una nueva unidad organizativa.
     *
     * @param unidad la nueva unidad a la que se asociará el usuario.
     */
    void setUnidad(Unidad unidad);

    /**
     * Obtiene la contraseña utilizada por el usuario para autenticarse en el sistema.
     *
     * @return la contraseña del usuario.
     */
    String getPassword();

    /**
     * Establece una nueva contraseña para el usuario.
     * <p>
     * Es importante garantizar que las contraseñas sean seguras y se almacenen de forma segura.
     * </p>
     *
     * @param password la nueva contraseña a asignar al usuario.
     */
    void setPassword(String password);
}