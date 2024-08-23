/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dio.bootcamp.conteudo;

import com.dio.bootcamp.conteudo.Conteudo;
import java.time.LocalDate;

/**
 *
 * @author enric
 */
public class Mentoria extends Conteudo {

    private LocalDate data;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public double calcularXP() {
        return XP_PADRAO + 20;
    }
    
    @Override
    public String toString() {
        return "Mentoria{" + "titulo=" + titulo + ", descricao=" + descricao + ", data=" + data + '}';
    }

}
