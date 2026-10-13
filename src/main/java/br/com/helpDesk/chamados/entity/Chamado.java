package br.com.helpDesk.chamados.entity;

import br.com.helpDesk.usuarios.entity.Usuario;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Chamado() {
    }

    public Chamado(Long id, String titulo, String descricao, String status, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id, chamado.id) && Objects.equals(titulo, chamado.titulo) && Objects.equals(descricao, chamado.descricao) && Objects.equals(status, chamado.status) && Objects.equals(usuario, chamado.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, status, usuario);
    }
}
