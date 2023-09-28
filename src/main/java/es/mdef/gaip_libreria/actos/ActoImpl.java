package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.constantes.TipoDeActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.unidades.Instalacion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link Acto}.
 * Esta clase representa un acto específico con sus propiedades asociadas y las relaciones con otras entidades.
 * Un acto puede tener múltiples anfitriones e invitados y está asociado a una instalación específica.
 */
@EqualsAndHashCode(of = {"nombre", "descripcion", "fecha"})
@Data
public class ActoImpl implements Acto {
    private String nombre;
    private String descripcion;
    private Instalacion instalacion;
    private EstadoActo estado;
    private ZonedDateTime fecha;
    private Set<Anfitrion> anfitriones;
    private Set<Invitado> invitados;
    private TipoDeActo tipo;

    /**
     * Constructor por defecto. Inicializa un acto con valores predeterminados.
     */
    public ActoImpl() {
        this("", "", null);
    }

    /**
     * Constructor con parámetros para inicializar un acto con nombre, descripción y fecha.
     *
     * @param nombre      Nombre del acto.
     * @param descripcion Descripción detallada del acto.
     * @param fecha       Fecha y hora en que se llevará a cabo el acto.
     */
    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha) {
        this(nombre, descripcion, fecha, EstadoActo.CREACION);
    }

    /**
     * Constructor con parámetros para inicializar un acto con nombre, descripción, fecha y estado.
     *
     * @param nombre      Nombre del acto.
     * @param descripcion Descripción detallada del acto.
     * @param fecha       Fecha y hora en que se llevará a cabo el acto.
     * @param estado      Estado actual del acto.
     */
    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha, EstadoActo estado) {
        anfitriones = new HashSet<>();
        invitados = new HashSet<>();
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    /**
     * Asocia una instalación al acto. Si el acto ya estaba asociado a otra instalación,
     * se elimina esa asociación previa. Establece la relación bidireccional entre el acto y la instalación.
     *
     * @param instalacion La instalación a asociar con el acto.
     */
    public void setInstalacion(Instalacion instalacion) {
        if (this.instalacion != instalacion) {
            if (this.instalacion != null) {
                this.instalacion.quitarActo(this);
            }
            this.instalacion = instalacion;
            if (instalacion != null) {
                instalacion.agregarActo(this);
            }
        }
    }

    /**
     * Agrega un invitado al acto y establece la relación bidireccional entre el acto y el invitado.
     *
     * @param invitado El invitado a agregar al acto.
     */
    @Override
    public void agregarInvitado(Invitado invitado) {
        if (invitado != null && !invitados.contains(invitado)) {
            invitados.add(invitado);
            if (invitado.getActo() != this) {
                invitado.setActo(this);
            }
        }
    }

    /**
     * Elimina un invitado del acto y rompe la relación bidireccional entre el acto y el invitado.
     *
     * @param invitado El invitado a eliminar del acto.
     */
    @Override
    public void quitarInvitado(Invitado invitado) {
        if (invitado != null && invitados.contains(invitado)) {
            invitados.remove(invitado);
            invitado.setActo(null);
        }
    }

    /**
     * Agrega un anfitrión al acto y establece la relación bidireccional entre el acto y el anfitrión.
     *
     * @param anfitrion El anfitrión a agregar al acto.
     */
    @Override
    public void agregarAnfitrion(Anfitrion anfitrion) {
        if (anfitrion != null && !anfitriones.contains(anfitrion)) {
            anfitriones.add(anfitrion);
            if (anfitrion.getActo() != this) {
                anfitrion.setActo(this);
            }
        }
    }

    /**
     * Elimina un anfitrión del acto y rompe la relación bidireccional entre el acto y el anfitrión.
     *
     * @param anfitrion El anfitrión a eliminar del acto.
     */
    @Override
    public void quitarAnfitrion(Anfitrion anfitrion) {
        if (anfitrion != null) {
            anfitriones.remove(anfitrion);
            anfitrion.setActo(null);
        }
    }
}