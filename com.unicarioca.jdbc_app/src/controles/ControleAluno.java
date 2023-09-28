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
import java.util.Comparator;
import java.util.List;

import dao.Dao;
import model.Aluno;
import model.Professor;

public class ControleAluno {

    ControleProfessor cp = new ControleProfessor();

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = Dao.getConnection();) {
            String querySelecet = "SELECT * FROM aluno";
            Statement st = conexao.createStatement();
            ResultSet result = st.executeQuery(querySelecet);
            int index = 0;
            while (result.next()) {
                alunos.add(index,
                        new Aluno(result.getInt("id_aluno"),
                                result.getString("nome"),
                                result.getString("curso"),
                                result.getInt("professor_id_professor")));
            }
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

        alunos.sort(Comparator.comparing(Aluno::getNome));
        return alunos;

    }

    public void addAlunos() throws IOException, SQLException {
        boolean existe = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int continua = 1;
        PreparedStatement preparedStatement = null;
        Aluno aluno = new Aluno();
        try (Connection conexao = Dao.getConnection();) {
            do {
                existe = buscarAlunoPeloNome(br, aluno);

                if (existe) {
                    System.out.println("Um aluno com esse nome já se encontra no banco");
                    System.out.println("Deseja Tentar novamente? ");
                    System.out.println("1- Sim, 2 - Não");
                    continua = Integer.parseInt(br.readLine());
                } else {
                    /// se não existe: adiciona
                    // Busca professor
                    Professor professor = null;
                    do {
                        System.out.println("Inserir nome do professor");
                        String nomeProfessor = br.readLine();
                        professor = cp.buscarProfessorPeloNome(nomeProfessor);
                        if (professor == null) {
                            System.out.println("Nenhum Professor com esse nome");
                            System.out.println("");
                        }
                    } while (professor == null);

                    /* System.out.println("Professor : " + professor);
                    preparedStatement = conexao.prepareStatement("INSERT INTO aluno VALUES(null, ?,?,?)");
                    preparedStatement.setString(1, aluno.getCurso());
                    preparedStatement.setString(2, aluno.getNome());
                    preparedStatement.setInt(3, professor.getId()); */
                    //boolean r = preparedStatement.execute();
                    boolean r = inserirAlunoNoBanco(aluno, professor);
                    if (!r) {
                        System.out.println("aluno inserido");
                    }
                    System.out.println("Adicionar mais alunos?");
                    System.out.println("1- Sim, 2 - Sair");
                    continua = Integer.parseInt(br.readLine());
                }
            } while (continua == 1);

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
                preparedStatement.close();
            }
        }

    }

    public void inserirInfoAluno(BufferedReader br, Aluno aluno) throws IOException {

        try {
            System.out.println("Nome do aluno");
            String nomeAluno = br.readLine();
            aluno.setNome(nomeAluno);
            System.out.println("Curso");
            String curso = br.readLine();
            aluno.setCurso(curso);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean buscarAlunoPeloNome(BufferedReader br, Aluno aluno) {
        ResultSet result = null;
        try (Connection conexao = Dao.getConnection()) {
            inserirInfoAluno(br, aluno);
            String querySelect = "select * from escola.aluno where nome = ? ";
            PreparedStatement preparedStatement = conexao.prepareStatement(querySelect);
            preparedStatement.setString(1, aluno.getNome().trim());
            result = preparedStatement.executeQuery();

            if (result.next()) {
                System.out.println("DENTRO DE..1");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("DENTRO DE..2");
        return false;
    }

    public Boolean inserirAlunoNoBanco(Aluno aluno, Professor professor){
        boolean resultado = false;
        try(Connection conexao = Dao.getConnection()){
            System.out.println("Professor : " + professor);
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO aluno VALUES(null, ?,?,?)");
            preparedStatement.setString(1, aluno.getCurso());
            preparedStatement.setString(2, aluno.getNome());
            preparedStatement.setInt(3, professor.getId());
            resultado = preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

}
