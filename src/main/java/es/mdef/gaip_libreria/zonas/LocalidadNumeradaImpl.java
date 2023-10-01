package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "numero", callSuper = true)
@Data
public class LocalidadNumeradaImpl extends LocalidadImpl implements LocalidadNumerada {
    private int numero;
}