package com.example.stuadentmanager.Model;

public class Classroom {
    int id;
    String name;

    public Classroom(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Classroom(String name) {
        this.name = name;
    }

    public Classroom() {
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



    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
