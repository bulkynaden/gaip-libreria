package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

public interface Anfitrion extends Persona {
    Acto getActo();

    void setActo(Acto acto);

    String getUnidadDeFormacion();

    void setUnidadDeFormacion(String unidadDeFormacion);

    Set<Invitado> getInvitados();

    void setInvitados(Set<Invitado> invitados);

    void agregarInvitado(Invitado invitado);

    void quitarInvitado(Invitado invitado);
}