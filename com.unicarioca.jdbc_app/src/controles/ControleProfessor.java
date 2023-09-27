package controles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void addProfessor(Professor professor) {

        try (Connection conexao = Dao.getConnection();) {
            String querySelect = "insert into professor values (null, ?,?)";
            PreparedStatement prepare = conexao.prepareStatement(querySelect);
            prepare.setString(1, professor.getNome());
            prepare.setString(2, professor.getTitulacao());
            int rows = prepare.executeUpdate();
            if(rows > 0){
                System.out.println("Registro inserido com sucesso");            }
            
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
            if(rows > 0){
                System.out.println("Registro deletado com sucesso");            }
            
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }
        
    }

    
}
