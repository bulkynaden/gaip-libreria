package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.constantes.Role;
import es.mdef.gaip_libreria.invitados.PersonaImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Implementación concreta de la interfaz {@link Usuario}.
 * Esta clase hereda de {@link PersonaImpl} y representa un usuario específico del sistema.
 * Además de las propiedades heredadas de Persona, un usuario tiene un rol, una unidad asociada y una contraseña.
 */
@EqualsAndHashCode(of = {"role", "password"}, callSuper = true)
@Data
public class UsuarioImpl extends PersonaImpl implements Usuario {

    /**
     * Rol asociado al usuario. Define los permisos y capacidades del usuario dentro del sistema.
     */
    private Role role;

    /**
     * Unidad a la que está asociado el usuario. Representa una agrupación o departamento específico.
     */
    @Getter
    private Unidad unidad;

    /**
     * Contraseña del usuario. Utilizada para autenticación y acceso al sistema.
     */
    private String password;

    /**
     * Establece la unidad a la que pertenece el usuario y actualiza la relación bidireccional.
     * Si la unidad proporcionada no es nula, el usuario se agrega a dicha unidad.
     *
     * @param unidad la nueva unidad a asociar al usuario.
     */
    public void setUnidad(Unidad unidad) {
        if (this.unidad != unidad) {
            this.unidad = unidad;
            if (unidad != null) {
                unidad.agregarUsuario(this);
            }
        }
    }
}