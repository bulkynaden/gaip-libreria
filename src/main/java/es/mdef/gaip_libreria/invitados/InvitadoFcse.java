package es.mdef.gaip_libreria.invitados;

public interface InvitadoFcse extends PersonaFcse {
    boolean getAsisteDeUniforme();

    void setAsisteDeUniforme(boolean asiste);

    boolean getRequiereVestuario();

    void setRequiereVestuario(boolean requiereVestuario);

    boolean getEntregaNombramiento();

    void setEntregaNombramiento(boolean entregaNombramiento);
}