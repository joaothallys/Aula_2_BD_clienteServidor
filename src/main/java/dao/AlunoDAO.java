package dao;

import org.example.config.ConnectionFactory;
import org.example.model.Alunos;
import org.example.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {


    public Alunos create(Alunos alunos) {
        String query = "INSERT INTO alunos (nome, maioridade, curso, sexo) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, alunos.getNome());
            preparedStatement.setBoolean(2, alunos.isMaioridade());
            preparedStatement.setString(3, alunos.getCurso().getNome());
            preparedStatement.setString(4, alunos.getSexo());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        alunos.setMatricula(generatedKeys.getLong(1)); // Atualiza a matrícula com o ID gerado
                    }
                }
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Alunos getById(long matricula) {
        String query = "SELECT * FROM alunos WHERE matricula = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, matricula);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Alunos aluno = new Alunos();
                    aluno.setMatricula(resultSet.getLong("matricula"));
                    aluno.setNome(resultSet.getString("nome"));
                    aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                    aluno.setCurso(Curso.valueOf(resultSet.getString("curso")));
                    aluno.setSexo(resultSet.getString("sexo"));
                    return aluno;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Se o aluno não for encontrado
    }


    public boolean update(Alunos alunos) {
        String query = "UPDATE alunos SET nome = ?, maioridade = ?, curso = ?, sexo = ? WHERE matricula = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alunos.getNome());
            preparedStatement.setBoolean(2, alunos.isMaioridade());
            preparedStatement.setString(3, alunos.getCurso().getNome());
            preparedStatement.setString(4, alunos.getSexo());
            preparedStatement.setLong(5, alunos.getMatricula());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean delete(long matricula) {
        String query = "DELETE FROM alunos WHERE matricula = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, matricula);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Alunos> getAll() {
        String query = "SELECT * FROM alunos";
        List<Alunos> alunosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Alunos aluno = new Alunos();
                    aluno.setMatricula(resultSet.getLong("matricula"));
                    aluno.setNome(resultSet.getString("nome"));
                    aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                    aluno.setCurso(Curso.valueOf(resultSet.getString("curso")));
                    aluno.setSexo(resultSet.getString("sexo"));
                    alunosList.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunosList;
    }

    public List<Alunos> getByCurso(String nomeCurso) {
        String query = "SELECT * FROM alunos WHERE curso = ?";
        List<Alunos> alunosList = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomeCurso);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Alunos aluno = new Alunos();
                    aluno.setMatricula(resultSet.getLong("matricula"));
                    aluno.setNome(resultSet.getString("nome"));
                    aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                    aluno.setCurso(Curso.valueOf(resultSet.getString("curso").toUpperCase()));
                    aluno.setSexo(resultSet.getString("sexo"));
                    alunosList.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunosList;
    }
}
