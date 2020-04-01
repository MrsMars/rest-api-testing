package com.aoher.model;

public class Person {

    private String id;
    private String name;
    private int height;
    private int mass;

    public Person() {
    }

    public Person(String id, String name, int height, int mass) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", mass=" + mass +
                '}';
    }
}
