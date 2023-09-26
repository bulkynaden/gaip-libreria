package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.unidades.Instalacion;

import java.util.Set;

public interface Zona {
    String getNombre();

    void setNombre(String nombre);
    
    int getNumeroLocalidades();

    void setNumeroLocalidades(int numeroLocalidades);

    Set<Localidad> getLocalidades();

    void setLocalidades(Set<Localidad> localidades);

    TipoDeZona getTipoDeZona();

    void setTipoDeZona(TipoDeZona tipoDeZona);

    Instalacion getInstalacion();

    void setInstalacion(Instalacion instalacion);

    void agregarLocalidad(Localidad localidad);

    void quitarLocalidad(Localidad localidad);
}