
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import controles.ControleAluno;
import controles.ControleProfessor;
import model.Aluno;
import model.Professor;

public class App {
    public static void main(String[] args) throws Exception {

        ControleAluno ca = new ControleAluno();
        ControleProfessor cp = new ControleProfessor();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Opções\n" +
                "1-Exibir todos os Alunos\n" +
                "2-Deletar Aluno\n" +
                "3-Atualizar Aluno\n" +
                "4-Adicionar Aluno\n" +
                "5-Exibir todos os professores\n" +
                "6-Deletar professor\n" +
                "7-Atualizar professor\n" +
                "8-Adicionar professor\n");
        int escolha = Integer.parseInt(br.readLine());
        switch (escolha) {
            case 1:
                List<Aluno> alunos = ca.getAllAlunos();
                System.out.printf("%s\t%s\t%s\t\t%s\n", "ID", "Curso", "Nome", "ID professor");
                for (Aluno a : alunos) {
                    System.out.printf("%d\t%s\t%-11s\t%d\n", a.getId(), a.getCurso(), a.getNome(), a.getIdProfessor());
                }
                break;
            case 2:
                ca.deletarAlunoDoBanco();
                break;
            case 3:
                ca.atualizarAlunoDoBanco();
                break;
            case 4:
                ca.addAlunos();
                break;
            case 5:
            List<Professor> professores = cp.getAllProfessores();
                System.out.printf("%s\t%-11s\t%s\n", "ID", "Nome", "Titulação");
                for (Professor p : professores) {
                    System.out.printf("%d\t%-11s\t%s\n", p.getId(), p.getNome(), p.getTitulacao());
                }
                break;
            case 6:
                cp.deleteProfessor();
                break;
            case 7:
                cp.atualizarProfessor();
                break;
            case 8:
                cp.addProfessor();
                break;
            default:
                System.out.println("Opção não valida");
                break;
        }
        // ca.addAlunos();
        // cp.addProfessor();
        // ca.buscarAlunoPeloId(escolha)
        // ca.getAllAlunos().forEach(System.out::println);
        // ca.deletarAlunoDoBanco();
        // ca.atualizarAlunoDoBanco();
        // List<Professor> profs = cp.buscarProfessorPeloNome("Tavares");
        // System.out.println(profs);
        // cp.atualizarProfessor();
        // cp.getAllProfessores();
        // cp.deleteProfessor();
        // System.out.println("ALUNO: " +ca.buscarAlunoPeloId(1));
    }
}
