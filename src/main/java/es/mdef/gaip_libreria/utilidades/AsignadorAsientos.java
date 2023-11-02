package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Asignable;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;
import es.mdef.gaip_libreria.invitados.Invitacion;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static es.mdef.gaip_libreria.constantes.EstadoLocalidad.NORMAL;
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

        int localidadesRestantes = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(TRIBUNA) - acto.getAsignablesSinAsignarPorTipoDeZona(TRIBUNA).size();

        if (validarGenericaCabeEnTribuna(acto, localidadesRestantes)) {
            for (Asignable asignable : acto.getAsignables()) {
                if (asignable.getInvitacion().getTipoDeZona() == GENERICA) {
                    asignable.getInvitacion().getInvitacionesPorActo().getAnfitrion().getInvitacionPorTipoDeZona(acto, TipoDeZona.TRIBUNA).agregarAsignable(asignable, true);
                    localidadesRestantes--;
                }
            }
        }

        while (validarListaDeEsperaCabeEnTribuna(acto, localidadesRestantes)) {
            for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                Asignable asignable = anfitrion
                        .getAsignablesSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA)
                        .stream()
                        .findFirst()
                        .orElse(null);

                anfitrion
                        .getInvitacionPorTipoDeZona(acto, TRIBUNA)
                        .agregarAsignable(asignable, true);

                localidadesRestantes--;
            }
        }

        AsignadorAsientosScip.sentarInvitados(acto);

        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            sentarEnZona(acto, anfitrion, GENERICA);
            sentarEnZona(acto, anfitrion, ACOTADO);
            sentarEnZona(acto, anfitrion, PARKING);
        }
    }

    public static void sentarInvitadosDeListaDeEsperaEnGenerica(Acto acto) {
        while (acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(GENERICA) > getAnfitrionesConInvitadosEnListaDeEspera(acto).size() && !getAnfitrionesConInvitadosEnListaDeEspera(acto).isEmpty()) {
            for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                anfitrion.getAsignablesSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA)
                        .stream()
                        .findFirst().ifPresent(invitado -> {
                            acto.getZonasConfiguradasPorTipo(GENERICA)
                                    .stream()
                                    .filter(zonaConfigurada -> zonaConfigurada.getNumeroLocalidadesParaRepartir() > 0)
                                    .findFirst()
                                    .flatMap(zona -> zona.getLocalidadesSinAsignar()
                                            .stream().
                                            findFirst())
                                    .ifPresent(localidadConfigurada -> invitado.setLocalidad(localidadConfigurada, true));
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
        acto.getAsignables().stream()
                .filter(e -> localidadEsLibrerable(e.getLocalidad()))
                .forEach(e -> e.setLocalidad(null, false));
    }

    private static boolean validarGenericaCabeEnTribuna(Acto acto, int localidadesRestantes) {
        return localidadesRestantes >= acto.getAsignablesSinAsignarPorTipoDeZona(GENERICA).size();
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
                    return !invitacionListaDeEspera.getAsignables().isEmpty();
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
        Set<Asignable> invitados = anfitrion.getAsignablesSinAsignarDeUnActoPorZona(acto, tipoZona);
        invitados.forEach(asignable -> asignable
                .setLocalidad(obtenerLocalidadLibrePorTipoZona(acto, tipoZona), true));
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
     * Obtiene la primera localidad libre para un tipo de zona en un acto específico.
     *
     * @param acto     El acto en el que buscar la localidad.
     * @param tipoZona El tipo de zona donde buscar la localidad.
     * @return Una localidad libre o null si no se encuentra ninguna.
     */
    private static LocalidadConfigurada obtenerLocalidadLibrePorTipoZona(Acto acto, TipoDeZona tipoZona) {
        Predicate<LocalidadConfigurada> esLocalidadLibre = localidad ->
                LIBRE.equals(localidad.getEstadoOcupacionLocalidad()) && NORMAL.equals(localidad.getEstadoLocalidad());

        return acto.getZonasConfiguradasPorTipo(tipoZona).stream()
                .flatMap(zona -> zona.getLocalidades().stream())
                .filter(esLocalidadLibre)
                .findFirst()
                .orElse(null);
    }
}