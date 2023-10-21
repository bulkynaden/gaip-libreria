package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.AccionesActo;
import es.mdef.gaip_libreria.constantes.EstadoActo;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Set;

import static es.mdef.gaip_libreria.constantes.AccionesActo.*;
import static es.mdef.gaip_libreria.constantes.EstadoActo.*;

public final class ActosHelper {
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

    private ActosHelper() {
    }

    public static boolean validarAccion(Acto acto, AccionesActo accion) {
        return VALIDACIONES.get(accion).contains(acto.getEstado());
    }

    public static boolean validarFechaFinRegistro(Acto acto, ZonedDateTime ahora) {
        return ahora.isAfter(acto.getFechaLimiteRegistro()) && acto.getEstado() != VALIDACION && acto.getEstado().ordinal() < VALIDACION.ordinal();
    }
}