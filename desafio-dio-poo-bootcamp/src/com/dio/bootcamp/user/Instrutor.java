/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dio.bootcamp.user;

import com.dio.bootcamp.conteudo.Conteudo;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author enric
 */
public class Instrutor {

    private String nome;
    private Set<Conteudo> conteudosMinitrados = new HashSet<>();
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosMinitrados() {
        return conteudosMinitrados;
    }
    
    public void adicionarConteudoMinistrado(Conteudo conteudo) {
        conteudo.adicionarInstrutor(this);
    }

}
