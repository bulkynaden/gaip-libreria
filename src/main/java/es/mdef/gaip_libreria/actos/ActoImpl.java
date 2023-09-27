package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class ActoImpl implements Acto {
    private String nombre;
    private Instalacion instalacion;
    private EstadoActo estado;
    private String descripcion;
    private ZonedDateTime fecha;
    private Set<Anfitrion> anfitriones;
    private Set<Invitado> invitados;

    ActoImpl() {
        anfitriones = new HashSet<>();
        invitados = new HashSet<>();
    }

    @Override
    public void agregarInvitado(Invitado invitado) {
        invitados.add(invitado);
    }

    @Override
    public void quitarInvitado(Invitado invitado) {
        invitados.remove(invitado);
    }

    @Override
    public void agregarAnfitrion(Anfitrion anfitrion) {
        anfitriones.add(anfitrion);
    }

    @Override
    public void quitarAnfitrion(Anfitrion anfitrion) {
        anfitriones.remove(anfitrion);
    }
}