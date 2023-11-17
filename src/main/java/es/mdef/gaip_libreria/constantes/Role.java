package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que define los distintos roles de usuario en el sistema.
 */
public enum Role {
    /**
     * Rol asignado a los administradores del sistema.
     */
    ROLE_ADMIN,

    /**
     * Rol para los gestores de unidades, con capacidades específicas de gestión.
     */
    ROLE_GESTOR_UNIDAD,

    /**
     * Rol asignado a los anfitriones de los actos.
     */
    ROLE_ANFITRION,

    /**
     * Rol para los usuarios encargados de las tareas de protocolo.
     */
    ROLE_PROTOCOLO,

    /**
     * Rol para los usuarios responsables del control y supervisión de los actos.
     */
    ROLE_CONTROL
}