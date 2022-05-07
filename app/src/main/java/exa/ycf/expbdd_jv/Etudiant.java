package exa.ycf.expbdd_jv;

class Etudiant {


    private long id;
    private String name;
    private double note;

    public Etudiant(long id, String name, double note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

}
