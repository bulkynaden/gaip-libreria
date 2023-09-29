package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas.Localidad;

public interface LocalidadConfigurada {
    ZonaConfigurada getZonaConfigurada();

    void setZonaConfigurada(ZonaConfigurada zonaConfigurada);

    Invitado getInvitado();

    void setInvitado(Invitado invitado);

    Localidad getLocalidad();

    void setLocalidad(Localidad localidad);

    EstadoLocalidad getEstadoLocalidad();

    void setEstadoLocalidad(EstadoLocalidad estadoLocalidad);
}