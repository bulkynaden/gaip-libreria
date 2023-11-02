package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

public interface Coche extends Asignable {
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

    void setLocalidad(LocalidadConfigurada localidad);
}