package es.mdef.gaip_libreria.constantes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN, ROLE_GESTOR_UNIDAD, ROLE_ANFITRION, ROLE_PROTOCOLO, ROLE_CONTROL,
}