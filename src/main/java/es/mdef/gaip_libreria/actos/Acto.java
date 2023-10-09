package es.mdef.gaip_libreria.actos;

import es.mdef.gaip_libreria.constantes.EstadoActo;
import es.mdef.gaip_libreria.constantes.EstadoDeUnaLocalidad;
import es.mdef.gaip_libreria.constantes.TipoDeActo;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.unidades.Instalacion;
import es.mdef.gaip_libreria.zonas_configuradas.ZonaConfigurada;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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
    Set<ZonaConfigurada> getZonas();

    /**
     * Asigna o establece el conjunto de zonas configuradas para el acto.
     *
     * @param zonas el conjunto de {@link ZonaConfigurada}s a establecer.
     */
    void setZonas(Set<ZonaConfigurada> zonas);

    /**
     * Obtiene el conjunto de anfitriones que han extendido invitaciones para el acto.
     *
     * @return un conjunto de {@link Anfitrion}s asociados al acto.
     */
    Set<Anfitrion> getAnfitriones();

    /**
     * Asigna o establece el conjunto de anfitriones que han extendido invitaciones para el acto.
     *
     * @param anfitriones el conjunto de {@link Anfitrion}s a establecer.
     */
    void setAnfitriones(Set<Anfitrion> anfitriones);

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
     * Obtiene el array de unidades de formación asociadas al acto.
     *
     * @return un array de {@link String}s con las unidades de formación asociadas al acto.
     */
    default String[] getUnidades() {
        Set<String> unidades = new HashSet<>();
        for (Anfitrion anfitrion : getAnfitriones()) {
            unidades.add(anfitrion.getUnidadDeFormacion());
        }
        return unidades.toArray(new String[0]);
    }

    /**
     * Calcula y devuelve el número total de localidades entre todas las zonas configuradas del acto.
     *
     * @return Número total de localidades.
     */
    default int getNumeroLocalidadesTotales() {
        return getZonas().stream()
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
        return getZonas().stream()
                .mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    default int getNumeroLocalidadesPorEstadoYTipoDeZona(TipoDeZona tipo, EstadoDeUnaLocalidad estado) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipo).mapToInt(e -> e.getNumeroLocalidadesPorEstado(estado))
                .sum();
    }

    default int getNumeroLocalidadesParaRepartir() {
        return getZonas().stream().mapToInt(ZonaConfigurada::getNumeroLocalidadesParaRepartir).sum();
    }

    default int getNumeroLocalidadesParaRepartirPorTipoDeZOna(TipoDeZona tipo) {
        return getZonas().stream().filter(e -> e.getZona().getTipoDeZona() == tipo).mapToInt(e -> e.getNumeroLocalidadesParaRepartir()).sum();
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
}