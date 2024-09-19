package org.example.util;

import dao.AlunoDAO;
import org.example.model.Alunos;

import java.util.List;

public class Funcoes {


    public static void print(Alunos alunos) {
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Alunos> alunosList = alunoDAO.getAll();

        if (alunosList.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (Alunos aluno : alunosList) {
                System.out.println("Matrícula: " + aluno.getMatricula());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Maioridade: " + (aluno.isMaioridade() ? "Sim" : "Não"));
                System.out.println("Curso: " + aluno.getCurso().name());
                System.out.println("Sexo: " + aluno.getSexo());
                System.out.println("----------------------------");
            }
        }
    }

    public static void listarAlunos() {
        AlunoDAO alunoDAO = new AlunoDAO(); // Cria uma instância do DAO
        List<Alunos> alunosList = alunoDAO.getAll(); // Obtém todos os alunos

        if (alunosList.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (Alunos aluno : alunosList) {
                System.out.println("Matrícula: " + aluno.getMatricula());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Maioridade: " + (aluno.isMaioridade() ? "Sim" : "Não"));
                System.out.println("Curso: " + aluno.getCurso().name());
                System.out.println("Sexo: " + aluno.getSexo());
                System.out.println("----------------------------");
            }
        }
    }

}
