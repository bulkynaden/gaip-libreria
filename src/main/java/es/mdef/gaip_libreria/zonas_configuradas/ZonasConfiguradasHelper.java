package es.mdef.gaip_libreria.zonas_configuradas;

import java.util.List;

public final class ZonasConfiguradasHelper {
    private ZonasConfiguradasHelper() {
    }

    public static List<LocalidadConfigurada> getLocalidadesOrdenadasPorNumero(ZonaConfigurada zona) {
        return zona.getLocalidades().stream()
                .sorted(LocalidadesConfiguradasHelper::compararLocalidadesPorNumero)
                .toList();
    }
}