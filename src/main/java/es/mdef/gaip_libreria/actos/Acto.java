package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.*;
import es.mdef.gaip_libreria.invitados.InvitacionesPorActo;
import es.mdef.gaip_libreria.invitados.Invitado;
import es.mdef.gaip_libreria.unidades.Instalacion;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Define las características y comportamientos específicos de un acto.
 * <p>
 * Un acto es un evento o actividad que se lleva a cabo en una instalación específica y puede tener
 * múltiples anfitriones que invitan a personas a asistir. Cada acto tiene un nombre, descripción,
 * estado, fecha, fecha límite de registro, tipo y configuración de zonas.
 * </p>
 */
public interface Acto {
    /**
     * Obtiene el nombre del acto.
     *
     * @return el nombre del acto.
     */
    String getNombre();

    /**
     * Asigna o establece el nombre del acto.
     *
     * @param nombre el nombre a establecer para el acto.
     */
    void setNombre(String nombre);

    /**
     * Obtiene la descripción detallada del acto.
     *
     * @return la descripción del acto.
     */
    String getDescripcion();

    /**
     * Asigna o establece la descripción del acto.
     *
     * @param descripcion la descripción a establecer para el acto.
     */
    void setDescripcion(String descripcion);

    /**
     * Obtiene la instalación específica donde se llevará a cabo el acto.
     *
     * @return la {@link Instalacion} asociada al acto.
     */
    Instalacion getInstalacion();

    /**
     * Asigna o establece la instalación donde se llevará a cabo el acto.
     *
     * @param instalacion la {@link Instalacion} a asociar al acto.
     */
    void setInstalacion(Instalacion instalacion);

    /**
     * Obtiene el estado actual del acto, que puede indicar si está planificado, en curso, finalizado, etc.
     *
     * @return el {@link EstadoActo} del acto.
     */
    EstadoActo getEstado();

    /**
     * Asigna o establece el estado del acto.
     *
     * @param estado el {@link EstadoActo} a establecer para el acto.
     */
    void setEstado(EstadoActo estado);

    /**
     * Obtiene la fecha y hora de inicio del acto.
     *
     * @return la fecha y hora de inicio del acto.
     */
    ZonedDateTime getFecha();

    /**
     * Asigna o establece la fecha y hora de inicio del acto.
     *
     * @param fecha la fecha y hora de inicio a establecer para el acto.
     */
    void setFecha(ZonedDateTime fecha);

    /**
     * Obtiene la fecha y hora límite hasta la cual se pueden registrar invitados para el acto.
     *
     * @return la fecha y hora límite de registro.
     */
    ZonedDateTime getFechaLimiteRegistro();

    /**
     * Asigna o establece la fecha y hora límite para el registro de invitados al acto.
     *
     * @param fechaLimiteRegistro la fecha y hora límite a establecer.
     */
    void setFechaLimiteRegistro(ZonedDateTime fechaLimiteRegistro);

    /**
     * Obtiene el tipo específico del acto, que puede indicar si es una conferencia, taller, ceremonia, etc.
     *
     * @return el {@link TipoDeActo} del acto.
     */
    TipoDeActo getTipo();

    /**
     * Asigna o establece el tipo del acto.
     *
     * @param tipo el {@link TipoDeActo} a establecer para el acto.
     */
    void setTipo(TipoDeActo tipo);

    /**
     * Obtiene el conjunto de zonas configuradas asociadas al acto.
     *
     * @return un conjunto de {@link ZonaConfigurada}s asociadas al acto.
     */
    List<ZonaConfigurada> getZonas();

    /**
     * Asigna o establece el conjunto de zonas configuradas para el acto.
     *
     * @param zonas el conjunto de {@link ZonaConfigurada}s a establecer.
     */
    void setZonas(List<ZonaConfigurada> zonas);

    /**
     * Obtiene el conjunto de anfitriones que han extendido invitaciones para el acto.
     *
     * @return un conjunto de {@link Anfitrion}s asociados al acto.
     */
    <T extends Anfitrion> Set<T> getAnfitriones();

    /**
     * Asigna o establece el conjunto de anfitriones que han extendido invitaciones para el acto.
     *
     * @param anfitriones el conjunto de {@link Anfitrion}s a establecer.
     */
    <T extends Anfitrion> void setAnfitriones(Set<T> anfitriones);

    /**
     * Agrega un anfitrión específico al conjunto de anfitriones del acto.
     *
     * @param anfitrion el {@link Anfitrion} a agregar al acto.
     */
    void agregarAnfitrion(Anfitrion anfitrion);

    /**
     * Elimina o quita un anfitrión específico del conjunto de anfitriones del acto.
     *
     * @param anfitrion el {@link Anfitrion} a quitar del acto.
     */
    void quitarAnfitrion(Anfitrion anfitrion);

    /**
     * Obtiene las invitaciones por acto que el anfitrión ha extendido.
     *
     * @return Conjunto de invitaciones por acto asociadas al anfitrión.
     */
    Set<InvitacionesPorActo> getInvitacionesPorActo();

    /**
     * Establece las invitaciones por acto que el anfitrión ha extendido.
     *
     * @param invitacionesPorActo Conjunto de invitaciones por acto.
     */
    void setInvitacionesPorActo(Set<InvitacionesPorActo> invitacionesPorActo);

    /**
     * Agrega una invitación por acto
     *
     * @param invitacionPorActo la invitación por acto a agregar.
     */
    void agregarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo);

    /**
     * Quita una invitación por acto
     *
     * @param invitacionPorActo la invitación por acto a quitard.
     */
    void quitarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo);

    /**
     * Agrega una zona configurada específica al conjunto de zonas del acto.
     *
     * @param zona la {@link ZonaConfigurada} a agregar al acto.
     */
    void agregarZonaConfigurada(ZonaConfigurada zona);

    /**
     * Elimina o quita una zona configurada específica del conjunto de zonas del acto.
     *
     * @param zona la {@link ZonaConfigurada} a quitar del acto.
     */
    void quitarZonaConfigurada(ZonaConfigurada zona);

    /**
     * Calcula y devuelve el número total de localidades entre todas las zonas configuradas del acto.
     *
     * @return Número total de localidades.
     */
    default int getNumeroLocalidadesTotales() {
        return getZonas().stream()
                .filter(zonaConfigurada -> zonaConfigurada.getZona().getTipoDeZona() != TipoDeZona.PARKING)
                .mapToInt(ZonaConfigurada::getNumeroLocalidadesTotales)
                .sum();
    }

    /**
     * Calcula y devuelve el número total de localidades entre todas las zonas configuradas del acto según el estado.
     *
     * @param estado filtro por estado de la localidad.
     * @return Número total de localidades en ese estado.
     */
    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return getZonas()
                .stream()
                .filter(zonaConfigurada -> zonaConfigurada.getZona().getTipoDeZona() != TipoDeZona.PARKING)
                .mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    default int getNumeroLocalidadesPorEstadoYTipoDeZona(TipoDeZona tipo, EstadoDeUnaLocalidad estado) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipo).mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    default int getNumeroLocalidadesParaRepartir() {
        return getZonas()
                .stream()
                .filter(zonaConfigurada -> zonaConfigurada.getZona().getTipoDeZona() != TipoDeZona.PARKING)
                .mapToInt(ZonaConfigurada::getNumeroLocalidadesParaRepartir)
                .sum();
    }

    default int getNumeroLocalidadesParaRepartirPorTipoDeZona(TipoDeZona tipo) {
        return getZonas()
                .stream()
                .filter(e -> e.getZona().getTipoDeZona() == tipo)
                .mapToInt(ZonaConfigurada::getNumeroLocalidadesParaRepartir).
                sum();
    }

    /**
     * Obtiene un array de las unidades de formación únicas asociadas al acto basado en sus anfitriones.
     *
     * @return un array de {@link String}s con las unidades de formación únicas asociadas al acto.
     */
    default String[] getUnidadesDeFormacion() {
        return getAnfitriones().stream()
                .map(Anfitrion::getUnidadDeFormacion)
                .distinct()
                .toArray(String[]::new);
    }

    default Set<Invitado> getInvitados() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream())
                .flatMap(invitacion -> invitacion.getInvitados().stream())
                .collect(Collectors.toSet());
    }

    default Set<Invitado> getInvitadosPorTipoDeZona(TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(invitacion -> invitacion.getTipoDeZona() == tipoDeZona))
                .flatMap(invitacion -> invitacion.getInvitados().stream())
                .collect(Collectors.toSet());
    }

    default Set<Invitado> getInvitadosSinAsignar() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() != TipoDeZona.LISTA_DE_ESPERA))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() == null))
                .collect(Collectors.toSet());
    }

    default Set<Invitado> getInvitadosSinAsignarPorTipoDeZona(TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo()
                .stream()
                .filter(invitacionesPorActo -> invitacionesPorActo.getAnfitrion() != null)
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() == tipoDeZona))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() == null))
                .collect(Collectors.toSet());
    }

    default List<LocalidadConfigurada> getLocalidadesPorEstado(EstadoLocalidad estado) {
        return getZonas().stream().flatMap(e -> e.getLocalidades().stream()).filter(e -> e.getEstadoLocalidad() == estado).collect(Collectors.toList());
    }

    default List<LocalidadConfigurada> getLocalidades() {
        return getZonas().stream().flatMap(e -> e.getLocalidades().stream()).collect(Collectors.toList());
    }

    default List<ZonaConfigurada> getZonasConfiguradasPorTipo(TipoDeZona tipoDeZona) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipoDeZona).collect(Collectors.toList());
    }

    EstadoCreacion getEstadoCreacion();

    void setEstadoCreacion(EstadoCreacion estadoCreacion);

    default void quitarAnfitriones() {
        List<Anfitrion> anfitrionesParaBorrar = new ArrayList<>(getAnfitriones());
        anfitrionesParaBorrar.forEach(this::quitarAnfitrion);
    }
}