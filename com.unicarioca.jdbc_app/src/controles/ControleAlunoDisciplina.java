package controles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dao.Dao;
import model.AlunoDisciplina;

public class ControleAlunoDisciplina {
    public List<AlunoDisciplina> getAllAlunoDisciplinas() {
        List<AlunoDisciplina> alunoDisciplinas = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "SELECT * FROM aluno_disciplina";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            int index = 0;
            while (result.next()) {
                alunoDisciplinas.add(index,
                        new AlunoDisciplina(
                                result.getInt("id"),
                                result.getInt("idAluno"),
                                result.getInt("idDisciplina")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        alunoDisciplinas.sort(Comparator.comparing(AlunoDisciplina::getId));
        return alunoDisciplinas;
        
    }
}
