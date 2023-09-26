package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;

public interface Localidad {
    EstadoLocalidad getEstado();

    void setEstado(EstadoLocalidad estado);

    Localidad getSiguienteLocalidad();

    void setSiguienteLocalidad(Localidad siguienteLocalidad);

    Zona getZona();

    void setZona(Zona zona);

    Boolean getImplicaSalto();

    void setImplicaSalto(Boolean implicaSalto);
}