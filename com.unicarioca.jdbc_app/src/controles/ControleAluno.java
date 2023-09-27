package controles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dao.Dao;
import model.Aluno;

public class ControleAluno {

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "SELECT * FROM aluno";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            int index = 0;
            while (result.next()) {
                alunos.add(index,
                        new Aluno(result.getInt("id"),
                                result.getString("nome"),
                                result.getString("curso"),
                                result.getInt("idProfessor")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        alunos.sort(Comparator.comparing(Aluno::getNome));
        return alunos;
        
    }
}
