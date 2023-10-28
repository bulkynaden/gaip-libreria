package es.mdef.gaip_libreria.constantes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    GESTOR_UNIDAD("ROLE_GESTOR_UNIDAD"),
    ANFITRION("ROLE_ANFITRION"),
    PROTOCOLO("ROLE_PROTOCOLO"),
    CONTROL("ROLE_CONTROL");

    private final String role;

    @Override
    public String toString() {
        return role;
    }
}