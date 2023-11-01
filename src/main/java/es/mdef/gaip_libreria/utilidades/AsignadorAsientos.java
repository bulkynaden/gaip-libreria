package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.ComparadorPorCantidadDeInvitadosEnZona;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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

        int localidadesRestantes = acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(TRIBUNA);
        if (validarGenericaCabeEnTribuna(acto, localidadesRestantes)) {
            for (Invitado invitado : acto.getInvitados()) {
                if (invitado.getInvitacion().getTipoDeZona() == GENERICA) {
                    invitado.getAnfitrion().getInvitacionPorTipoDeZona(acto, TipoDeZona.TRIBUNA).agregarInvitado(invitado);
                }
            }
        } else {
            while (validarListaDeEsperaCabeEnTribuna(acto, localidadesRestantes)) {
                for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                    Invitado invitado = anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA).stream().findFirst().orElse(null);
                    anfitrion.getInvitacionPorTipoDeZona(acto, TRIBUNA).agregarInvitado(invitado);
                    localidadesRestantes--;
                }
            }
        }

        AsignadorAsientosScip.sentarInvitados(acto);

        for (Anfitrion anfitrion : anfitrionesOrdenados) {
            sentarEnZona(acto, anfitrion, GENERICA);
            sentarEnZona(acto, anfitrion, ACOTADO);
        }
    }

    public static void sentarInvitadosDeListaDeEsperaEnGenerica(Acto acto) {
        System.out.println(acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(GENERICA));
        while (acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(GENERICA) > getAnfitrionesConInvitadosEnListaDeEspera(acto).size()) {
            System.out.println(acto.getNumeroLocalidadesParaRepartirPorTipoDeZona(GENERICA));
            System.out.println("anfitriones: " + getAnfitrionesConInvitadosEnListaDeEspera(acto).size());
            for (Anfitrion anfitrion : getAnfitrionesConInvitadosEnListaDeEspera(acto)) {
                anfitrion.getInvitadosSinAsignarDeUnActoPorZona(acto, LISTA_DE_ESPERA)
                        .stream()
                        .findFirst().ifPresent(invitado -> acto.getZonasConfiguradasPorTipo(GENERICA)
                                .stream()
                                .filter(zonaConfigurada -> zonaConfigurada.getNumeroLocalidadesParaRepartir() > 0)
                                .findFirst()
                                .flatMap(zona -> zona.getLocalidadesSinAsignar()
                                        .stream().
                                        findFirst())
                                .ifPresent(invitado::setLocalidad));
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
                .forEach(e -> e.setLocalidad(null));
    }

    private static boolean validarGenericaCabeEnTribuna(Acto acto, int localidadesRestantes) {
        return localidadesRestantes >= acto.getInvitadosSinAsignarPorTipoDeZona(GENERICA).size();
    }

    private static boolean validarListaDeEsperaCabeEnTribuna(Acto acto, int localidadesRestantes) {
        int count = getAnfitrionesConInvitadosEnListaDeEspera(acto).size();
        return localidadesRestantes > count;
    }

    private static List<Anfitrion> getAnfitrionesConInvitadosEnListaDeEspera(Acto acto) {
        return acto.getAnfitriones()
                .stream()
                .filter(anfitrion -> anfitrion.getInvitacionesPorActo()
                        .stream()
                        .anyMatch(invitacionesPorActo -> invitacionesPorActo.getInvitaciones()
                                .stream().anyMatch(invitacion -> invitacion.getTipoDeZona() == LISTA_DE_ESPERA && !invitacion.getInvitados().isEmpty())))
                .toList();
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
        LocalidadConfigurada localidad = obtenerLocalidadLibrePorTipoZona(acto, tipoZona);
        invitados.forEach(invitado -> invitado.setLocalidad(localidad));
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