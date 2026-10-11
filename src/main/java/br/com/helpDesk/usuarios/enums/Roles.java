package br.com.helpDesk.usuarios.enums;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {

    ROLE_CLIENTE("ROLE_CLIENTE"),
    ROLE_ATENDENTE("ROLE_ATENDENTE");


    private final String descricao;

    Roles(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public @Nullable String getAuthority() {
        return descricao;
    }
}
