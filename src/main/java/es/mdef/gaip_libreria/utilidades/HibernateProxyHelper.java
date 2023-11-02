package es.mdef.gaip_libreria.utilidades;

import org.hibernate.proxy.HibernateProxy;

public final class HibernateProxyHelper {
    private HibernateProxyHelper() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getEntity(T entidad) {
        if (entidad instanceof HibernateProxy) {
            entidad = (T) ((HibernateProxy) entidad).getHibernateLazyInitializer().getImplementation();
        }
        return entidad;
    }
}