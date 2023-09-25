package es.mdef.gaip_libreria.unidades;

import es.mdef.gaip_libreria.actos.Acto;

import java.util.Set;

public interface Unidad {
    String getNombre();

    void setNombre(String nombre);

    Set<Usuario> getUsuarios();

    void setUsuarios(Set<Usuario> usuarios);

    Set<Instalacion> getInstalaciones();

    void setInstalaciones(Set<Instalacion> instalaciones);

    Set<Acto> getActos();

    void setActos(Set<Acto> actos);

    void agregarUsuario(Usuario usuario);

    void quitarUsuario(Usuario usuario);

    void agregarActo(Acto acto);

    void quitarActo(Acto acto);
}