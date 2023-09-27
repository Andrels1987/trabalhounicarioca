package model;

public class AlunoDisciplina {

    private int id;
    private int idAluno;
    private int idProfessor;

    public AlunoDisciplina() {
        
    }
    public AlunoDisciplina(int id, int idAluno, int idProfessor) {
        this.id = id;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
    @Override
    public String toString() {
        return "AlunoDisciplina [id=" + id + ", idAluno=" + idAluno + ", idProfessor=" + idProfessor + "]";
    }

    
    
}
