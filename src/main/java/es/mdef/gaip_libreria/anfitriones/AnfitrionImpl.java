package es.mdef.gaip_libreria.anfitriones;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.InvitacionesPorActo;
import es.mdef.gaip_libreria.invitados.PersonaImpl;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementación concreta de la interfaz {@link Anfitrion}.
 * Representa a un anfitrión con capacidad para invitar personas a actos, vinculado a una unidad de formación,
 * y con un conjunto de invitaciones por acto que ha extendido.
 */
@EqualsAndHashCode(of = {"unidadDeFormacion", "empleo"}, callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnfitrionImpl extends PersonaImpl implements Anfitrion {
    private final Set<Acto> actos = new HashSet<>();
    private final Set<InvitacionesPorActo> invitacionesPorActo = new HashSet<>();
    private String unidadDeFormacion;
    private String empleo;

    /**
     * Constructor para crear un anfitrión con detalles específicos.
     *
     * @param empleo            Empleo del anfitrión.
     * @param nombre            Nombre del anfitrión.
     * @param primerApellido    Primer apellido del anfitrión.
     * @param segundoApellido   Segundo apellido del anfitrión.
     * @param unidadDeFormacion Unidad de formación a la que pertenece el anfitrión.
     * @param email             Email del anfitrión.
     */
    public AnfitrionImpl(String empleo, String nombre, String primerApellido, String segundoApellido, String unidadDeFormacion, String email) {
        setEmpleo(empleo);
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setEmail(email);
        setUnidadDeFormacion(unidadDeFormacion);
    }

    /**
     * Establece los actos asociados al anfitrión, asegurando la coherencia en la relación bidireccional.
     *
     * @param actos Conjunto de actos a asociar con este anfitrión.
     * @param <T>   Tipo genérico que extiende de {@link Acto}.
     */
    @Override
    public <T extends Acto> void setActos(Set<T> actos) {
        this.actos.forEach(e -> e.quitarAnfitrion(this));
        this.actos.clear();
        if (actos != null) {
            actos.forEach(this::agregarActo);
        }
    }

    /**
     * Establece las invitaciones por acto para el anfitrión, manteniendo la coherencia bidireccional.
     *
     * @param invitacionesPorActo Conjunto de invitaciones por acto.
     * @param <T>                 Tipo genérico que extiende de {@link InvitacionesPorActo}.
     */
    @Override
    public <T extends InvitacionesPorActo> void setInvitacionesPorActo(Set<T> invitacionesPorActo) {
        if (this.invitacionesPorActo != invitacionesPorActo) {
            this.invitacionesPorActo.clear();
            if (invitacionesPorActo != null) {
                invitacionesPorActo.forEach(this::agregarInvitacionesPorActo);
            }
        }
    }

    /**
     * Agrega una invitación por acto al conjunto de invitaciones del anfitrión, estableciendo la relación bidireccional.
     *
     * @param invitacionPorActo Invitación por acto a agregar.
     */
    @Override
    public void agregarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo) {
        if (invitacionPorActo == null) {
            throw new IllegalArgumentException("La invitación por acto no puede ser nula.");
        }
        if (!invitacionesPorActo.contains(invitacionPorActo)) {
            invitacionesPorActo.add(invitacionPorActo);
            if (invitacionPorActo.getAnfitrion() != this) {
                invitacionPorActo.setAnfitrion(this);
            }
        }
    }

    /**
     * Elimina una invitación por acto del conjunto de invitaciones del anfitrión, rompiendo la relación bidireccional.
     *
     * @param invitacionPorActo Invitación por acto a eliminar.
     */
    @Override
    public void quitarInvitacionesPorActo(InvitacionesPorActo invitacionPorActo) {
        if (invitacionPorActo == null) {
            throw new IllegalArgumentException("La invitación por acto no puede ser nula.");
        }
        if (invitacionesPorActo.contains(invitacionPorActo)) {
            invitacionesPorActo.remove(invitacionPorActo);
            if (invitacionPorActo.getAnfitrion() == this) {
                invitacionPorActo.setAnfitrion(null);
            }
        }
    }

    /**
     * Agrega un acto al conjunto de actos del anfitrión, asegurando la coherencia en la relación bidireccional.
     *
     * @param acto Acto a agregar.
     */
    @Override
    public void agregarActo(Acto acto) {
        if (acto == null) {
            throw new IllegalArgumentException("El acto no puede ser nulo.");
        }
        if (!actos.contains(acto)) {
            actos.add(acto);
            if (!acto.getAnfitriones().contains(this)) {
                acto.agregarAnfitrion(this);
            }
        }
    }

    /**
     * Elimina un acto del conjunto de actos del anfitrión, manteniendo la coherencia en la relación bidireccional.
     *
     * @param acto Acto a eliminar.
     */
    @Override
    public void quitarActo(Acto acto) {
        if (acto == null) {
            throw new IllegalArgumentException("El acto no puede ser nulo.");
        }
        if (actos.contains(acto)) {
            actos.remove(acto);
            if (acto.getAnfitriones() != null && acto.getAnfitriones().contains(this)) {
                acto.quitarAnfitrion(this);
            }
        }
    }

    /**
     * Compara dos anfitriones por la cantidad de invitados de un tipo de zona en un acto específico.
     *
     * @param acto       Acto específico para la comparación.
     * @param tipo       Tipo de zona específico para la comparación.
     * @param anfitrion1 Primer anfitrión a comparar.
     * @param anfitrion2 Segundo anfitrión a comparar.
     * @return Valor negativo, cero o positivo si el primer anfitrión tiene menos, igual o más invitados que el segundo anfitrión, respectivamente.
     */
    @Override
    public int compararPorCantidadDeInvitadosDeUnTipoDeZona(Acto acto, TipoDeZona tipo, Anfitrion anfitrion1, Anfitrion anfitrion2) {
        long thisCount = this.getNumeroInvitadosDeUnActoPorZona(acto, tipo);
        long otherCount = anfitrion2.getNumeroInvitadosDeUnActoPorZona(acto, tipo);
        return Long.compare(otherCount, thisCount);
    }

    /**
     * Compara este anfitrión con otro basándose en la unidad de formación.
     * Esta implementación de {@code compareTo} proporciona una forma de ordenar anfitriones
     * primariamente por su unidad de formación.
     *
     * @param o El anfitrión con el que se compara.
     * @return un valor negativo si esta unidad de formación es menor que la del anfitrión argumento,
     * un valor positivo si es mayor, y 0 si son iguales.
     */
    @Override
    public int compareTo(Anfitrion o) {
        return this.unidadDeFormacion.compareTo(o.getUnidadDeFormacion());
    }
}