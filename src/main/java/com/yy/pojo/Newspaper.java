package com.yy.pojo;

public class Newspaper {
    private int id;
    private String name;
    private String publisher;
    private String cycle;
    private double offer;
    private String content;
    private int classify_id;

    public Newspaper() {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", cycle='" + cycle + '\'' +
                ", offer=" + offer +
                ", content='" + content + '\'' +
                ", classify_id=" + classify_id +
                '}';
    }
}
