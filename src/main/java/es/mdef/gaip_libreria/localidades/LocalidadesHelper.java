package es.mdef.gaip_libreria.localidades;

public final class LocalidadesHelper {
    private LocalidadesHelper() {
    }

    public static int compararLocalidadesPorNumero(Localidad localidad1, Localidad localidad2) {
        try {
            return Integer.compare(((LocalidadNumerada) localidad1).getNumero(), ((LocalidadNumerada) localidad2).getNumero());
        } catch (Exception ignored) {
        }
        return -1;
    }
}