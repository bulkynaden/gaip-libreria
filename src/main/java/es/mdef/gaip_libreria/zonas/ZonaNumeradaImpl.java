package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"inicioNumeracion", "numeroLocalidades"}, callSuper = true)
@Data
public class ZonaNumeradaImpl extends ZonaImpl implements ZonaNumerada {
    private int inicioNumeracion;
    private int numeroLocalidades;
}