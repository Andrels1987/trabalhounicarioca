package controles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dao.Dao;

import model.Professor;

public class ControleProfessor {

    public List<Professor> getAllProfessores() {
        List<Professor> professores = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "SELECT * FROM professor";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            int index = 0;
            while (result.next()) {
                professores.add(index,
                        new Professor(result.getInt("id"),
                                result.getString("nome"),
                                result.getString("titulacao")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        professores.sort(Comparator.comparing(Professor::getNome));
        return professores;

    }

    public Professor buscarProfessorPeloNome(String nome) throws SQLException {
        Professor professor = new Professor();
        ResultSet result = null;
        try (Connection conexao = Dao.getConnection();) {
            PreparedStatement preparedStatement = conexao.prepareStatement("Select * from professor where nome = ? ");
            preparedStatement.setString(1, nome.trim());
            result = preparedStatement.executeQuery();
            if (result.next()) {
                System.out.println("RESULTSET DENTRO DE BUSCA professor : " + result.getString("nome"));
                professor.setId(result.getInt("id_professor"));
                professor.setNome(result.getString("nome"));
                professor.setTitulacao(result.getString("titulacao"));
                return professor;
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        return null;

    }

    public void addProfessor(Professor professor) {

        try (Connection conexao = Dao.getConnection();) {
            String querySelect = "insert into professor values (null, ?,?)";
            PreparedStatement prepare = conexao.prepareStatement(querySelect);
            prepare.setString(1, professor.getNome());
            prepare.setString(2, professor.getTitulacao());
            int rows = prepare.executeUpdate();
            if (rows > 0) {
                System.out.println("Registro inserido com sucesso");
            }

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

    }

    public void deleteProfessor(Professor professor) {

        try (Connection conexao = Dao.getConnection();) {
            String querySelect = "delete from professor where nome = ?";
            PreparedStatement prepare = conexao.prepareStatement(querySelect);
            prepare.setString(1, professor.getNome());
            int rows = prepare.executeUpdate();
            if (rows > 0) {
                System.out.println("Registro deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

    }

}
