package model;

public class Professor {
    private int id;
    private String nome;
    private String titulacao;
  
      public Professor() {
    }
    public Professor( String nome, String titulacao) {
        this.nome = nome;
        this.titulacao = titulacao;
    }
    public Professor(int id, String nome, String titulacao) {
        this.id = id;
        this.nome = nome;
        this.titulacao = titulacao;
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
    public String getTitulacao() {
        return titulacao;
    }
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", titulacao=" + titulacao + "]";
    }

    

}
