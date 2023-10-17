package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.zonas.Localidad;
import es.mdef.gaip_libreria.zonas.LocalidadNumerada;
import es.mdef.gaip_libreria.zonas.Zona;

import java.util.List;

public final class ZonasManager {
    private ZonasManager() {
    }

    public static void asignarCoordenadas(Zona zona) {
        System.out.println(zona.getNombre());
        List<Localidad> localidades = zona.getLocalidades();
        double[] inicioFilas = zona.getXInicioFilas();
        double xActual = inicioFilas[0];
        double yActual = zona.getYInicioColumna();

        int filaActual = 0;
        for (Localidad localidad : localidades) {
            if (localidad instanceof LocalidadNumerada num) {
                System.out.println(num.getNumero());
            }
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