package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.constantes.TipoDeZona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reparticion {
    private TipoDeZona tipoDeZona;
    private int numeroDeInvitaciones;
}