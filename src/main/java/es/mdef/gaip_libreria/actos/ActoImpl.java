package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;

@Data
public class ActoImpl implements Acto {
    private String nombre;
    private Instalacion instalacion;
    private EstadoActo estado;
}