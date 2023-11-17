package es.mdef.gaip_libreria.herramientas;

/**
 * Interfaz que define las propiedades y métodos comunes de una figura geométrica.
 */
public interface Figura {

    /**
     * Obtiene la posición en el eje X de la figura.
     *
     * @return La posición X de la figura.
     */
    double getX();

    /**
     * Establece la posición en el eje X de la figura.
     *
     * @param x La nueva posición X de la figura.
     */
    void setX(double x);

    /**
     * Obtiene la posición en el eje Y de la figura.
     *
     * @return La posición Y de la figura.
     */
    double getY();

    /**
     * Establece la posición en el eje Y de la figura.
     *
     * @param y La nueva posición Y de la figura.
     */
    void setY(double y);

    /**
     * Obtiene la altura de la figura.
     *
     * @return La altura de la figura.
     */
    double getAltura();

    /**
     * Establece la altura de la figura.
     *
     * @param altura La nueva altura de la figura.
     */
    void setAltura(double altura);

    /**
     * Obtiene la anchura de la figura.
     *
     * @return La anchura de la figura.
     */
    double getAnchura();

    /**
     * Establece la anchura de la figura.
     *
     * @param ancho La nueva anchura de la figura.
     */
    void setAnchura(double ancho);
}