package es.mdef.gaip_libreria.zonas;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Implementación concreta de una {@link ZonaNumerada}, extendiendo {@link ZonaImpl}.
 * Esta clase representa una zona numerada, que es una especialización de una zona con un punto de inicio específico para la numeración.
 */
@EqualsAndHashCode(of = {"inicioNumeracion"}, callSuper = true)
@Data
public class ZonaNumeradaImpl extends ZonaImpl implements ZonaNumerada {
    private int inicioNumeracion;
}