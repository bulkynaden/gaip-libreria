package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.EstadoLocalidad;

public interface Localidad {
    EstadoLocalidad getEstado();

    void setEstado(EstadoLocalidad estado);

    Zona getZona();

    void setZona(Zona zona);
}