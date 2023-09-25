package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.constantes.Rol;
import es.mdef.gaip_libreria.invitados.PersonaImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Clase que representa un usuario del sistema.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UsuarioImpl extends PersonaImpl implements Usuario {
    /**
     * Rol del usuario.
     */
    private Rol rol;

    /**
     * Unidad del usuario.
     */
    private Unidad unidad;

    /**
     * Contraseña del usuario.
     */
    private String password;
}