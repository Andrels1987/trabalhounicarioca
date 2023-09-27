package model;

public class Disciplina {
    private int id;
    private String nome;
    private int carga;

    public Disciplina() {
        
    }
    public Disciplina(int id, String nome, int carga) {
        this.id = id;
        this.nome = nome;
        this.carga = carga;
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
    public int getCarga() {
        return carga;
    }
    public void setCarga(int carga) {
        this.carga = carga;
    }
    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", nome=" + nome + ", carga=" + carga + "]";
    }

    
    
}
