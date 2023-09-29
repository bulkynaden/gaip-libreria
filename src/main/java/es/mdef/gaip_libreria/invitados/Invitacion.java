package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Set;

public interface Invitacion {
    Set<Invitado> getInvitados();

    void setInvitados(Set<Invitado> invitados);

    int getNumeroMaximoInvitados();

    TipoDeZona getTipoDeZona();

    Anfitrion getAnfitrion();

    void setAnfitrion(Anfitrion anfitrion);

    void agregarInvitado(Invitado invitado);

    void quitarInvitado(Invitado invitado);

    void agregarNumeroMaximoInvitado(int cantidad);

    void quitarNumeroMaximoInvitado(int cantidad);
}