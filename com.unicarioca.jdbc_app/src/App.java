import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import controles.ControleAluno;

public class App {
    public static void main(String[] args) throws Exception {
        
        ControleAluno ca = new ControleAluno();
        //ca.addAlunos(); 
        ca.getAllAlunos().forEach(System.out::println);
    }
}
