/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dio.bootcamp.conteudo;

import com.dio.bootcamp.user.Instrutor;

/**
 *
 * @author enric
 */
public abstract class Conteudo {
    protected static final double XP_PADRAO = 50; 
    
    protected String titulo;
    protected String descricao;
    protected Instrutor instrutor;
    
    public abstract double calcularXP();

    public void adicionarInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
        instrutor.getConteudosMinitrados().add(this);
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
    
}
