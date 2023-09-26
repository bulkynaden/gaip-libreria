package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(of = {"nombre"})
public class UnidadImpl implements Unidad {
    private String nombre;
    private Set<Usuario> usuarios;
    private Set<Instalacion> instalaciones;
    private Set<Acto> actos;

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
}