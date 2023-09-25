package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.unidades.Instalacion;

public interface Acto {
    String getNombre();

    void setNombre(String nombre);

    Instalacion getInstalacion();

    void setInstalacion(Instalacion instalacion);

    EstadoActo getEstado();

    void setEstado(EstadoActo estado);
}