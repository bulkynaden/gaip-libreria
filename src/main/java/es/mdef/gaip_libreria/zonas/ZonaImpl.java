package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(of = {"nombre", "tipoDeZona"})
public class ZonaImpl implements Zona {
    private String nombre;
    private Set<Localidad> localidades;
    private TipoDeZona tipoDeZona;
    private Instalacion instalacion;
}