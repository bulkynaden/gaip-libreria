package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.*;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.RESERVADA;
import static es.mdef.gaip_libreria.constantes.EstadoOcupacionLocalidad.LIBRE;
import static es.mdef.gaip_libreria.constantes.TipoDeZona.*;

/**
 * Clase utilidad para asignar asientos a invitados en diferentes zonas.
 */
public final class AsignadorAsientos {

    private AsignadorAsientos() {
    }

    /**
     * Asigna los asientos a los invitados de un acto específico.
     *
     * @param acto El acto para el cual se asignarán los asientos.
     */
    public static void sentarInvitados(Acto acto) {
        List<Anfitrion> anfitrionesOrdenados = new ArrayList<>(acto.getAnfitriones());
        anfitrionesOrdenados.sort(new ComparadorPorCantidadDeInvitadosEnZona(TRIBUNA, acto));

        int localidadesRestantes = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(TRIBUNA)
                - acto.getInvitadosSinAsignarPorTipoDeZona(TRIBUNA).size();

        if (validarGenericaCabeEnTribuna(acto, localidadesRestantes)) {
            for (Invitado invitado : acto.getInvitados()) {
                if (invitado.getInvitacion().getTipoDeZona() == GENERICA) {
                    invitado.getAnfitrion().getInvitacionPorTipoDeZona(acto, TipoDeZona.TRIBUNA)
                            .agregarInvitado(invitado, true);
                    localidadesRestantes--;
                }
            }
        }

        while (validarListaDeEsperaCabeEnTribuna(acto, localidadesRestantes)) {
            for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                Invitado invitado = anfitrion
                        .getInvitadosSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA)
                        .stream()
                        .findFirst()
                        .orElse(null);

                anfitrion
                        .getInvitacionPorTipoDeZona(acto, TRIBUNA)
                        .agregarInvitado(invitado, true);

                localidadesRestantes--;
            }
        }

        AsignadorAsientosScip.sentarInvitados(acto);

        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            sentarEnZona(acto, anfitrion, GENERICA);
            sentarEnZona(acto, anfitrion, ACOTADO);
            aparcarCoches(acto, anfitrion);
        }

        sentarProtocoloEnZona(acto, TRIBUNA);
        sentarProtocoloEnZona(acto, ACOTADO);
        sentarProtocoloEnZona(acto, GENERICA);
        aparcarCochesProtocolo(acto);
    }

    public static void sentarInvitadosDeListaDeEsperaEnGenerica(Acto acto) {
        while (acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(
                GENERICA) > getAnfitrionesConInvitadosEnListaDeEspera(acto).size()
                && !getAnfitrionesConInvitadosEnListaDeEspera(acto).isEmpty()) {
            for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA)
                        .stream()
                        .findFirst().ifPresent(invitado -> {
                            acto.getZonasConfiguradasPorTipo(GENERICA)
                                    .stream()
                                    .filter(zonaConfigurada -> zonaConfigurada.getNumeroLocalidadesParaRepartir() > 0)
                                    .findFirst()
                                    .flatMap(zona -> zona.getLocalidadesSinAsignar()
                                            .stream().findFirst())
                                    .ifPresent(
                                            localidadConfigurada -> invitado.setLocalidad(localidadConfigurada, true));
                        });
            }
        }
    }

    /**
     * Levanta a los invitados del acto, liberando los asientos que ocupaban.
     *
     * @param acto Acto del cual se levantarán los invitados.
     */
    public static void levantarInvitados(Acto acto) {
        acto.getInvitados().stream()
                .filter(e -> localidadEsLibrerable(e.getLocalidad()))
                .forEach(e -> {
                    e.setLocalidad(null, false);
                    if (e.getCoche() != null) {
                        e.getCoche().setLocalidad(null, false);
                    }
                });
    }

    private static boolean validarGenericaCabeEnTribuna(Acto acto, int localidadesRestantes) {
        return localidadesRestantes >= acto.getInvitadosSinAsignarPorTipoDeZona(GENERICA).size();
    }

    private static boolean validarListaDeEsperaCabeEnTribuna(Acto acto, int localidadesRestantes) {
        int count = getAnfitrionesConInvitadosEnListaDeEspera(acto).size();
        return localidadesRestantes > count && count > 0;
    }

    private static List<Anfitrion> getAnfitrionesConInvitadosEnListaDeEspera(Acto acto) {
        return acto.getAnfitriones()
                .stream()
                .filter(anfitrion -> {
                    Invitacion invitacionListaDeEspera = anfitrion.getInvitacionPorTipoDeZona(acto, LISTA_DE_ESPERA);
                    return !invitacionListaDeEspera.getInvitados().isEmpty();
                })
                .collect(Collectors.toList());
    }

    /**
     * Asigna asientos en una zona específica para un anfitrión y acto dados.
     *
     * @param acto      El acto para el cual se asignarán los asientos.
     * @param anfitrion El anfitrión para el cual se asignarán los asientos.
     * @param tipoZona  El tipo de zona donde se asignarán los asientos.
     */
    private static void sentarEnZona(Acto acto, Anfitrion anfitrion, TipoDeZona tipoZona) {
        Set<Invitado> invitados = anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, tipoZona);
        invitados.forEach(invitado -> invitado
                .setLocalidad(obtenerLocalidadLibrePorTipoZona(acto, tipoZona), true));
    }

    private static void sentarProtocoloEnZona(Acto acto, TipoDeZona tipoZona) {
        Set<Invitado> invitados = acto.getInvitacionesPorActo()
                .stream()
                .filter(invitacionesPorActo -> invitacionesPorActo.getAnfitrion() == null)
                .map(InvitacionesPorActo::getInvitaciones)
                .flatMap(Set::stream)
                .filter(invitacion -> invitacion.getTipoDeZona() == tipoZona)
                .flatMap(invitacion -> invitacion.getInvitados().stream())
                .filter(invitado -> invitado.getLocalidad() == null)
                .collect(Collectors.toSet());
        invitados.forEach(
                invitado -> invitado.setLocalidad(obtenerLocalidadLibreProtocoloPorTipoZona(acto, tipoZona), true));
    }

    /**
     * Asigna plazas de parking para un anfitrion y acto dados.
     *
     * @param acto      El acto para el cual se asignará el parking.
     * @param anfitrion El anfitrión para el cual se asignarán las plazas de parking
     */
    private static void aparcarCoches(Acto acto, Anfitrion anfitrion) {
        Set<Coche> coches = anfitrion.getCochesSinAsignarDeUnActo(acto);
        coches.forEach(coche -> {
            if (coche.getInvitado() instanceof InvitadoFcse) {
                coche.setLocalidad(obtenerParkingLibreParaMilitar(acto), true);
            } else {
                coche.setLocalidad(obtenerParkingLibreParaCivil(acto), true);
            }
        });
    }

    /**
     * Asigna plazas de parking para un anfitrion y acto dados.
     *
     * @param acto  El acto para el cual se asignará el parking.
     * @param coche El coche que se desea aparcar.
     */
    public static void aparcarCoche(Acto acto, Coche coche) {
        if (coche.getInvitado() instanceof InvitadoFcse) {
            coche.setLocalidad(obtenerParkingLibreParaMilitar(acto), true);
        } else {
            coche.setLocalidad(obtenerParkingLibreParaCivil(acto), true);
        }
    }

    private static void aparcarCochesProtocolo(Acto acto) {
        Set<Coche> coches = acto.getInvitacionesPorActo()
                .stream()
                .filter(invitacionesPorActo -> invitacionesPorActo.getAnfitrion() == null)
                .map(InvitacionesPorActo::getInvitaciones)
                .flatMap(Set::stream)
                .filter(invitacion -> invitacion.getTipoDeZona() == PARKING)
                .flatMap(invitacion -> invitacion.getCoches().stream())
                .filter(coche -> coche.getLocalidad() == null)
                .collect(Collectors.toSet());
        coches.forEach(coche -> aparcarCoche(acto, coche));
    }

    /**
     * Verifica si la localidad es liberable.
     *
     * @param localidadConfigurada La localidad a verificar.
     * @return Verdadero si la localidad es liberable, falso en caso contrario.
     */
    private static boolean localidadEsLibrerable(LocalidadConfigurada localidadConfigurada) {
        return localidadConfigurada != null && localidadConfigurada.getEstadoLocalidad() == NORMAL;
    }

    /**
     * Obtiene la primera localidad libre para un tipo de zona en un acto
     * específico.
     *
     * @param acto     El acto en el que buscar la localidad.
     * @param tipoZona El tipo de zona donde buscar la localidad.
     * @return Una localidad libre o null si no se encuentra ninguna.
     */
    private static LocalidadConfigurada obtenerLocalidadLibrePorTipoZona(Acto acto, TipoDeZona tipoZona) {
        Predicate<LocalidadConfigurada> esLocalidadLibre = localidad -> LIBRE
                .equals(localidad.getEstadoOcupacionLocalidad()) && NORMAL.equals(localidad.getEstadoLocalidad());

        return acto.getZonasConfiguradasPorTipo(tipoZona).stream()
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(esLocalidadLibre)
                .findFirst()
                .orElse(null);
    }

    private static LocalidadConfigurada obtenerParkingLibreParaMilitar(Acto acto) {
        Predicate<LocalidadConfigurada> esLocalidadLibre = localidad -> LIBRE
                .equals(localidad.getEstadoOcupacionLocalidad()) && NORMAL.equals(localidad.getEstadoLocalidad());

        return acto.getZonasConfiguradasPorTipo(TipoDeZona.PARKING).stream()
                .sorted(Comparator.comparing((ZonaConfigurada zona) -> zona.getZona().getPrioridadParkingMilitares())
                        .thenComparing((ZonaConfigurada zona) -> zona.getZona().getNombre().toLowerCase()
                                .replaceAll("parking", "").trim().substring(0, 1)))
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(esLocalidadLibre)
                .findFirst()
                .orElse(null);
    }

    private static LocalidadConfigurada obtenerParkingLibreParaCivil(Acto acto) {
        Predicate<LocalidadConfigurada> esLocalidadLibre = localidad -> LIBRE
                .equals(localidad.getEstadoOcupacionLocalidad()) && NORMAL.equals(localidad.getEstadoLocalidad());

        return acto.getZonasConfiguradasPorTipo(TipoDeZona.PARKING).stream()
                .sorted(Comparator.comparing((ZonaConfigurada zona) -> zona.getZona().getPrioridadParkingMilitares())
                        .reversed()
                        .thenComparing((ZonaConfigurada zona) -> zona.getZona().getNombre().toLowerCase()
                                .replaceAll("parking", "").trim().substring(0, 1)))
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(esLocalidadLibre)
                .findFirst()
                .orElse(null);
    }

    private static LocalidadConfigurada obtenerLocalidadLibreProtocoloPorTipoZona(Acto acto, TipoDeZona tipoZona) {
        Predicate<LocalidadConfigurada> esLocalidadLibre = localidad -> tipoZona == ACOTADO
                ? LIBRE
                        .equals(localidad.getEstadoOcupacionLocalidad())
                        && NORMAL.equals(localidad.getEstadoLocalidad())
                : LIBRE
                        .equals(localidad.getEstadoOcupacionLocalidad())
                        && RESERVADA.equals(localidad.getEstadoLocalidad());

        return acto.getZonasConfiguradasPorTipo(tipoZona).stream()
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(esLocalidadLibre)
                .findFirst()
                .orElse(null);
    }
}