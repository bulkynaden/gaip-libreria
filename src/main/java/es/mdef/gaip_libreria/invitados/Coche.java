package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.anfitriones.Anfitrion;
import es.mdef.gaip_libreria.zonas_configuradas.LocalidadConfigurada;

/**
 * Representa un coche asociado a un invitado en el sistema de gestión de invitados y localidades.
 * Proporciona métodos para obtener y establecer propiedades del coche, como la matrícula, el color,
 * el modelo, la marca, y la localidad configurada, así como la invitación asociada al coche.
 * Permite controlar si se puede exceder el máximo de coches permitidos del {@link Anfitrion}.
 */
public interface Coche {
    /**
     * Obtiene el invitado asociado a este coche.
     *
     * @return el {@link Invitado} asociado a este coche.
     */
    Invitado getInvitado();

    /**
     * Asigna un invitado a este coche.
     *
     * @param invitado el {@link Invitado} a asignar.
     */
    void setInvitado(Invitado invitado);

    /**
     * Obtiene la matrícula del coche.
     *
     * @return la matrícula del coche.
     */
    String getMatricula();

    /**
     * Asigna la matrícula a este coche.
     *
     * @param matricula la matrícula a asignar.
     */
    void setMatricula(String matricula);

    /**
     * Obtiene el color del coche.
     *
     * @return el color del coche.
     */
    String getColor();

    /**
     * Asigna un color a este coche.
     *
     * @param color el color a asignar.
     */
    void setColor(String color);

    /**
     * Obtiene el modelo del coche.
     *
     * @return el modelo del coche.
     */
    String getModelo();

    /**
     * Asigna un modelo a este coche.
     *
     * @param modelo el modelo a asignar.
     */
    void setModelo(String modelo);

    /**
     * Obtiene la marca del coche.
     *
     * @return la marca del coche.
     */
    String getMarca();

    /**
     * Asigna una marca a este coche.
     *
     * @param marca la marca a asignar.
     */
    void setMarca(String marca);

    /**
     * Obtiene la localidad configurada para este coche.
     *
     * @return la {@link LocalidadConfigurada} para este coche.
     */
    LocalidadConfigurada getLocalidad();

    /**
     * Asigna una localidad a este coche.
     *
     * @param localidad             la {@link LocalidadConfigurada} a asignar.
     * @param permitirExcederMaximo indica si se permite superar el máximo de coches permitidos del {@link Anfitrion}.
     */
    void setLocalidad(LocalidadConfigurada localidad, boolean permitirExcederMaximo);

    /**
     * Obtiene la invitación que ha sido asociada a este coche.
     *
     * @return la {@link Invitacion} asociada a este coche.
     */
    Invitacion getInvitacion();

    /**
     * Asigna una invitación específica a este coche.
     *
     * @param invitacion            la {@link Invitacion} a asignar al coche.
     * @param permitirExcederMaximo indica si se permite superar el máximo de coches permitidos en la {@link Invitacion}.
     */
    void setInvitacion(Invitacion invitacion, boolean permitirExcederMaximo);
}