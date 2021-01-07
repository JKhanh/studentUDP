package model;

public class Subject {
    private int id;
    private String name;
    private int tinChi;
    private String description;

    public Subject() {
    }

    public Subject(int id, String name, int tinChi, String description) {
        this.id = id;
        this.name = name;
        this.tinChi = tinChi;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
