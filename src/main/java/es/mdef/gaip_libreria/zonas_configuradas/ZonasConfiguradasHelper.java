package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.localidades.LocalidadesHelper;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.List;

public final class ZonasConfiguradasHelper {
    private ZonasConfiguradasHelper() {
    }

    public static List<Localidad> getLocalidadesOrdenadasPorNumero(Zona zona) {
        return zona.getLocalidades().stream()
                .sorted(LocalidadesHelper::compararLocalidadesPorNumero)
                .toList();
    }
}