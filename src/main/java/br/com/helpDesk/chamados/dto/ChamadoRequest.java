package br.com.helpDesk.chamados.dto;

import java.util.Objects;

public class ChamadoRequest {

    private String titulo;
    private String descricao;

    public ChamadoRequest() {
    }

    public ChamadoRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChamadoRequest that = (ChamadoRequest) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao);
    }

    public static class StatusRequest {

        private String status;

        public StatusRequest() {
        }

        public StatusRequest(String status) {
            this.status = status;
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
            StatusRequest that = (StatusRequest) o;
            return Objects.equals(status, that.status);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(status);
        }
    }
}
