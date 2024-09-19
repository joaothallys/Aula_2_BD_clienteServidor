package org.example;

import dao.AlunoDAO;
import org.example.model.Alunos;
import org.example.model.Curso;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO(); // Inicializa o DAO antes de usá-lo


        Curso cursoDesejado = Curso.ADS;


        List<Alunos> alunosNoCurso = dao.getByCurso(cursoDesejado.name());


        System.out.println("Alunos no curso " + cursoDesejado + ":");
        for (Alunos aluno : alunosNoCurso) {
            System.out.println(aluno.getNome()); // Imprime apenas o nome
        }

        System.out.println("Processo finalizado com sucesso.");
    }
}
