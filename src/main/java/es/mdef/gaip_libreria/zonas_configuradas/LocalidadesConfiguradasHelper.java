package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.localidades.LocalidadNumerada;

public class LocalidadesConfiguradasHelper {
    private LocalidadesConfiguradasHelper() {
    }

    public static int compararLocalidadesPorNumero(LocalidadConfigurada localidad1, LocalidadConfigurada localidad2) {
        try {
            return Integer.compare(((LocalidadNumerada) localidad1.getLocalidad()).getNumero(), ((LocalidadNumerada) localidad2.getLocalidad()).getNumero());
        } catch (Exception ignored) {
        }
        return -1;
    }
}
