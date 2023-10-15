package es.mdef.gaip_libreria.utilidades;

import es.mdef.gaip_libreria.actos.Acto;
import es.mdef.gaip_libreria.actos.ActoImpl;
import es.mdef.gaip_libreria.invitados.Anfitrion;
import es.mdef.gaip_libreria.invitados.AnfitrionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InvitacionesManagerTest {

    private Acto acto;
    private HashMap<String, Reparticion> asignaciones;

    @BeforeEach
    public void setup() {
        Anfitrion anfitrion1 = new AnfitrionImpl();
        anfitrion1.setUnidadDeFormacion("Unidad1");
        Anfitrion anfitrion2 = new AnfitrionImpl();
        anfitrion2.setUnidadDeFormacion("Unidad2");

        acto = new ActoImpl();
        acto.agregarAnfitrion(anfitrion1);
        acto.agregarAnfitrion(anfitrion2);

        asignaciones = new HashMap<>();
        asignaciones.put("Unidad1", new Reparticion(5, 3));
        asignaciones.put("Unidad2", new Reparticion(2, 4));
    }

    @Test
    public void testCalcularReparto() {
        Map<String, List<Reparticion>> result = InvitacionesManager.calcularReparto(acto);
        assertTrue(result.containsKey("Unidad1"));
        assertTrue(result.containsKey("Unidad2"));

        Reparticion expectedReparto = new Reparticion(2, 3);
        assertEquals(expectedReparto, result.get("Unidad1").get(0));
        assertEquals(expectedReparto, result.get("Unidad2").get(0));

    }

    @Test
    public void testCalcularRepartoPersonalizado() {
        Map<String, List<Reparticion>> result = InvitacionesManager.calcularRepartoPersonalizado(acto, asignaciones);


    }

    @Test
    public void testUnidadSinAnfitrion() {
        asignaciones.put("UnidadSinAnfitrion", new Reparticion(1, 1));

        assertThrows(IllegalArgumentException.class, () -> {
            InvitacionesManager.calcularRepartoPersonalizado(acto, asignaciones);
        });
    }
}