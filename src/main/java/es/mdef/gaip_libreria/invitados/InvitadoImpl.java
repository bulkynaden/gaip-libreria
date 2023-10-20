package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.Sexo;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Implementación concreta de la interfaz {@link Invitado}.
 * Esta clase representa un invitado específico con sus propiedades asociadas y las relaciones con otras entidades.
 * Un invitado puede tener un parentesco y está asociado a una invitación específica.
 */
@EqualsAndHashCode(of = {"parentesco"}, callSuper = true)
@Data
@NoArgsConstructor
public class InvitadoImpl extends PersonaImpl implements Invitado {
    private String parentesco;
    private Invitacion invitacion;
    private LocalidadConfigurada localidad;
    private Coche coche;

    /**
     * Constructor con parámetros para inicializar un invitado con sus propiedades básicas.
     *
     * @param nombre          Nombre del invitado.
     * @param primerApellido  Primer apellido del invitado.
     * @param segundoApellido Segundo apellido del invitado.
     * @param dni             DNI del invitado.
     * @param sexo            Sexo del invitado.
     * @param fechaNacimiento Fecha de nacimiento del invitado.
     * @param email           Email del invitado.
     * @param telefono        Teléfono del invitado.
     * @param parentesco      Parentesco del invitado con el anfitrión.
     * @param coche           Coche asociado al invitado.
     */
    public InvitadoImpl(String nombre, String primerApellido, String segundoApellido, String dni, Sexo sexo, ZonedDateTime fechaNacimiento, String email, String telefono, String parentesco, Coche coche) {
        super(nombre, primerApellido, segundoApellido, dni, sexo, fechaNacimiento, email, telefono);
        this.parentesco = parentesco;
        setCoche(coche);
    }

    /**
     * Asocia una invitación al invitado. Si el invitado ya estaba asociado a otra invitación,
     * se elimina esa asociación previa. Establece la relación bidireccional entre el invitado y la invitación.
     *
     * @param invitacion La invitación a asociar con el invitado.
     */
    public void setInvitacion(Invitacion invitacion) {
        if (this.invitacion != invitacion) {
            if (this.invitacion != null) {
                this.invitacion.quitarInvitado(this);
            }
            this.invitacion = invitacion;
            if (this.invitacion != null) {
                this.invitacion.agregarInvitado(this);
            }
        }
    }

    /**
     * Establece la localidad para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param localidad La localidad asociada a la localidad configurada. No puede ser nula.
     * @throws IllegalArgumentException si la localidad es nula.
     */
    public void setLocalidad(LocalidadConfigurada localidad) {
        if (this.localidad != localidad) {
            if (this.localidad != null) {
                this.localidad.setInvitado(null);
            }
            this.localidad = localidad;
            if (this.localidad != null) {
                this.localidad.setInvitado(this);
                cambiarTipoDeInvitacion();
            }
        }
    }

    private void cambiarTipoDeInvitacion() {
        if (getInvitacion() != null) {
            TipoDeZona tipoDeZona = getLocalidad().getZonaConfigurada().getZona().getTipoDeZona();
            if (tipoDeZona != getInvitacion().getTipoDeZona()) {
                getInvitacion().quitarInvitado(this);
                Invitacion nuevaInvitacion = getInvitacion().getInvitacionesPorActo().getInvitaciones().stream().filter(e -> e.getTipoDeZona() == tipoDeZona).findFirst().orElseThrow();
                nuevaInvitacion.agregarInvitado(this);
            }
        }
    }

    public void setCoche(Coche coche) {
        if (this.coche != coche) {
            if (this.coche != null && this.coche.getInvitado() != null) {
                this.coche.getInvitado().setCoche(null);
            }
            this.coche = coche;
            this.coche.setInvitado(this);
        }
    }
}