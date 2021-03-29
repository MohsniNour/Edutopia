package Entities;

public class Subjectt {

private int id;
private String id_Subject;

    public Subjectt() {
    }

    public Subjectt(int id, String id_Subject) {
        this.id = id;
        this.id_Subject = id_Subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(String id_Subject) {
        this.id_Subject = id_Subject;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", id_Subject=" + id_Subject + '}';
    }


}
