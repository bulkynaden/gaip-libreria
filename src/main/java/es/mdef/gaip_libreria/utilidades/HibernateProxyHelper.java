package es.mdef.gaip_libreria.utilidades;

import org.hibernate.proxy.HibernateProxy;

/**
 * Clase de utilidad para trabajar con proxies de Hibernate.
 * Esta clase proporciona métodos estáticos para facilitar la manipulación y acceso a las entidades reales detrás de los proxies de Hibernate.
 * No está destinada a ser instanciada.
 */
public final class HibernateProxyHelper {

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private HibernateProxyHelper() {
    }

    /**
     * Recupera la entidad real de un posible proxy de Hibernate.
     * Si el objeto proporcionado es un proxy de Hibernate, devuelve la instancia real subyacente.
     * Si no es un proxy, devuelve el objeto tal como está.
     *
     * @param entidad El objeto que puede ser un proxy de Hibernate.
     * @param <T>     El tipo de la entidad.
     * @return La instancia real subyacente si es un proxy, de lo contrario el mismo objeto.
     * @throws ClassCastException si el tipo de la entidad real no es asignable al tipo de la entidad proxy.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getEntity(T entidad) {
        if (entidad instanceof HibernateProxy) {
            entidad = (T) ((HibernateProxy) entidad).getHibernateLazyInitializer().getImplementation();
        }
        return entidad;
    }
}