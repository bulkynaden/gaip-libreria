package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas.Localidad;
import lombok.Data;

/**
 * Implementación concreta de la interfaz {@link LocalidadConfigurada}.
 * Esta clase representa una localidad específica que ha sido configurada con un estado y asociada a un invitado y una zona.
 */
@Data
public class LocalidadConfiguradaImpl implements LocalidadConfigurada {
    private Invitado invitado;
    private Localidad localidad;
    private EstadoLocalidad estadoLocalidad;
    private ZonaConfigurada zonaConfigurada;

    /**
     * Establece la localidad para la localidad configurada.
     *
     * @param localidad La localidad asociada a la localidad configurada.
     */
    public void setLocalidad(Localidad localidad) {
        if (this.localidad != localidad) {
            this.localidad = localidad;
        }
    }

    /**
     * Establece la zona configurada para la localidad y mantiene la coherencia bidireccional.
     *
     * @param zonaConfigurada La zona configurada para la localidad. No puede ser nula.
     */
    public void setZonaConfigurada(ZonaConfigurada zonaConfigurada) {
        if (this.zonaConfigurada != zonaConfigurada) {
            if (this.zonaConfigurada != null) {
                this.zonaConfigurada.getLocalidades().remove(this);
            }
            this.zonaConfigurada = zonaConfigurada;
            zonaConfigurada.getLocalidades().add(this);
        }
    }

    /**
     * Establece el invitado para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param invitado El invitado asociado a la localidad configurada. No puede ser nulo.
     */
    public void setInvitado(Invitado invitado) {
        if (this.invitado != invitado) {
            if (this.invitado != null && this.invitado.getLocalidad() != null) {
                this.invitado.getLocalidad().setLocalidad(null);
            }
            this.invitado = invitado;
        }
    }
}