import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import controles.ControleAluno;
import controles.ControleProfessor;
import model.Aluno;

public class Teste {

    ControleAluno ca = new ControleAluno();
   ControleProfessor cp = new ControleProfessor();
    @Test
    public void listSize(){
        Assertions.assertEquals(6, ca.getAllAlunos().size());
    }

    @Test
    public void isEmpty(){
        Assertions.assertTrue(true == !ca.getAllAlunos().isEmpty());
    }

     @Test
    public void alunoExiste(){
        Aluno aluno = new Aluno();
        aluno.setNome("Manoel");
        aluno.setCurso("Redes");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean existe = false;
        try {
            existe = ca.alunoExiste(br, aluno);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(true == existe);
    }   

    @Test
    public void buscarAluno(){
        Aluno a = ca.buscarAlunoPeloId(2);
        System.out.println(a);
        Assertions.assertNotNull(a);
    }
}
