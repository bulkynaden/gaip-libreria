package es.mdef.gaip_libreria.invitados;

public interface Invitado extends Persona {
    Invitacion getInvitacion();

    void setInvitacion(Invitacion invitacion);

    String getParentesco();

    void setParentesco(String parentesco);
}