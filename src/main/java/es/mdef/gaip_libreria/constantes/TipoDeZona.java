package es.mdef.gaip_libreria.constantes;

/**
 * Enumeración que define los distintos tipos de zonas que pueden estar presentes en un acto.
 */
public enum TipoDeZona {
    /**
     * Zona designada como tribuna.
     */
    TRIBUNA,

    /**
     * Zona acotada para personal militar de uniforme.
     */
    ACOTADO,

    /**
     * Zona genérica, sin especificaciones o restricciones particulares.
     */
    GENERICA,

    /**
     * Categoría para cualquier otro tipo de zona que no encaje en las categorías definidas.
     */
    OTROS,

    /**
     * Zona destinada para la lista de espera de invitados pendientes de confirmación.
     */
    LISTA_DE_ESPERA,

    /**
     * Zona designada para el estacionamiento de vehículos.
     */
    PARKING,

    /**
     * Zona específica para los actos sociales asociadas con el acto.
     */
    ACTO_SOCIAL
}