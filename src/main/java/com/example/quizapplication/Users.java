package com.example.quizapplication;

public class Users {
    private int id;
    private String name;
    private int result;

    // Constructor with all properties
    public Users(int id, String name, int result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    // Constructor without ID
    public Users(String name, int result) {
        this.name = name;
        this.result = result;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Result
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    // Override toString for debugging purposes
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", result=" + result +
                '}';
    }
}
