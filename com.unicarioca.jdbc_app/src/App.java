public class App {
    public static void main(String[] args) throws Exception {
        
        ControleAluno ca = new ControleAluno();
        //ca.addAlunos(); 
        ca.getAllAlunos().forEach(System.out::println);
    }
}
