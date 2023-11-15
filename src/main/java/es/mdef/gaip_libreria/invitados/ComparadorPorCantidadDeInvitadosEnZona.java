package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Comparator;

/**
 * Comparador para ordenar anfitriones según la cantidad de invitados sin asignar en una zona y acto específicos.
 * En caso de que dos anfitriones tengan la misma cantidad de invitados sin asignar, los ordena por DNI.
 */
public class ComparadorPorCantidadDeInvitadosEnZona implements Comparator<Anfitrion> {
    private final TipoDeZona zona;
    private final Acto acto;

    /**
     * Crea una instancia del comparador con una zona y un acto específicos.
     *
     * @param zona La zona en la que se desea comparar los anfitriones.
     * @param acto El acto asociado a la comparación.
     */
    public ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona zona, Acto acto) {
        this.zona = zona;
        this.acto = acto;
    }

    /**
     * Compara dos anfitriones en función de la cantidad de invitados sin asignar en la zona y acto especificados.
     * Si tienen la misma cantidad de invitados sin asignar, los compara por DNI.
     *
     * @param a1 El primer anfitrión a comparar.
     * @param a2 El segundo anfitrión a comparar.
     * @return Un valor negativo si a1 tiene menos invitados sin asignar que a2,
     * un valor positivo si a1 tiene más invitados sin asignar que a2,
     * o cero si tienen la misma cantidad de invitados sin asignar,
     * en cuyo caso se compara por DNI.
     */
    @Override
    public int compare(Anfitrion a1, Anfitrion a2) {
        int comparacionPorInvitados = Integer.compare(
                a1.getInvitadosSinAsignarDeUnActoPorZona(acto, zona).size(),
                a2.getInvitadosSinAsignarDeUnActoPorZona(acto, zona).size()
        );

        if (comparacionPorInvitados == 0) {
            return a1.getDni().compareTo(a2.getDni());
        }

        return comparacionPorInvitados;
    }
}