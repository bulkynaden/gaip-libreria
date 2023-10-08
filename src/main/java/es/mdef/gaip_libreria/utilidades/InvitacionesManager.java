package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.constantes.TipoDeZona;
import es.mdef.gaip_libreria.invitados.Anfitrion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InvitacionesManager {
    private InvitacionesManager() {
    }

    public static Map<String, List<Reparticion>> calcularReparto(Acto acto) {
        int cantidadLocalidadesParaTribuna = acto.getNumeroLocalidadesParaRepartirPorTipoDeZOna(TipoDeZona.TRIBUNA);
        int cantidadLocalidadesParaGenerica = acto.getNumeroLocalidadesParaRepartirPorTipoDeZOna(TipoDeZona.GENERICA);
        int numeroAnfitriones = acto.getAnfitriones().size();

        if (numeroAnfitriones == 0) {
            return new HashMap<>();
        }

        int baseTribuna = cantidadLocalidadesParaTribuna / numeroAnfitriones;
        int remanenteTribuna = cantidadLocalidadesParaTribuna % numeroAnfitriones;

        int baseGenerica = cantidadLocalidadesParaGenerica / numeroAnfitriones;
        int remanenteGenerica = cantidadLocalidadesParaGenerica % numeroAnfitriones;

        Map<String, List<Reparticion>> reparticionesPorUnidad = new HashMap<>();

        for (Anfitrion anfitrion : acto.getAnfitriones()) {
            String unidad = anfitrion.getUnidadDeFormacion();
            int tribunaParaAnfitrion = baseTribuna;
            int genericaParaAnfitrion = baseGenerica;

            if (remanenteTribuna > 0) {
                tribunaParaAnfitrion++;
                remanenteTribuna--;
            }

            if (remanenteGenerica > 0) {
                genericaParaAnfitrion++;
                remanenteGenerica--;
            }

            Reparticion reparticion = new Reparticion(tribunaParaAnfitrion, genericaParaAnfitrion);

            reparticionesPorUnidad.putIfAbsent(unidad, new ArrayList<>());
            reparticionesPorUnidad.get(unidad).add(reparticion);
        }

        return reparticionesPorUnidad;
    }
}