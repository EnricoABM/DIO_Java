/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dio.bootcamp.conteudo;

/**
 *
 * @author enric
 */
public class Aula {
    private String nome;
    private double duracaoMinutos;

    public Aula() {
        super();
    }
    
    public Aula(String nome, int duracao) {
        this.nome = nome;
        this.duracaoMinutos = duracao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(double duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
   
}
