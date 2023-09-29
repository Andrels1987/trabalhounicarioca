package controles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dao.Dao;
import model.Disciplina;

public class ControleDisciplina {
    
    public List<Disciplina> getAllDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "SELECT * FROM disciplina";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            int index = 0;
            while (result.next()) {
                disciplinas.add(index,
                        new Disciplina(result.getInt("id"),
                                result.getString("nome"),
                                result.getInt("carga")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        disciplinas.sort(Comparator.comparing(Disciplina::getNome));
        return disciplinas;
        
    }

    
}
