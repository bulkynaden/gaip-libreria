package es.mdef.gaip_libreria.constantes;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    ROLE_GESTOR_UNIDAD("ROLE_GESTOR_UNIDAD"),
    ROLE_ANFITRION("ROLE_ANFITRION"),
    ROLE_PROTOCOLO("ROLE_PROTOCOLO"),
    ROLE_CONTROL("ROLE_CONTROL");

    private final String role;

    Role(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return role;
    }
}