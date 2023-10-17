package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.constantes.TipoDeZona;

import java.util.Comparator;

public class ComparadorPorCantidadDeInvitadosEnZona implements Comparator<Anfitrion> {
    private final TipoDeZona tipo;

    public ComparadorPorCantidadDeInvitadosEnZona(TipoDeZona tipo) {
        this.tipo = tipo;
    }

    @Override
    public int compare(Anfitrion a1, Anfitrion a2) {
        return a1.compararPorCantidadDeInvitadosDeUnTipoDeZona(a2, tipo);
    }
}