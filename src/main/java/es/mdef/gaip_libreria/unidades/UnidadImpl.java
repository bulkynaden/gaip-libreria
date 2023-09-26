package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class UnidadImpl implements Unidad {
    private String nombre;
    private Set<Usuario> usuarios;
    private Set<Instalacion> instalaciones;
    private Set<Acto> actos;

    public UnidadImpl() {
        this.usuarios = new HashSet<>();
        this.instalaciones = new HashSet<>();
        this.actos = new HashSet<>();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void quitarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public void agregarActo(Acto acto) {
        actos.add(acto);
    }

    @Override
    public void quitarActo(Acto acto) {
        actos.remove(acto);
    }

    @Override
    public void agregarInstalacion(Instalacion instalacion) {
        instalaciones.add(instalacion);
    }

    @Override
    public void quitarInstalacion(Instalacion instalacion) {
        instalaciones.remove(instalacion);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setInstalaciones(Set<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    public void setActos(Set<Acto> actos) {
        this.actos = actos;
    }

    public String toString() {
        return "UnidadImpl(nombre=" + this.getNombre() + ", usuarios=" + this.getUsuarios() + ", instalaciones=" + this.getInstalaciones() + ", actos=" + this.getActos() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UnidadImpl other)) return false;
        if (!other.canEqual(this)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        return Objects.equals(this$nombre, other$nombre);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnidadImpl;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        return result;
    }
}