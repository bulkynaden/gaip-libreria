package es.mdef.gaip_libreria.unidades;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación de la interfaz {@link Unidad}.
 * Representa una unidad con sus propiedades y relaciones.
 */
@Data
@EqualsAndHashCode(of = {"nombre"})
public class UnidadImpl implements Unidad {
    private String nombre;
    @Getter
    private Set<Usuario> usuarios = new HashSet<>();
    @Getter
    private Set<Instalacion> instalaciones = new HashSet<>();

    /**
     * Establece los usuarios asociados a la unidad.
     * Limpia los usuarios actuales y establece los nuevos usuarios.
     *
     * @param usuarios el conjunto de usuarios a establecer.
     */
    public void setUsuarios(Set<Usuario> usuarios) {
        if (this.usuarios != null) {
            this.usuarios.forEach(usuario -> usuario.setUnidad(null));
            this.usuarios.clear();
        }
        if (usuarios != null) {
            usuarios.forEach(this::agregarUsuario);
        }
    }

    /**
     * Establece las instalaciones asociadas a la unidad.
     * Limpia las instalaciones actuales y establece las nuevas instalaciones.
     *
     * @param instalaciones el conjunto de instalaciones a establecer.
     */
    public void setInstalaciones(Set<Instalacion> instalaciones) {
        if (this.instalaciones != null) {
            this.instalaciones.forEach(instalacion -> instalacion.setUnidad(null));
            this.instalaciones.clear();
        }
        if (instalaciones != null) {
            instalaciones.forEach(this::agregarInstalacion);
        }
    }

    /**
     * Agrega un usuario a la unidad y establece la relación bidireccional.
     *
     * @param usuario el usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        if (usuario != null && !usuarios.contains(usuario)) {
            usuarios.add(usuario);
            if (usuario.getUnidad() != this) {
                usuario.setUnidad(this);
            }
        }
    }

    /**
     * Quita un usuario de la unidad y elimina la relación bidireccional.
     *
     * @param usuario el usuario a quitar.
     */
    public void quitarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.remove(usuario);
            usuario.setUnidad(null);
        }
    }

    /**
     * Agrega una instalación a la unidad y establece la relación bidireccional.
     *
     * @param instalacion la instalación a agregar.
     */
    public void agregarInstalacion(Instalacion instalacion) {
        if (instalacion != null && !instalaciones.contains(instalacion)) {
            instalaciones.add(instalacion);
            if (instalacion.getUnidad() != this) {
                instalacion.setUnidad(this);
            }
        }
    }

    /**
     * Quita una instalación de la unidad y elimina la relación bidireccional.
     *
     * @param instalacion la instalación a quitar.
     */
    public void quitarInstalacion(Instalacion instalacion) {
        if (instalacion != null) {
            instalaciones.remove(instalacion);
            instalacion.setUnidad(null);
        }
    }
}