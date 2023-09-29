package controles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import dao.Dao;


public class ControleUnicarioca {
    public void getTodosRegistros() {
        //List<Professor> professores = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "select a.nome as aluno,p.nome as professor, d.nome as disciplina\n" + 
                    "from aluno a join aluno_disciplina ad  on a.id = ad.idAluno\n" + 
                    "join professor p on a.idProfessor = p.id\n" + 
                    "join disciplina d on ad.idDisciplina = d.id order by ad.idDisciplina asc, a.id";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            
            while (result.next()) {
                System.out.printf("%s\t%s\t\t%s\n",
                    result.getString("aluno"),result.getString("professor"),result.getString("disciplina"));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        
    }
}
