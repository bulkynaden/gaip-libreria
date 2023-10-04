package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.zonas.Localidad;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.Set;

public class ZonasManager {
    public static void asignarCoordenadas(Zona zona) {
        Set<Localidad> localidades = zona.getLocalidades();
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