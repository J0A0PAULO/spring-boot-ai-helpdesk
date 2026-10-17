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
    private Long  chamadoId;


    public MensagensResponse() {
    }

    public MensagensResponse(Long id, String conteudo, LocalDateTime dataCriacao, Long chamadoId) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.chamadoId = chamadoId;
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

    public Long getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(Long chamadoId) {
        this.chamadoId = chamadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MensagensResponse that = (MensagensResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(conteudo, that.conteudo) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(chamadoId, that.chamadoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudo, dataCriacao, chamadoId);
    }
}
