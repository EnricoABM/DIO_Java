package com.dio.bootcamp.conteudo;

import com.dio.bootcamp.user.Instrutor;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Curso extends Conteudo {

    private Set<Aula> aulas = new LinkedHashSet<>();
    private Instrutor instrutor;

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void adicionarAula(Aula aula) {
        aulas.add(aula);
    }

    public void removerAula(Aula aula) {
        aulas.remove(aula);
    }

    @Override
    public double calcularXP() {
        return XP_PADRAO * calcularCargaHorario();
    }

    public double calcularCargaHorario() {
        return aulas.stream()
                .mapToDouble(conteudo -> conteudo.getDuracaoMinutos())
                .sum();
    }

    @Override
    public String toString() {
        return "Curso{" + "titulo=" + titulo + ", descricao=" + descricao + ", cargaHoraria=" + calcularCargaHorario() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, calcularCargaHorario());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.aulas, other.aulas)) {
            return false;
        }
        return Objects.equals(this.instrutor, other.instrutor);
    }
    
    

}
