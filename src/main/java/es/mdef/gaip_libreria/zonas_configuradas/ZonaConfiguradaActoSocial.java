package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.constantes.EstadoDeUnaLocalidad;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.RESERVADA;

public interface ZonaConfiguradaActoSocial extends ZonaConfigurada {
    int getLocalidadesReservadas();

    int getLocalidadesNormales();

    @Override
    default int getNumeroLocalidadesParaRepartir() {
        return getLocalidadesNormales();
    }

    @Override
    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return estado == RESERVADA ? getLocalidadesReservadas() :
                estado == NORMAL ? getLocalidadesNormales() : 0;
    }
}