package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.constantes.*;
import es.mdef.gaip_libreria.invitados.Coche;
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
     * Obtiene el estado actual de creación del acto.
     * Este método refleja el progreso o el estado de desarrollo en el que se encuentra el acto.
     *
     * @return El {@link EstadoCreacion} del acto.
     */
    EstadoCreacion getEstadoCreacion();

    /**
     * Establece el estado de creación del acto.
     * Este método permite actualizar el estado de desarrollo o progreso en el que se encuentra el acto.
     *
     * @param estadoCreacion El nuevo {@link EstadoCreacion} a establecer para el acto.
     */
    void setEstadoCreacion(EstadoCreacion estadoCreacion);

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
     * Este método permite incluir un nuevo anfitrión, ampliando así las posibilidades
     * de invitación y gestión del acto.
     *
     * @param anfitrion el {@link Anfitrion} a agregar al acto. No debe ser {@code null}.
     */
    void agregarAnfitrion(Anfitrion anfitrion);

    /**
     * Elimina o quita un anfitrión específico del conjunto de anfitriones del acto.
     * Al quitar un anfitrión, se reducen las asociaciones y responsabilidades ligadas
     * a este en el contexto del acto.
     *
     * @param anfitrion el {@link Anfitrion} a quitar del acto. No debe ser {@code null}.
     */
    void quitarAnfitrion(Anfitrion anfitrion);

    /**
     * Obtiene las invitaciones por acto que el anfitrión ha extendido.
     * Este método proporciona un conjunto de todas las invitaciones asociadas
     * a un acto, permitiendo una gestión y seguimiento efectivo de los invitados.
     *
     * @return Conjunto de {@link InvitacionesPorActo}, nunca {@code null}.
     */
    Set<InvitacionesPorActo> getInvitacionesPorActo();

    /**
     * Establece las invitaciones por acto que el anfitrión ha extendido.
     * Este método permite actualizar el conjunto de invitaciones asociadas
     * al acto, brindando control sobre quién está invitado y cómo se gestionan
     * estas invitaciones.
     *
     * @param invitacionesPorActo Conjunto de {@link InvitacionesPorActo} a establecer. No debe ser {@code null}.
     */
    void setInvitacionesPorActo(Set<InvitacionesPorActo> invitacionesPorActo);

    /**
     * Agrega una invitación por acto.
     * Este método añade una nueva invitación al conjunto de invitaciones del acto,
     * permitiendo la inclusión de más invitados o la modificación de los detalles
     * de una invitación existente.
     *
     * @param invitacionPorActo la invitación por acto a agregar. No debe ser {@code null}.
     */
    void agregarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo);

    /**
     * Quita una invitación por acto.
     * Este método elimina una invitación específica del conjunto de invitaciones del acto,
     * lo que puede afectar a los invitados asociados a dicha invitación.
     *
     * @param invitacionPorActo la invitación por acto a quitar. No debe ser {@code null}.
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
     * Este método facilita la gestión y planificación de espacios según su estado,
     * como disponibles, ocupadas, reservadas, etc.
     *
     * @param estado filtro por estado de la localidad.
     * @return Número total de localidades en ese estado. Cero si no hay localidades que coincidan.
     */
    default int getNumeroLocalidadesPorEstado(EstadoDeUnaLocalidad estado) {
        return getZonas()
                .stream()
                .filter(zonaConfigurada -> zonaConfigurada.getZona().getTipoDeZona() != TipoDeZona.PARKING)
                .mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    /**
     * Calcula y devuelve el número total de localidades según el estado y el tipo de zona especificados.
     * Este método es útil para obtener una vista detallada de las localidades, filtradas tanto por su estado
     * (como libre, ocupada, etc.) como por el tipo de zona (como Tribuna, Acotado, etc.).
     *
     * @param tipo   El {@link TipoDeZona} por el cual filtrar las localidades.
     * @param estado El {@link EstadoDeUnaLocalidad} de las localidades a contar.
     * @return El número total de localidades que cumplen con los criterios especificados.
     */
    default int getNumeroLocalidadesPorEstadoYTipoDeZona(TipoDeZona tipo, EstadoDeUnaLocalidad estado) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipo).mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    /**
     * Calcula y devuelve el número total de localidades disponibles para repartir entre todas las zonas,
     * excluyendo las zonas de estacionamiento.
     * Este método es importante para la planificación y distribución de espacios en el acto, permitiendo
     * una gestión efectiva de las localidades disponibles.
     *
     * @return El número total de localidades disponibles para repartir, excluyendo zonas de estacionamiento.
     */
    default int getNumeroLocalidadesParaRepartir() {
        return getZonas()
                .stream()
                .filter(zonaConfigurada -> zonaConfigurada.getZona().getTipoDeZona() != TipoDeZona.PARKING)
                .mapToInt(ZonaConfigurada::getNumeroLocalidadesParaRepartir)
                .sum();
    }

    /**
     * Calcula y devuelve el número total de localidades disponibles para repartir en un tipo específico de zona.
     * Este método es útil para la gestión detallada de las localidades en zonas específicas, como VIP o general,
     * facilitando su organización y asignación.
     *
     * @param tipo El {@link TipoDeZona} por el cual filtrar las localidades disponibles.
     * @return El número total de localidades disponibles para repartir en la zona especificada.
     */
    default int getNumeroLocalidadesParaRepartirPorTipoDeZona(TipoDeZona tipo) {
        return getZonas()
                .stream()
                .filter(e -> e.getZona().getTipoDeZona() == tipo)
                .mapToInt(ZonaConfigurada::getNumeroLocalidadesParaRepartir).
                sum();
    }

    /**
     * Obtiene un array de las unidades de formación únicas asociadas al acto basado en sus anfitriones.
     * Este método proporciona una vista de las diferentes unidades de formación representadas
     * por los anfitriones del acto, útil para la planificación y el análisis estadístico.
     *
     * @return un array de {@link String}s con las unidades de formación únicas asociadas al acto. Nunca {@code null}, pero puede estar vacío.
     */
    default String[] getUnidadesDeFormacion() {
        return getAnfitriones().stream()
                .map(Anfitrion::getUnidadDeFormacion)
                .distinct()
                .toArray(String[]::new);
    }

    /**
     * Obtiene todos los invitados de todas las invitaciones asociadas al acto.
     * Este método compila un conjunto de todos los invitados, independientemente de su zona asignada o estado.
     *
     * @return Un conjunto de {@link Invitado} que incluye a todos los invitados al acto. Nunca {@code null}.
     */
    default Set<Invitado> getInvitados() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream())
                .flatMap(invitacion -> invitacion.getInvitados().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los invitados asignados a un tipo específico de zona.
     * Este método es útil para la gestión de invitados en zonas diferenciadas del acto, como Acotado, Tribuna, etc.
     *
     * @param tipoDeZona El {@link TipoDeZona} por el cual filtrar los invitados.
     * @return Un conjunto de {@link Invitado} asignados a la zona especificada. Nunca {@code null}.
     */
    default Set<Invitado> getInvitadosPorTipoDeZona(TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(invitacion -> invitacion.getTipoDeZona() == tipoDeZona))
                .flatMap(invitacion -> invitacion.getInvitados().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los invitados que aún no han sido asignados a una localidad.
     * Este método es útil para identificar invitados que requieren asignación de localidades.
     *
     * @return Un conjunto de {@link Invitado} sin una localidad asignada. Nunca {@code null}.
     */
    default Set<Invitado> getInvitadosSinAsignar() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() != TipoDeZona.LISTA_DE_ESPERA))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() == null))
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los invitados que han sido asignados a una localidad.
     * Este método permite identificar a los invitados que ya tienen una localidad asignado en el acto.
     *
     * @return Un conjunto de {@link Invitado} con una localidad asignada. Nunca {@code null}.
     */
    default Set<Invitado> getInvitadosAsignados() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() != TipoDeZona.LISTA_DE_ESPERA))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() != null))
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los vehículos asignados a zonas de estacionamiento.
     * Este método es importante para la gestión y control de los espacios de estacionamiento en el acto.
     *
     * @return Un conjunto de {@link Coche} asignados a zonas de estacionamiento. Nunca {@code null}.
     */
    default Set<Coche> getVehiculosAsignados() {
        return getInvitacionesPorActo().stream()
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() == TipoDeZona.PARKING))
                .flatMap(invitacion -> invitacion.getCoches().stream().filter(coche -> coche.getLocalidad() != null))
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los invitados asignados a un tipo específico de zona.
     * Este método es útil para el manejo detallado de invitados en zonas específicas, facilitando su organización y control.
     *
     * @param tipoDeZona El {@link TipoDeZona} por el cual filtrar los invitados asignados.
     * @return Un conjunto de {@link Invitado} asignados a la zona especificada. Nunca {@code null}.
     */
    default Set<Invitado> getInvitadosAsignadosPorTipoDeZona(TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo()
                .stream()
                .filter(invitacionesPorActo -> invitacionesPorActo.getAnfitrion() != null)
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() == tipoDeZona))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() != null))
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todos los invitados no asignados a un tipo específico de zona.
     * Este método permite identificar invitados pendientes de asignación en zonas específicas del acto.
     *
     * @param tipoDeZona El {@link TipoDeZona} por el cual filtrar los invitados sin asignar.
     * @return Un conjunto de {@link Invitado} sin asignar en la zona especificada. Nunca {@code null}.
     */
    default Set<Invitado> getInvitadosSinAsignarPorTipoDeZona(TipoDeZona tipoDeZona) {
        return getInvitacionesPorActo()
                .stream()
                .filter(invitacionesPorActo -> invitacionesPorActo.getAnfitrion() != null)
                .flatMap(invitacionesPorActo -> invitacionesPorActo.getInvitaciones().stream().filter(e -> e.getTipoDeZona() == tipoDeZona))
                .flatMap(invitacion -> invitacion.getInvitados().stream().filter(invitado -> invitado.getLocalidad() == null))
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene todas las localidades configuradas en una zona específica según su estado.
     * Este método es crucial para el seguimiento y gestión de las localidades según su disponibilidad o uso.
     *
     * @param estado El estado de las localidades a filtrar.
     * @return Una lista de {@link LocalidadConfigurada} en el estado especificado. Nunca {@code null}.
     */
    default List<LocalidadConfigurada> getLocalidadesPorEstado(EstadoLocalidad estado) {
        return getZonas().stream().flatMap(e -> e.getLocalidades().stream()).filter(e -> e.getEstadoLocalidad() == estado).collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de todas las localidades configuradas en el acto.
     * Este método proporciona una visión general de todas las localidades, independientemente de su estado o zona.
     *
     * @return Una lista de todas las {@link LocalidadConfigurada} en el acto. Nunca {@code null}.
     */
    default List<LocalidadConfigurada> getLocalidades() {
        return getZonas().stream().flatMap(e -> e.getLocalidades().stream()).collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de todas las zonas configuradas de un tipo específico.
     * Este método facilita la gestión y el acceso a zonas específicas, como Tribuna, Acotado, etc.
     *
     * @param tipoDeZona El {@link TipoDeZona} por el cual filtrar las zonas.
     * @return Una lista de {@link ZonaConfigurada} del tipo especificado. Nunca {@code null}.
     */
    default List<ZonaConfigurada> getZonasConfiguradasPorTipo(TipoDeZona tipoDeZona) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipoDeZona).collect(Collectors.toList());
    }

    /**
     * Elimina todos los anfitriones asociados al acto.
     * Este método es útil para reiniciar o limpiar la lista de anfitriones asociados al acto.
     */
    default void quitarAnfitriones() {
        List<Anfitrion> anfitrionesParaBorrar = new ArrayList<>(getAnfitriones());
        anfitrionesParaBorrar.forEach(this::quitarAnfitrion);
    }
}