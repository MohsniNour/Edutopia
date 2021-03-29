package Entities;

public class LigneExamen {
    private int idLigne;
    private User etudiant;
    private Exam exam;
    private float note;

    public LigneExamen(){
        
    }
    
    public LigneExamen(int idLigne, User etudiant, Exam exam, float note) {
        this.idLigne = idLigne;
        this.etudiant = etudiant;
        this.exam = exam;
        this.note = note;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public User getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
    
}
