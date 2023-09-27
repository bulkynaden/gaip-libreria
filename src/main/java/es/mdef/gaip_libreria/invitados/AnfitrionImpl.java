package es.mdef.gaip_libreria.invitados;

import es.mdef.gaip_libreria.actos.Acto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = {"unidadDeFormacion"}, callSuper = true)
@Data
public class AnfitrionImpl extends PersonaImpl implements Anfitrion {
    private String unidadDeFormacion;
    private Set<Invitado> invitados;
    private Acto acto;

    public AnfitrionImpl() {
        invitados = new HashSet<>();
    }

    @Override
    public void agregarInvitado(Invitado invitado) {
        invitados.add(invitado);
    }

    @Override
    public void quitarInvitado(Invitado invitado) {
        invitados.remove(invitado);
    }
}