package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;

public interface Invitado extends Persona {
    Acto getActo();

    void setActo(Acto acto);

    Anfitrion getAnfitrion();

    void setAnfitrion(Anfitrion anfitrion);

    String getParentesco();

    void setParentesco(String parentesco);
}