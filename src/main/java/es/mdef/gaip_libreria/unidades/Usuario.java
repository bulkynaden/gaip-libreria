package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.constantes.Rol;
import es.mdef.gaip_libreria.invitados.Persona;

public interface Usuario extends Persona {
    Rol getRol();

    void setRol(Rol rol);

    Unidad getUnidad();

    void setUnidad(Unidad unidad);

    String getPassword();

    void setPassword(String password);
}