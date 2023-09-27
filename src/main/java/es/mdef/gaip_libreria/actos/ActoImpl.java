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
    private String descripcion;
    private Instalacion instalacion;
    private EstadoActo estado;
    private ZonedDateTime fecha;
    private Set<Anfitrion> anfitriones;
    private Set<Invitado> invitados;

    public ActoImpl() {
        this("", "", null);
    }

    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha) {
        this(nombre, descripcion, fecha, EstadoActo.CREACION);
    }

    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha, EstadoActo estado) {
        anfitriones = new HashSet<>();
        invitados = new HashSet<>();
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
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