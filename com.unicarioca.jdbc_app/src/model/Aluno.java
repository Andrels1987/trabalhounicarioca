package model;

public class Aluno {

    private int id;
    private String nome;
    private String curso;
    private int idProfessor;
  
      public Aluno() {
    }
    public Aluno( String nome, int idProfessor) {
        this.nome = nome;
        this.idProfessor = idProfessor;
    }
    public Aluno(int id, String nome, String curso, int idProfessor) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.idProfessor = idProfessor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
    
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", curso=" + curso + ", idProfessor=" + idProfessor + "]";
    }
    
    
}
