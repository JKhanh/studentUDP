package model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private int id;
    private String name;
    private Date birthYear;
    private String classYear;
    private String address;

    public Student() {
    }

    public Student(int id, String name, Date birthYear, String classYear, String address) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.classYear = classYear;
        this.address = address;
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

    public java.sql.Date getBirthYear() {
        return new java.sql.Date(birthYear.getTime());
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object[] toObject(){
        return new Object[]{getId(), getName(), getBirthYear(), getClassYear(), getAddress()};
    }
}
