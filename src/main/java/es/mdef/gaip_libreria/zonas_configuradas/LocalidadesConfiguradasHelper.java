package es.mdef.gaip_libreria.zonas_configuradas;

import es.mdef.gaip_libreria.localidades.LocalidadNumerada;
import es.mdef.gaip_libreria.utilidades.HibernateProxyHelper;

public class LocalidadesConfiguradasHelper {
    private LocalidadesConfiguradasHelper() {
    }

    public static int compararLocalidadesPorNumero(LocalidadConfigurada localidad1, LocalidadConfigurada localidad2) {
        try {
            LocalidadNumerada localidadNumerada1 = (LocalidadNumerada) HibernateProxyHelper.getEntity(localidad1.getLocalidad());
            LocalidadNumerada localidadNumerada2 = (LocalidadNumerada) HibernateProxyHelper.getEntity(localidad2.getLocalidad());

            return Integer.compare(localidadNumerada1.getNumero(), localidadNumerada2.getNumero());
        } catch (Exception ignored) {
        }
        return -1;
    }
}
