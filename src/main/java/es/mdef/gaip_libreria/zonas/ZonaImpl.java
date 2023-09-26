package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"nombre", "tipoDeZona", "numeroLocalidades"})
public class ZonaImpl implements Zona {
    private String nombre;
    private int numeroLocalidades;
    private Set<Localidad> localidades;
    private TipoDeZona tipoDeZona;
    private Instalacion instalacion;

    public ZonaImpl() {
        if (this instanceof ZonaNumerada) {
            localidades = new LinkedHashSet<>();
        } else {
            localidades = new HashSet<>();
        }
    }

    @Override
    public void agregarLocalidad(Localidad localidad) {
        if (localidad != null) {
            localidades.add(localidad);
        }
    }

    @Override
    public void quitarLocalidad(Localidad localidad) {
        localidades.remove(localidad);
    }
}