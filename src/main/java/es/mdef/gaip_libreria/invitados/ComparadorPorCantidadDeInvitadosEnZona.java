package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Comparator;

public class ComparadorPorCantidadDeInvitadosEnZona implements Comparator<Anfitrion> {
    private final TipoDeZona tipo;
    private final Acto acto;

    public ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona tipo, Acto acto) {
        this.tipo = tipo;
        this.acto = acto;
    }

    @Override
    public int compare(Anfitrion a1, Anfitrion a2) {
        return a1.compararPorCantidadDeInvitadosDeUnTipoDeZona(acto, tipo, a1, a2);
    }
}