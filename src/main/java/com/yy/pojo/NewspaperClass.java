package com.yy.pojo;

public class NewspaperClass {
    private int id;
    private String name;

    public NewspaperClass() {
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
        return "NewspaperClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
