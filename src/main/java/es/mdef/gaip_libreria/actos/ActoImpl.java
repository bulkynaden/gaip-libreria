package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.constantes.TipoDeActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.unidades.Instalacion;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa un acto específico con sus propiedades asociadas y las relaciones con otras entidades.
 * Un acto puede tener múltiples anfitriones e invitados y está asociado a una instalación específica.
 * Esta clase implementa la interfaz {@link Acto}.
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
    private Set<ZonaConfigurada> zonas;

    /**
     * Constructor por defecto. Inicializa un acto con valores predeterminados.
     */
    public ActoImpl() {
        this("", "", null, null, TipoDeActo.SIN_ENTREGA);
    }

    /**
     * Constructor parametrizado para inicializar un acto con nombre, descripción, fecha, fecha límite de registro y tipo del acto.
     *
     * @param nombre              El nombre del acto.
     * @param descripcion         Una descripción detallada del acto.
     * @param fecha               La fecha y hora en que se llevará a cabo el acto.
     * @param fechaLimiteRegistro La fecha límite para el registro al acto.
     * @param tipoDeActo          El tipo de acto. No puede ser nulo.
     */
    public ActoImpl(String nombre, String descripcion, ZonedDateTime fecha, ZonedDateTime fechaLimiteRegistro, TipoDeActo tipoDeActo) {
        this(nombre, descripcion, fecha, fechaLimiteRegistro, EstadoActo.CREACION, tipoDeActo);
    }

    /**
     * Constructor parametrizado para inicializar un acto con nombre, descripción, fecha, fecha límite de registro, estado y tipo del acto.
     *
     * @param nombre              El nombre del acto.
     * @param descripcion         Una descripción detallada del acto.
     * @param fecha               La fecha y hora en que se llevará a cabo el acto.
     * @param fechaLimiteRegistro La fecha límite para el registro al acto.
     * @param estado              El estado actual del acto.
     * @param tipoDeActo          El tipo de acto.
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
     * @param instalacion La instalación a asociar con el acto. No puede ser nula.
     * @throws IllegalArgumentException si la instalación es nula.
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

    /**
     * Establece las zonas configuradas para el acto y mantiene la coherencia bidireccional.
     *
     * @param zonas El conjunto de zonas configuradas para el acto.
     */
    public void setZonas(Set<ZonaConfigurada> zonas) {
        if (this.zonas != zonas) {
            if (this.zonas != null) {
                this.zonas.forEach(zona -> zona.setActo(null));
                this.zonas.clear();
            }
            if (zonas != null) {
                zonas.forEach(this::agregarZonaConfigurada);
            }
        }
    }

    /**
     * Establece los anfitriones para el acto y mantiene la coherencia bidireccional.
     *
     * @param anfitriones El conjunto de anfitriones para el acto.
     */
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
     * @param anfitrion El anfitrión a agregar al acto. No puede ser nulo.
     * @throws IllegalArgumentException si el anfitrión es nulo.
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
     * @param anfitrion El anfitrión a eliminar del acto. No puede ser nulo.
     * @throws IllegalArgumentException si el anfitrión es nulo.
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

    /**
     * Agrega una zona configurada al acto y establece la relación bidireccional entre el acto y la zona.
     *
     * @param zona La zona configurada a agregar al acto. No puede ser nula.
     * @throws IllegalArgumentException si la zona es nula.
     */
    @Override
    public void agregarZonaConfigurada(ZonaConfigurada zona) {
        if (zona == null) {
            throw new IllegalArgumentException("La zona no puede ser nula.");
        }
        if (!zonas.contains(zona)) {
            zonas.add(zona);
            if (zona.getActo() != this) {
                zona.setActo(this);
            }
        }
    }

    /**
     * Elimina una zona configurada del acto y rompe la relación bidireccional entre el acto y la zona.
     *
     * @param zona La zona configurada a eliminar del acto. No puede ser nula.
     * @throws IllegalArgumentException si la zona es nula.
     */
    @Override
    public void quitarZonaConfigurada(ZonaConfigurada zona) {
        if (zona == null) {
            throw new IllegalArgumentException("La zona no puede ser nula.");
        }
        if (zonas.contains(zona)) {
            zonas.remove(zona);
            if (zona.getActo() == this) {
                zona.setActo(null);
            }
        }
    }
}