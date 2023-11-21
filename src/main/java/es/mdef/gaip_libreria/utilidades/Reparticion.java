package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una repartición de invitaciones según el tipo de zona.
 * Esta clase mantiene la información sobre la cantidad de invitaciones asignadas a un tipo específico de zona.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reparticion {
    private TipoDeZona tipoDeZona;
    private int numeroDeInvitaciones;
}