package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

public interface Coche {
    Invitado getInvitado();

    void setInvitado(Invitado invitado);

    String getMatricula();

    void setMatricula(String matricula);

    String getColor();

    void setColor(String color);

    String getModelo();

    void setModelo(String modelo);

    String getMarca();

    void setMarca(String marca);

    LocalidadConfigurada getLocalidad();

    void setLocalidad(LocalidadConfigurada localidad, boolean superarMaximo);

    /**
     * Obtiene la invitación que ha sido asociada a este coche.
     *
     * @return la {@link Invitacion} asociada a este coche.
     */
    Invitacion getInvitacion();

    /**
     * Asigna una invitación específica a este coche.
     *
     * @param invitacion la {@link Invitacion} a asignar al coche.
     */
    void setInvitacion(Invitacion invitacion, boolean superarMaximo);
}