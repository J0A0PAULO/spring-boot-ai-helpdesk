package br.com.helpDesk.mensagens.dto;

import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.usuarios.entity.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

public class MensagensResponse {

    private Long id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private Chamado chamado;

    public MensagensResponse(Long id, String conteudo, LocalDateTime dataCriacao, Chamado chamado) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.chamado = chamado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MensagensResponse that = (MensagensResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(conteudo, that.conteudo) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(chamado, that.chamado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudo, dataCriacao, chamado);
    }
}
