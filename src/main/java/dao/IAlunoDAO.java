package dao;

import org.example.model.Alunos;
import org.example.model.Curso;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    Alunos create(Alunos aluno);
    Alunos update(Alunos aluno);
    void delete (Long matricula);
    List<Alunos> findAll();
    Optional<Alunos> findById(Long matricula);

    List<Alunos> findByCurso(Curso curso);

}
