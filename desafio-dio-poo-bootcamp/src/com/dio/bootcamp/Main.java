/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dio.bootcamp;

import com.dio.bootcamp.conteudo.Aula;
import com.dio.bootcamp.conteudo.Conteudo;
import com.dio.bootcamp.user.Dev;
import com.dio.bootcamp.conteudo.Mentoria;
import com.dio.bootcamp.conteudo.Curso;
import com.dio.bootcamp.trilha.Bootcamp;
import com.dio.bootcamp.user.Instrutor;
import java.time.LocalDate;

/**
 *
 * @author enric
 */
public class Main {

    public static void main(String[] args) {
        Main programa = new Main();
//        programa.exemploInicial();
//        programa.exemploPoli();
        programa.exemploFinal();
    }

    private void exemploInicial() {
        Curso cursoJava = new Curso();
        cursoJava.setTitulo("Java Developer");
        cursoJava.setDescricao("Curso de Java do básico até criação de aplicações web.");
        cursoJava.adicionarAula(new Aula("Primeira Aula", 10));

        System.out.println(cursoJava);

        Curso cursoJavaScript = new Curso();
        cursoJavaScript.setTitulo("JavaScript Developer");
        cursoJavaScript.setDescricao("Curso de JavaScript do básico para desenvolvimento de páginas dinâmicas e aplicações web");
        cursoJavaScript.adicionarAula(new Aula("Primeira Aula", 10));

        System.out.println(cursoJavaScript);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("Estrutura de dados com Java");
        mentoria.setDescricao("Mentoria para utilização das classes do framework Collections para resolução de problemas comuns.");
        mentoria.setData(LocalDate.now());

        System.out.println(mentoria);
    }

    private void exemploPoli() {
        Conteudo conteudo1 = new Curso();
        conteudo1.setTitulo("Curso");
        System.out.println(conteudo1);

        Conteudo conteudo2 = new Mentoria();
        conteudo2.setTitulo("Mentoria");
        System.out.println(conteudo2);

    }

    private void exemploFinal() {
        // CRIANDO BOOTCAMP

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Bootcamp para linguagem java do básico ao avançado.");

        // CRIANDO CURSOS DO BOOTCAMP
        Curso curso1 = new Curso();
        curso1.setTitulo("Sintaxe básica do Java");
        curso1.setDescricao("Curso introdutório da sintaxe da linguagem java");
        curso1.adicionarAula(new Aula("Introdução ao curso", 10));
        curso1.adicionarAula(new Aula("Palavras Reservadas da linguagem Java", 10));
        curso1.adicionarAula(new Aula("Demonstrando as principais palavras da linguagem", 10));

        Curso curso2 = new Curso();
        curso2.setTitulo("Tipos de dados em Java");
        curso2.setDescricao("Curso os tipos de dados da linguagem Java");
        curso2.adicionarAula(new Aula("Introdução ao Curso", 7));
        curso2.adicionarAula(new Aula("Principais tipos de dados", 7));
        curso2.adicionarAula(new Aula("Dados primitivos e por referência", 7));
        curso2.adicionarAula(new Aula("Conversão de dados", 7));

        // CRIANDO MENTORIA
        Mentoria mentoria1 = new Mentoria();
        mentoria1.setTitulo("Iniciando a carreira no Mundo Java");
        mentoria1.setDescricao("Mentoria para iniciantes em programação demonstranado o mundo de desenvolvimento com a linguagem java");
        mentoria1.setData(LocalDate.now());

        bootcamp.adicionarConteudo(curso1);
        bootcamp.adicionarConteudo(curso2);
        bootcamp.adicionarConteudo(mentoria1);

        System.out.println("Conteudos do Bootcamp: " + bootcamp.getConteudos());

        // CRIANDO OS DEVS
        Dev dev1 = new Dev();
        dev1.setNome("Grace Hopper");
        System.out.println("Nome: " + dev1.getNome());
        System.out.println("XP: " + dev1.calcularTotalXP());
        System.out.println("Conteudos Incritos: " + dev1.getConteudosInscritos());
        dev1.increverBootcamp(bootcamp);
        System.out.println("Conteudos Incritos: " + dev1.getConteudosInscritos());
        System.out.println("Conteudos Concluidos: " + dev1.getConteudosConcluidos());
        dev1.progredir();
        System.out.println("Conteudos Incritos: " + dev1.getConteudosInscritos());
        System.out.println("Conteudos Concluidos: " + dev1.getConteudosConcluidos());
        System.out.println("XP: " + dev1.calcularTotalXP());

        System.out.println("------------------------------------");
        Dev dev2 = new Dev();
        dev2.setNome("Tin Berners Lee");
        System.out.println("Nome: " + dev2.getNome());
        System.out.println("XP: " + dev2.calcularTotalXP());
        System.out.println("Conteudos Incritos: " + dev2.getConteudosInscritos());
        dev2.increverBootcamp(bootcamp);
        System.out.println("Conteudos Incritos: " + dev2.getConteudosInscritos());
        System.out.println("Conteudos Concluidos: " + dev2.getConteudosConcluidos());
        dev2.progredir();
        dev2.progredir();
        dev2.progredir();
        System.out.println("Conteudos Incritos: " + dev2.getConteudosInscritos());
        System.out.println("Conteudos Concluidos: " + dev2.getConteudosConcluidos());
        System.out.println("XP: " + dev2.calcularTotalXP());

        // CRIANDO INSTRUTORES
        System.out.println("------------------------------------");
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Joana D'arc");
        System.out.println("Nome: " + instrutor.getNome());
        System.out.println("Conteudos Minitrados: " + instrutor.getConteudosMinitrados());
        instrutor.adicionarConteudoMinistrado(mentoria1);
        instrutor.adicionarConteudoMinistrado(curso1);
        instrutor.adicionarConteudoMinistrado(curso2);
        System.out.println("Conteudos Minitrados: " + instrutor.getConteudosMinitrados());

    }

}
