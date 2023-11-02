package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.invitados.Coche;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.localidades.Localidad;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link LocalidadConfigurada}.
 * Esta clase representa una localidad específica que ha sido configurada con un estado y asociada a un invitado y una zona.
 */
@Data
@EqualsAndHashCode(of = {"estadoLocalidad"})
public class LocalidadConfiguradaImpl implements LocalidadConfigurada {
    private static final EstadoLocalidad ESTADO_LOCALIDAD_POR_DEFECTO = EstadoLocalidad.BLOQUEADA;
    private Invitado invitado;
    private Coche coche;
    private Localidad localidad;
    private EstadoLocalidad estadoLocalidad;
    private ZonaConfigurada zonaConfigurada;

    /**
     * Constructor por defecto.
     */
    public LocalidadConfiguradaImpl() {
        setEstadoLocalidad(ESTADO_LOCALIDAD_POR_DEFECTO);
    }

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
     * @param zonaConfigurada La zona configurada para la localidad.
     */
    public void setZonaConfigurada(ZonaConfigurada zonaConfigurada) {
        if (this.zonaConfigurada != zonaConfigurada) {
            if (this.zonaConfigurada != null) {
                this.zonaConfigurada.quitarLocalidad(this);
            }
            this.zonaConfigurada = zonaConfigurada;
            if (this.zonaConfigurada != null) {
                this.zonaConfigurada.agregarLocalidad(this);
            }
        }
    }

    /**
     * Establece el invitado para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param invitado El invitado asociado a la localidad configurada. No puede ser nulo.
     */
    public void setInvitado(Invitado invitado, boolean superarMaximo) {
        if (this.invitado != invitado) {
            Invitado oldInvitado = this.invitado;
            this.invitado = invitado;

            if (oldInvitado != null) {
                oldInvitado.setLocalidad(null, superarMaximo);
            }

            if (this.invitado != null && this.invitado.getLocalidad() != this) {
                this.invitado.setLocalidad(this, false);
            }
        }
    }

    /**
     * Establece el coche para la localidad configurada y mantiene la coherencia bidireccional.
     *
     * @param coche El coche asociado a la localidad configurada. No puede ser nulo.
     */
    public void setCoche(Coche coche, boolean superarMaximo) {
        if (this.coche != coche) {
            Coche oldCoche = this.coche;
            this.coche = coche;

            if (oldCoche != null) {
                oldCoche.setLocalidad(null, superarMaximo);
            }

            if (this.coche != null && this.coche.getLocalidad() != this) {
                this.coche.setLocalidad(this, false);
            }
        }
    }
}