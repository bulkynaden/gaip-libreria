package es.mdef.gaip_libreria.zonas;

import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.localidades.LocalidadesHelper;

import java.util.ArrayList;
import java.util.List;

public final class ZonasHelper {
    private ZonasHelper() {
    }

    public static List<Localidad> getLocalidadesOrdenadasPorNumero(Zona zona) {
        if (zona == null || zona.getLocalidades() == null) {
            return new ArrayList<>();
        }
        return zona.getLocalidades().stream()
                .sorted(LocalidadesHelper::compararLocalidadesPorNumero)
                .toList();
    }
}