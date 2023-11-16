package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.AccionesActo;
import es.mdef.gaip_libreria.constantes.EstadoActo;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Set;

import static es.mdef.gaip_libreria.constantes.AccionesActo.*;
import static es.mdef.gaip_libreria.constantes.EstadoActo.*;

/**
 * Clase utilitaria para proporcionar funciones de validación de acciones y fechas para actos.
 * Esta clase no debe ser instanciada.
 */
public final class ActosHelper {

    /**
     * Mapa estático que define las validaciones entre acciones de actos y sus estados permitidos.
     */
    private static final HashMap<AccionesActo, Set<EstadoActo>> VALIDACIONES;

    static {
        VALIDACIONES = new HashMap<>();
        VALIDACIONES.put(AGREGAR_ANFITRIONES, Set.of(CREACION));
        VALIDACIONES.put(AGREGAR_INVITADOS, Set.of(REGISTRO, VALIDACION));
        VALIDACIONES.put(ENVIAR_CORREO_FORMULARIO, Set.of(CREACION));
        VALIDACIONES.put(CONFIGURAR_ZONAS, Set.of(CREACION));
        VALIDACIONES.put(CONFIGURAR_PRIORIDADES, Set.of(CREACION, REGISTRO, VALIDACION));
        VALIDACIONES.put(CONFIGURAR_DISTRIBUCION, Set.of(CREACION));
        VALIDACIONES.put(BORRAR_ANFITRIONES, Set.of(CREACION));
        VALIDACIONES.put(SENTAR_INVITADOS, Set.of(VALIDACION));
    }

    // Constructor privado para prevenir instancias de esta clase utilitaria.
    private ActosHelper() {
    }

    /**
     * Valida si una acción dada es aplicable al estado actual de un acto.
     *
     * @param acto   El acto a validar.
     * @param accion La acción a verificar.
     * @return true si la acción es válida para el estado actual del acto, false en caso contrario.
     */
    public static boolean validarAccion(Acto acto, AccionesActo accion) {
        return VALIDACIONES.get(accion).contains(acto.getEstado());
    }

    /**
     * Verifica si la fecha límite de registro para un acto ha pasado y el acto no está en estado de validación.
     *
     * @param acto  El acto a validar.
     * @param ahora La fecha y hora actual.
     * @return true si la fecha límite de registro ha pasado y el acto no está en validación, false en caso contrario.
     */
    public static boolean validarFechaFinRegistro(Acto acto, ZonedDateTime ahora) {
        return ahora.isAfter(acto.getFechaLimiteRegistro()) && acto.getEstado() != VALIDACION && acto.getEstado().ordinal() < VALIDACION.ordinal();
    }

    /**
     * Comprueba si la fecha del acto ha pasado.
     *
     * @param acto  El acto a validar.
     * @param ahora La fecha y hora actual.
     * @return true si la fecha del acto ya pasó, false en caso contrario.
     */
    public static boolean validarFechaDelActo(Acto acto, ZonedDateTime ahora) {
        return ahora.isAfter(acto.getFecha().plusDays(1));
    }
}