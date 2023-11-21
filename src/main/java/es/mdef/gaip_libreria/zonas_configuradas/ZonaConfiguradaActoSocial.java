package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoDeUnaLocalidad;
import es.mdef.gaip_libreria.constantes.EstadoLocalidad;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.RESERVADA;

/**
 * Extensión de {@link ZonaConfigurada} específica para actos sociales.
 * Esta interfaz se centra en la gestión de localidades reservadas y normales en un contexto de acto social.
 */
public interface ZonaConfiguradaActoSocial extends ZonaConfigurada {

    /**
     * Obtiene el número de localidades reservadas en la zona.
     *
     * @return el número total de localidades reservadas.
     */
    int getLocalidadesReservadas();

    /**
     * Obtiene el número de localidades normales en la zona.
     *
     * @return el número total de localidades normales.
     */
    int getLocalidadesNormales();

    /**
     * Calcula el número de localidades disponibles para ser asignadas o repartidas en un acto social.
     * En el contexto de un acto social, esto corresponde al número de localidades normales.
     *
     * @return el número de localidades normales disponibles para asignar.
     */
    @Override
    default int getNumeroLocalidadesParaRepartir() {
        return getLocalidadesNormales();
    }

    /**
     * Calcula el número de localidades en un estado específico dentro de un acto social.
     * Este método proporciona una implementación específica para actos sociales, diferenciando entre localidades reservadas y normales.
     *
     * @param estado el estado de una localidad para filtrar.
     * @return el número de localidades que coinciden con el estado dado.
     * Retorna el número de localidades reservadas si el estado es {@link EstadoLocalidad#RESERVADA},
     * el número de localidades normales si el estado es {@link EstadoLocalidad#NORMAL},
     * y 0 para cualquier otro estado.
     */
    @Override
    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return estado == RESERVADA ? getLocalidadesReservadas() :
                estado == NORMAL ? getLocalidadesNormales() : 0;
    }
}