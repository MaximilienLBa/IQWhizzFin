package com.lsinf1225.iqwhizz;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private int id;
    private String age;
    private String mdp;
    private String mail;
    private String username;
    public int score;



    //petite arraylist pour ajouter facilement et simplement des amis
    //j'ai mis les id ça me paraissait mieux même si dans le rapport ça dit des strings


    private static String[] amis = new String[10];

    public User (){

    }
    public String getAge() { return age; }

    public int getId() {return id; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getUsername() {
        return username;
    }

    public void setAge(String age) { this.age = age; }

    public void setId(int id) { this.id = id; }


    public void setMail(String mail) { this.mail = mail; }

    public void setMdp(String mdp) { this.mdp = mdp; }


    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public  void setScore(int score) {
        this.score = score;
    }

}

