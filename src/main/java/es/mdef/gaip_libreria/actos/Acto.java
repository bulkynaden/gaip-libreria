package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.unidades.Instalacion;

import java.util.Set;

public interface Acto {
    String getNombre();

    void setNombre(String nombre);

    String getDescripcion();

    void setDescripcion(String descripcion);

    Instalacion getInstalacion();

    void setInstalacion(Instalacion instalacion);

    EstadoActo getEstado();

    void setEstado(EstadoActo estado);

    Set<Anfitrion> getAnfitriones();

    void setAnfitriones(Set<Anfitrion> anfitriones);

    Set<Invitado> getInvitados();

    void setInvitados(Set<Invitado> invitados);

    void agregarInvitado(Invitado invitado);

    void quitarInvitado(Invitado invitado);

    void agregarAnfitrion(Anfitrion anfitrion);

    void quitarAnfitrion(Anfitrion anfitrion);
}