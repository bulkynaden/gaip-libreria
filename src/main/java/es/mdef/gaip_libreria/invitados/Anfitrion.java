package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

public interface Anfitrion extends Persona {
    Acto getActo();

    void setActo(Acto acto);

    String getUnidadDeFormacion();

    void setUnidadDeFormacion(String unidadDeFormacion);

    Set<Invitacion> getInvitaciones();

    void setInvitaciones(Set<Invitacion> invitaciones);

    void agregarInvitacion(Invitacion invitacion);

    void quitarInvitacion(Invitacion invitacion);
}