package com.example.andro.church;


public class Contact {
    private String name, addres, numberphine, Father_of_confession, Date_of_visit;
    private int Children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getNumberphine() {
        return numberphine;
    }

    public void setNumberphine(String numberphine) {
        this.numberphine = numberphine;
    }

    public String getFather_of_confession() {
        return Father_of_confession;
    }

    public void setFather_of_confession(String father_of_confession) {
        Father_of_confession = father_of_confession;
    }

    public String getDate_of_visit() {
        return Date_of_visit;
    }

    public void setDate_of_visit(String date_of_visit) {
        Date_of_visit = date_of_visit;
    }

    public int getChildren() {
        return Children;
    }

    public void setChildren(int children) {
        Children = children;
    }

    public Contact(int id, String name, String addres, String numberphine, String father_of_confession, String date_of_visit, int children) {
        this.id = id;
        this.name = name;
        this.addres = addres;
        this.numberphine = numberphine;
        Father_of_confession = father_of_confession;
        Date_of_visit = date_of_visit;
        Children = children;

    }
}
