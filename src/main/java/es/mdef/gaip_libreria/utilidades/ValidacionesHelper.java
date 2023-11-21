package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

/**
 * Clase de utilidad para realizar diversas validaciones relacionadas con actos, invitados y localidades configuradas.
 * Proporciona métodos estáticos para validar la coherencia entre estas entidades.
 * Esta clase no está diseñada para ser instanciada.
 */
public final class ValidacionesHelper {

    /**
     * Constructor privado para prevenir la instanciación de la clase.
     */
    private ValidacionesHelper() {
    }

    /**
     * Valida si un invitado está asignado al acto especificado.
     *
     * @param invitado El invitado a validar.
     * @param acto     El acto con el que se compara.
     * @return true si el invitado está asignado al acto, false en caso contrario.
     */
    public static boolean validarInvitadoActo(Invitado invitado, Acto acto) {
        return acto == invitado.getActo();
    }

    /**
     * Valida si una localidad configurada pertenece al acto especificado.
     *
     * @param localidadConfigurada La localidad configurada a validar.
     * @param acto                 El acto con el que se compara.
     * @return true si la localidad configurada pertenece al acto, false en caso contrario.
     */
    public static boolean validadLocalidadConfiguradaActo(LocalidadConfigurada localidadConfigurada, Acto acto) {
        return acto == localidadConfigurada.getActo();
    }

    /**
     * Valida si un invitado está asignado al mismo acto que la localidad configurada.
     *
     * @param invitado             El invitado a validar.
     * @param localidadConfigurada La localidad configurada a comparar.
     * @return true si el invitado y la localidad configurada pertenecen al mismo acto, false en caso contrario.
     */
    public static boolean validadInvitadoParaLocalidadConfigurada(Invitado invitado, LocalidadConfigurada localidadConfigurada) {
        return invitado.getActo() == localidadConfigurada.getActo();
    }
}