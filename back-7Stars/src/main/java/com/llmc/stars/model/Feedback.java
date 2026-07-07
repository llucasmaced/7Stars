package com.llmc.stars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_criador")
    private String nomeCriador;

    @Column(name = "email_criador")
    private String emailCriador;

    @Column(name = "nome_avaliado")
    private String nomeAvaliado;

    @Column(name = "instagram_avaliado")
    private String instagramAvaliado;

    private String categoria;

    private Integer nota;

    private String comentario;

    public Feedback(String nomeCriador, String emailCriador, String nomeAvaliado, String instagramAvaliado, String categoria, Integer nota, String comentario) {
        this.nomeCriador = nomeCriador;
        this.emailCriador = emailCriador;
        this.nomeAvaliado = nomeAvaliado;
        this.instagramAvaliado = instagramAvaliado;
        this.categoria = categoria;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Feedback() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCriador() {
        return nomeCriador;
    }

    public void setNomeCriador(String nomeCriador) {
        this.nomeCriador = nomeCriador;
    }

    public String getEmailCriador() {
        return emailCriador;
    }

    public void setEmailCriador(String emailCriador) {
        this.emailCriador = emailCriador;
    }

    public String getNomeAvaliado() {
        return nomeAvaliado;
    }

    public void setNomeAvaliado(String nomeAvaliado) {
        this.nomeAvaliado = nomeAvaliado;
    }

    public String getInstagramAvaliado() {
        return instagramAvaliado;
    }

    public void setInstagramAvaliado(String instagramAvaliado) {
        this.instagramAvaliado = instagramAvaliado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
