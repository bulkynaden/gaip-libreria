package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.invitados.PersonaImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link Usuario}.
 * Esta clase hereda de {@link PersonaImpl} y representa un usuario específico del sistema.
 * Además de las propiedades heredadas de Persona, un usuario tiene un rol, una unidad asociada y una contraseña.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UsuarioImpl extends PersonaImpl implements Usuario {

    /**
     * Unidad a la que está asociado el usuario. Representa una agrupación o departamento específico.
     */
    private Unidad unidad;

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