package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.localidades.Localidad;
import es.mdef.gaip_libreria.zonas.Zona;
import es.mdef.gaip_libreria.zonas.ZonasHelper;

import java.util.List;

/**
 * Clase de utilidad para la gestión de zonas.
 * Proporciona métodos estáticos para operaciones específicas relacionadas con la configuración y manejo de zonas.
 * Esta clase no está destinada a ser instanciada.
 */
public final class ZonasManager {

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private ZonasManager() {
    }

    /**
     * Asigna coordenadas a las localidades dentro de una zona.
     * Las localidades se ordenan por número y se les asigna una posición X e Y basada en la configuración geométrica de la zona.
     * Esto incluye ajustes para saltos regulares y saltos por huecos en la zona.
     *
     * @param zona La {@link Zona} cuyas localidades necesitan asignación de coordenadas.
     */
    public static void asignarCoordenadas(Zona zona) {
        List<Localidad> localidades = ZonasHelper.getLocalidadesOrdenadasPorNumero(zona);
        double[] inicioFilas = zona.getXInicioFilas();
        double xActual = inicioFilas[0];
        double yActual = zona.getYInicioColumna();

        int filaActual = 0;
        for (Localidad localidad : localidades) {
            localidad.setX(xActual);
            localidad.setY(yActual);
            if (localidad.getImplicaSalto() && !localidad.getImplicaSaltoFila()) {
                xActual += zona.getSaltoHuecoX();
            } else if (localidad.getImplicaSaltoFila()) {
                filaActual++;
                xActual = inicioFilas[filaActual];
                yActual -= zona.getSaltoY();
            } else {
                xActual += zona.getSaltoX();
            }
        }
    }
}