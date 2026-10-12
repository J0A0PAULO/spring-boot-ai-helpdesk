package br.com.helpDesk.config;

import br.com.helpDesk.usuarios.enums.Roles;

import java.util.HashSet;
import java.util.Objects;

public class JWTUserData {

    private Long userId;
    private String email;
    private HashSet<Roles> roles;

    public JWTUserData(Long userId, String email, HashSet<Roles> roles) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }

    public JWTUserData() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashSet<Roles> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JWTUserData that = (JWTUserData) o;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, roles);
    }
}
