package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad;
import es.mdef.gaip_libreria.invitados.Coche;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.localidades.Localidad;

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
     * @param invitado      el invitado a asociar.
     * @param superarMaximo si es true, se podrá superar el máximo de invitados.
     */
    void setInvitado(Invitado invitado, boolean superarMaximo);

    /**
     * Obtiene el {@link Coche} asociado a esta localidad configurada.
     *
     * @return el coche asociado.
     */
    Coche getCoche();

    /**
     * Establece el {@link Coche} asociado a esta localidad configurada.
     *
     * @param coche         el coche a asociar.
     * @param superarMaximo si es true, se podrá superar el máximo de invitados.
     */
    void setCoche(Coche coche, boolean superarMaximo);

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

    /**
     * Obtiene la siguiente {@link LocalidadConfigurada} de esta localidad configurada.
     *
     * @return la siguiente localidad configurada.
     */
    default LocalidadConfigurada getSiguienteLocalidad() {
        if (getLocalidad() == null) {
            return null;
        }
        return getZonaConfigurada().getLocalidades().stream().filter(e -> e.getLocalidad() == getLocalidad().getSiguienteLocalidad()).findFirst().orElse(null);
    }

    /**
     * Obtiene el {@link EstadoOcupacionLocalidad} de esta localidad configurada.
     * Si no hay invitado ni coche asociado, el estado es {@link EstadoOcupacionLocalidad#LIBRE}.
     * Si hay un invitado o un coche, el estado es {@link EstadoOcupacionLocalidad#OCUPADA}.
     *
     * @return el estado de ocupación de la localidad. Nunca es {@code null}.
     */
    default EstadoOcupacionLocalidad getEstadoOcupacionLocalidad() {
        return getInvitado() == null && getCoche() == null ? EstadoOcupacionLocalidad.LIBRE : EstadoOcupacionLocalidad.OCUPADA;
    }

    /**
     * Obtiene el {@link Acto} asociado a esta localidad configurada.
     * Si la zona configurada está asociada a un acto, retorna ese acto.
     * En caso contrario, retorna {@code null}.
     *
     * @return el acto asociado, o {@code null} si no hay ninguno.
     */
    default Acto getActo() {
        return getZonaConfigurada().getActo();
    }
}