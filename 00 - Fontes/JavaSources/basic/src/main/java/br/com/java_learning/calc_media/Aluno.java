package br.com.java_learning.calc_media;

public class Aluno {

    private final String nome;
    private final double nota1;
    private final double nota2;
    private final double nota3;

    public Aluno(String nome, double nota1, double nota2, double nota3) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public String getNome() {
        return nome;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getNota3() {
        return nota3;
    }

    /**
     * Calcula a média das notas do aluno.
     *
     * @return a média das notas
     */
    public double calcularMedia() {
        return (nota1 + nota2) / 2;
    }

    @Override
    public String toString() {
        return "Aluno: " + getNome() + "\nNota 1: " + getNota1() + ", Nota 2: " + getNota2() + ", Nota 3: " + getNota3();
    }

}
