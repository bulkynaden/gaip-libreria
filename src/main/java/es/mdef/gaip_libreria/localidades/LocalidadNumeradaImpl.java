package es.mdef.gaip_libreria.localidades;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de la interfaz {@link LocalidadNumerada}, extendiendo {@link LocalidadImpl}.
 * Esta clase representa una localidad numerada, que es una especialización de una localidad con un número asignado.
 */
@EqualsAndHashCode(of = "numero", callSuper = true)
@Data
public class LocalidadNumeradaImpl extends LocalidadImpl implements LocalidadNumerada {
    private int numero;
}