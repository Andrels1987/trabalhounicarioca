package controles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import dao.Dao;
import model.Aluno;
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
                        new Professor(result.getInt("id_professor"),
                                result.getString("nome"),
                                result.getString("titulacao")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        professores.sort(Comparator.comparing(Professor::getId));
        return professores;

    }

    public List<Professor> buscarProfessorPeloNome(String nome) throws SQLException {
        List<Professor> professores = new ArrayList<>();
        ResultSet result = null;
        try (Connection conexao = Dao.getConnection();) {
            PreparedStatement preparedStatement = conexao
                    .prepareStatement("Select * from professor where nome LIKE ? ");
            preparedStatement.setString(1, '%' + nome.trim() + '%');
            result = preparedStatement.executeQuery();
            int index = 0;
            while (result.next()) {
                professores.add(index, new Professor(result.getInt("id_professor"),
                        result.getString("nome"),
                        result.getString("titulacao")));
                index++;
            }

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        return professores;
    }

    public void addProfessor() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Professor professor = new Professor();
        boolean continua = true;
        int escolha = 1;
        do {
            inserirInfoProfessor(br, professor);
            boolean professorExiste = professorExiste(br, professor);
            if (professorExiste == true) {
                System.out.println("Um professor com esse nome ja existe no banco");
                System.out.println("Deseja tentar novamente? 1-Sim 2-Não");
                escolha = Integer.parseInt(br.readLine());
                if (escolha != 1) {
                    continua = false;
                }
            } else {
                try (Connection conexao = Dao.getConnection();) {
                    String querySelect = "insert into professor values (null, ?,?)";
                    PreparedStatement prepare = conexao.prepareStatement(querySelect);
                    prepare.setString(1, professor.getNome());
                    prepare.setString(2, professor.getTitulacao());
                    int rows = prepare.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Registro inserido com sucesso");
                    }
                    System.out.println("Deseja adicionar mais professores?");
                    System.out.println("1-Sim 2-Não");
                    escolha = Integer.parseInt(br.readLine());
                    if(escolha != 1){
                        continua = false;
                    }

                } catch (Exception e) {
                    System.out.println("ERRO : " + e.getMessage());
                }
            }

        } while (continua == true);

    }

    public Boolean professorExiste(BufferedReader br, Professor professor) throws IOException {
        // MUDAR PARA RETORNAR OBJECT ALUNO
        // Para testar: comentar a linha abaixo e criar objeto no
        // proprio teste
        //inserirInfoProfessor(br, professor);
        ResultSet result = null;
        try (Connection conexao = Dao.getConnection()) {
            String querySelect = "select * from escola.professor where nome = ? ";
            PreparedStatement preparedStatement = conexao.prepareStatement(querySelect);
            preparedStatement.setString(1, professor.getNome().trim());
            result = preparedStatement.executeQuery();

            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Não existe");
        return false;
    }

    public void inserirInfoProfessor(BufferedReader br, Professor professor) throws IOException {

        try {
            System.out.println("Nome do Professor");
            String nomeAluno = br.readLine();
            professor.setNome(nomeAluno);
            System.out.println("Titulação");
            String titulacao = br.readLine();
            professor.setTitulacao(titulacao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProfessor() throws NumberFormatException, IOException {
        List<Professor> professores = getAllProfessores();
        System.out.printf("%s\t%-11s\t%s\n", "ID", "Nome", "Titulação");
        for (Professor p : professores) {
            System.out.printf("%d\t%-11s\t%s\n", p.getId(), p.getNome(), p.getTitulacao());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escolha o id do professor a ser deletado");
        int id = Integer.parseInt(br.readLine());
        try (Connection conexao = Dao.getConnection();) {
            String querySelect = "delete from professor where id_professor = ?";
            PreparedStatement prepare = conexao.prepareStatement(querySelect);
            prepare.setInt(1, id);
            int rows = prepare.executeUpdate();
            if (rows > 0) {
                System.out.println("Registro deletado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }finally{
            if(br != null){
                br.close();
            }
        }

    }

    public void atualizarProfessor() throws SQLException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Professor professor = null;
        do {
            System.out.println("Inserir nome do professor");
            String nomeProfessor = br.readLine();
            List<Professor> professores = buscarProfessorPeloNome(nomeProfessor);
            professor = escolherProfessor(professores);
        } while (professor == null);
        System.out.println("Professor : " + professor);
        try (Connection conexao = Dao.getConnection();) {
            System.out.println("Digite o Novo nome do professor ou ENTER para pular");
            String nomeProfessor = br.readLine();
            System.out.println("Digite a nova titulacao do professor ou ENTER para pular");
            String titulacao = br.readLine();
            if (nomeProfessor != "") {
                professor.setNome(nomeProfessor);
            }
            if (titulacao != "") {
                professor.setTitulacao(titulacao);
            }

            String querySelect = "update professor set nome = ?, titulacao = ? where id_professor = ?";
            PreparedStatement prepare = conexao.prepareStatement(querySelect);
            prepare.setString(1, professor.getNome());
            prepare.setString(2, professor.getTitulacao());
            prepare.setInt(3, professor.getId());
            int rows = prepare.executeUpdate();
            if (rows > 0) {
                System.out.println("Registro atualizado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

    }

    public Professor escolherProfessor(List<Professor> professores)
            throws SQLException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Professor professor = null;
        if (professores.isEmpty()) {
            System.out.println("Nenhum Professor com esse nome");
            System.out.println("");
        } else {
            for (Professor p : professores) {
                System.out.printf("%d\t%s\n", p.getId(), p.getNome());
            }
            System.out.println("Escolha o ID do professor ");
            int escolha = Integer.parseInt(br.readLine());
            for (Professor p : professores) {
                if (escolha == p.getId())
                    professor = p;
            }
        }
        return professor;
    }

}
