package br.com.helpDesk.chamados.dto;

import java.util.Objects;

public class ChamadoReponse {

    private String titulo;
    private String descricao;
    private String status;

    public ChamadoReponse() {
    }

    public ChamadoReponse(String titulo, String descricao, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChamadoReponse that = (ChamadoReponse) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, status);
    }
}
