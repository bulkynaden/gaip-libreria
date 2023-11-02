package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Comparator;

public class ComparadorPorCantidadDeInvitadosEnZona implements Comparator<Anfitrion> {
    private final TipoDeZona zona;
    private final Acto acto;

    public ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona zona, Acto acto) {
        this.zona = zona;
        this.acto = acto;
    }

    @Override
    public int compare(Anfitrion a1, Anfitrion a2) {
        int comparacionPorInvitados = Integer.compare(
                a1.getAsignablesSinAsignarDeUnActoPorZona(acto, zona).size(),
                a2.getAsignablesSinAsignarDeUnActoPorZona(acto, zona).size()
        );

        if (comparacionPorInvitados == 0) {
            return a1.getDni().compareTo(a2.getDni());
        }

        return comparacionPorInvitados;
    }
}