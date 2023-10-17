package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas.Localidad;

/**
 * Representa una localidad configurada dentro de una {@link ZonaConfigurada}.
 * <p>
 * Una localidad configurada es una localidad específica dentro de una zona que ha sido asignada o reservada para un {@link Invitado} específico.
 * Esta interfaz define los métodos para obtener y establecer la zona configurada, el invitado, la localidad y el estado de la localidad.
 * </p>
 * <p>
 * Por ejemplo, ciertas localidades pueden ser reservadas para invitados específicos o tener un estado particular (como ocupado, libre, reservado, etc.).
 * Esta interfaz permite definir y gestionar esas configuraciones de localidad.
 * </p>
 */
public interface LocalidadConfigurada {

    /**
     * Obtiene la {@link ZonaConfigurada} asociada a esta localidad configurada.
     *
     * @return la zona configurada asociada.
     */
    ZonaConfigurada getZonaConfigurada();

    /**
     * Establece la {@link ZonaConfigurada} asociada a esta localidad configurada.
     *
     * @param zonaConfigurada la zona configurada a asociar.
     */
    void setZonaConfigurada(ZonaConfigurada zonaConfigurada);

    /**
     * Obtiene el {@link Invitado} asociado a esta localidad configurada.
     *
     * @return el invitado asociado.
     */
    Invitado getInvitado();

    /**
     * Establece el {@link Invitado} asociado a esta localidad configurada.
     *
     * @param invitado el invitado a asociar.
     */
    void setInvitado(Invitado invitado);

    /**
     * Obtiene la {@link Localidad} asociada a esta localidad configurada.
     *
     * @return la localidad asociada.
     */
    Localidad getLocalidad();

    /**
     * Establece la {@link Localidad} asociada a esta localidad configurada.
     *
     * @param localidad la localidad a asociar.
     */
    void setLocalidad(Localidad localidad);

    /**
     * Obtiene el {@link EstadoLocalidad} de esta localidad configurada.
     *
     * @return el estado de la localidad.
     */
    EstadoLocalidad getEstadoLocalidad();

    /**
     * Establece el {@link EstadoLocalidad} de esta localidad configurada.
     *
     * @param estadoLocalidad el estado de la localidad a establecer.
     */
    void setEstadoLocalidad(EstadoLocalidad estadoLocalidad);

    default LocalidadConfigurada getSiguienteLocalidad() {
        if (getLocalidad() == null) {
            return null;
        }
        return getZonaConfigurada().getLocalidades().stream().filter(e -> e.getLocalidad() == getLocalidad().getSiguienteLocalidad()).findFirst().orElse(null);
    }

    /**
     * Obtiene el {@link EstadoOcupacionLocalidad} de esta localidad configurada.
     *
     * @return el estado de ocupación la localidad.
     */
    default EstadoOcupacionLocalidad getEstadoOcupacionLocalidad() {
        return getInvitado() == null ? EstadoOcupacionLocalidad.LIBRE : EstadoOcupacionLocalidad.OCUPADA;
    }
}