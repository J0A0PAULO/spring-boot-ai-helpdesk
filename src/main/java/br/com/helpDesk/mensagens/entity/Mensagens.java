package br.com.helpDesk.mensagens.entity;

import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.usuarios.entity.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "mensagem")
public class Mensagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @Column(name = "data_criacao")
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "chamado_id")
    private Chamado chamado;

    public Mensagens() {
    }

    public Mensagens(Long id, String conteudo, LocalDateTime dataCriacao, Usuario usuario, Chamado chamado) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        Mensagens mensagens = (Mensagens) o;
        return Objects.equals(id, mensagens.id) && Objects.equals(conteudo, mensagens.conteudo) && Objects.equals(dataCriacao, mensagens.dataCriacao) && Objects.equals(usuario, mensagens.usuario) && Objects.equals(chamado, mensagens.chamado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudo, dataCriacao, usuario, chamado);
    }
}
