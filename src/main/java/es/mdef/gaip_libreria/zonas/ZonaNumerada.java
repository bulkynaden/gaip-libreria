package es.mdef.gaip_libreria.zonas;

public interface ZonaNumerada extends Zona {
    int getInicioNumeracion();

    void setInicioNumeracion(int inicioNumeracion);

    int getNumeroLocalidades();

    void setNumeroLocalidades(int numeroLocalidades);
}