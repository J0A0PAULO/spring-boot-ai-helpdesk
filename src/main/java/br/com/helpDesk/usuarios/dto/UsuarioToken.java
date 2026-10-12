package br.com.helpDesk.usuarios.dto;

import java.util.Objects;

public class UsuarioToken {

    String token;

    public UsuarioToken() {
    }

    public UsuarioToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioToken that = (UsuarioToken) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }
}
