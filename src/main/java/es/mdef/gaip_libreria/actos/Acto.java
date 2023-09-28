package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.constantes.TipoDeActo;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.unidades.Instalacion;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Interfaz que define las operaciones y propiedades asociadas a un acto.
 * Un acto representa un evento o actividad específica que tiene un nombre, descripción,
 * estado, y está asociado a una instalación. Además, un acto puede tener múltiples anfitriones e invitados.
 */
public interface Acto {

    /**
     * @return el nombre del acto.
     */
    String getNombre();

    /**
     * Establece el nombre del acto.
     *
     * @param nombre el nombre a establecer.
     */
    void setNombre(String nombre);

    /**
     * @return la descripción del acto.
     */
    String getDescripcion();

    /**
     * Establece la descripción del acto.
     *
     * @param descripcion la descripción a establecer.
     */
    void setDescripcion(String descripcion);

    /**
     * @return la instalación asociada al acto.
     */
    Instalacion getInstalacion();

    /**
     * Asocia una instalación al acto.
     *
     * @param instalacion la instalación a asociar.
     */
    void setInstalacion(Instalacion instalacion);

    /**
     * Obtiene el estado del acto.
     *
     * @return el estado actual del acto.
     */
    EstadoActo getEstado();

    /**
     * Establece el estado del acto.
     *
     * @param estado el estado a establecer.
     */
    void setEstado(EstadoActo estado);

    /**
     * Obtiene la fecha de inicio del acto.
     *
     * @return la fecha de inicio del acto.
     */
    ZonedDateTime getFecha();

    /**
     * Establece la fecha de inicio del acto.
     *
     * @param fecha la fecha de inicio a establecer.
     */
    void setFecha(ZonedDateTime fecha);

    /**
     * Obtiene la fecha de límite de registro del acto.
     *
     * @return la fecha de límite de registro del acto.
     */
    ZonedDateTime getFechaLimiteRegistro();

    /**
     * Establece la fecha de límite de registro del acto.
     *
     * @param fechaLimiteRegistro la fecha de límite de registro a establecer.
     */
    void setFechaLimiteRegistro(ZonedDateTime fechaLimiteRegistro);

    /**
     * @return el conjunto de anfitriones asociados al acto.
     */
    Set<Anfitrion> getAnfitriones();

    /**
     * Establece el conjunto de anfitriones asociados al acto.
     *
     * @param anfitriones el conjunto de anfitriones a establecer.
     */
    void setAnfitriones(Set<Anfitrion> anfitriones);

    /**
     * Obtiene el tipo del acto.
     *
     * @return el tipo del acto.
     */
    TipoDeActo getTipo();

    /**
     * Establece el tipo del acto.
     *
     * @param tipo el tipo a establecer.
     */
    void setTipo(TipoDeActo tipo);

    /**
     * @return el conjunto de invitados asociados al acto.
     */
    Set<Invitado> getInvitados();

    /**
     * Establece el conjunto de invitados asociados al acto.
     *
     * @param invitados el conjunto de invitados a establecer.
     */
    void setInvitados(Set<Invitado> invitados);

    /**
     * Agrega un invitado al acto.
     *
     * @param invitado el invitado a agregar.
     */
    void agregarInvitado(Invitado invitado);

    /**
     * Elimina un invitado del acto.
     *
     * @param invitado el invitado a eliminar.
     */
    void quitarInvitado(Invitado invitado);

    /**
     * Agrega un anfitrión al acto.
     *
     * @param anfitrion el anfitrión a agregar.
     */
    void agregarAnfitrion(Anfitrion anfitrion);

    /**
     * Elimina un anfitrión del acto.
     *
     * @param anfitrion el anfitrión a eliminar.
     */
    void quitarAnfitrion(Anfitrion anfitrion);
}