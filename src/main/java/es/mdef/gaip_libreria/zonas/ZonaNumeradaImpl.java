package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"inicioNumeracion"}, callSuper = true)
@Data
public class ZonaNumeradaImpl extends ZonaImpl<LocalidadNumerada> implements ZonaNumerada {
    private int inicioNumeracion;
}