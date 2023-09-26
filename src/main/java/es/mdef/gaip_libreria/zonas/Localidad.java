package es.mdef.gaip_libreria.zonas;

public interface Localidad {
    Localidad getSiguienteLocalidad();

    void setSiguienteLocalidad(Localidad siguienteLocalidad);

    Zona getZona();

    void setZona(Zona zona);

    Boolean getImplicaSalto();

    void setImplicaSalto(Boolean implicaSalto);
}