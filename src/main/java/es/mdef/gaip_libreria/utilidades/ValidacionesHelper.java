package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

public final class ValidacionesHelper {
    private ValidacionesHelper() {
    }

    public static boolean validarInvitadoActo(Invitado invitado, Acto acto) {
        return acto == invitado.getActo();
    }

    public static boolean validadLocalidadConfiguradaActo(LocalidadConfigurada localidadConfigurada, Acto acto) {
        return acto == localidadConfigurada.getActo();
    }

    public static boolean validadInvitadoParaLocalidadConfigurada(Invitado invitado, LocalidadConfigurada localidadConfigurada) {
        return invitado.getActo() == localidadConfigurada.getActo();
    }
}
