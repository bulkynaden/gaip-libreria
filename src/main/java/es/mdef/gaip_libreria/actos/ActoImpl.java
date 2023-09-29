package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.constantes.TipoDeActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
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
    private ZonedDateTime fechaLimiteRegistro;
    private Set<Anfitrion> anfitriones = new HashSet<>();
    private TipoDeActo tipo;

    /**
     * Constructor por defecto. Inicializa un acto con valores predeterminados.
     */
    public ActoImpl() {
        this("", "", null, null, TipoDeActo.SIN_ENTREGA);
    }

    /**
     * Constructor con parámetros para inicializar un acto con nombre, descripción y fecha.
     *
     * @param nombre              Nombre del acto.
     * @param descripcion         Descripción detallada del acto.
     * @param fecha               Fecha y hora en que se llevará a cabo el acto.
     * @param fechaLimiteRegistro Fecha límite para el registro al acto.
     * @param tipoDeActo          Tipo de acto. No puede ser nulo.
     */
    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha, ZonedDateTime fechaLimiteRegistro, TipoDeActo tipoDeActo) {
        this(nombre, descripcion, fecha, fechaLimiteRegistro, EstadoActo.CREACION, tipoDeActo);
    }

    /**
     * Constructor con parámetros para inicializar un acto con nombre, descripción, fecha y estado.
     *
     * @param nombre              Nombre del acto.
     * @param descripcion         Descripción detallada del acto.
     * @param fecha               Fecha y hora en que se llevará a cabo el acto.
     * @param fechaLimiteRegistro Fecha límite para el registro al acto.
     * @param estado              Estado actual del acto.
     * @param tipoDeActo          Tipo de acto.
     */
    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha, ZonedDateTime fechaLimiteRegistro, EstadoActo estado, TipoDeActo tipoDeActo) {
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.fechaLimiteRegistro = fechaLimiteRegistro;
        this.tipo = tipoDeActo;
    }

    /**
     * Asocia una instalación al acto. Si el acto ya estaba asociado a otra instalación,
     * se elimina esa asociación previa. Establece la relación bidireccional entre el acto y la instalación.
     *
     * @param instalacion La instalación a asociar con el acto.
     */
    public void setInstalacion(Instalacion instalacion) {
        if (instalacion == null) {
            throw new IllegalArgumentException("La instalación no puede ser nula.");
        }
        if (this.instalacion != instalacion) {
            if (this.instalacion != null) {
                this.instalacion.quitarActo(this);
            }
            this.instalacion = instalacion;
            if (!instalacion.getActos().contains(this)) {
                instalacion.agregarActo(this);
            }
        }
    }

    public void setAnfitriones(Set<Anfitrion> anfitriones) {
        if (this.anfitriones != anfitriones) {
            if (this.anfitriones != null) {
                this.anfitriones.forEach(anfitrion -> anfitrion.setActo(null));
                this.anfitriones.clear();
            }
            if (anfitriones != null) {
                anfitriones.forEach(this::agregarAnfitrion);
            }
        }
    }

    /**
     * Agrega un anfitrión al acto y establece la relación bidireccional entre el acto y el anfitrión.
     *
     * @param anfitrion El anfitrión a agregar al acto.
     */
    @Override
    public void agregarAnfitrion(Anfitrion anfitrion) {
        if (anfitrion == null) {
            throw new IllegalArgumentException("El anfitrión no puede ser nulo.");
        }
        if (!anfitriones.contains(anfitrion)) {
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
        if (anfitrion == null) {
            throw new IllegalArgumentException("El anfitrión no puede ser nulo.");
        }
        if (anfitriones.contains(anfitrion)) {
            anfitriones.remove(anfitrion);
            if (anfitrion.getActo() == this) {
                anfitrion.setActo(null);
            }
        }
    }
}