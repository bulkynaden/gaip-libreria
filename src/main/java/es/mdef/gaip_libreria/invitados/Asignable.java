package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

public interface Asignable {

    /**
     * Obtiene la localidad configurada que ha sido asignada a este invitado dentro del acto.
     *
     * @return la {@link LocalidadConfigurada} asignada al invitado.
     */
    LocalidadConfigurada getLocalidad();

    /**
     * Asigna una localidad específica a este invitado dentro del acto.
     *
     * @param localidad la {@link LocalidadConfigurada} a asignar al invitado.
     */
    void setLocalidad(LocalidadConfigurada localidad, boolean superarMaximo);

    /**
     * Obtiene la invitación que ha sido asociada a este invitado.
     *
     * @return la {@link Invitacion} asociada a este invitado.
     */
    Invitacion getInvitacion();

    /**
     * Asigna una invitación específica a este invitado.
     *
     * @param invitacion la {@link Invitacion} a asignar al invitado.
     */
    void setInvitacion(Invitacion invitacion, boolean superarMaximo);
}