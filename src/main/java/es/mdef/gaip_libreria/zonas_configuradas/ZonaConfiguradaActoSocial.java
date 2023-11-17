package es.mdef.gaip_libreria.zonas_configuradas;

public interface ZonaConfiguradaActoSocial extends ZonaConfigurada {
    int getLocalidadesReservadas();

    int getLocalidadesNormales();

    @Override
    default int getNumeroLocalidadesParaRepartir() {
        return getLocalidadesNormales();
    }
}