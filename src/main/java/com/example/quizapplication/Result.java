package com.example.quizapplication;

public class Result {
    private int ID,Result;
    private String Useranme;

    public int getID() {
        return ID;
    }

    public int getResult() {
        return Result;
    }

    public String getUseranme() {
        return Useranme;
    }
    public Result(String propUsername,int propResult){
        Useranme=propUsername;
        Result=propResult;
    }
}
