package com.lsinf1225.iqwhizz;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private int id;
    private String login;
    private String age;
    private String mdp;
    private String profil;
    private String mail;
    private String username;
    public int score;

    private boolean demande;
    private int idask;


    //petite arraylist pour ajouter facilement et simplement des amis
    //j'ai mis les id ça me paraissait mieux même si dans le rapport ça dit des strings
    private ArrayList<Integer>  amis = new ArrayList<Integer>();

    public User (){

    }
    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    //pas encore implémenter, je sais pas encore comment faire :(
    public void askFriendUser(User a){
        if(a.id != -1)
        {
            a.demande=true;
            a.idask=this.id;
        }
    }


    //assez simple à faire
    public void addFriendUser(User a){
        if((this.demande==true)&&(a.id==this.idask)) {
            this.amis.add(a.id);
        }
    }

    //User a doit être dans la liste sinon ça retourne false
    public boolean removeFriendUser(User a){
        int i=0;
        //si la liste est vide
        if (i > this.amis.size()-1){return false;}

        while(this.amis.get(i)!=a.id){
            i++;

            //si on dépasse la liste
            if (i > this.amis.size()-1){return false;}
        }
        this.amis.remove(i);
        return true;
    }

    public ArrayList<Integer> getAmis() { return amis; }

    public String getAge() { return age; }

    public int getId() {return id; }

    public String getLogin() { return login; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getProfil() { return profil; }

    public String getUsername() {
        return username;
    }

    public void setAge(String age) { this.age = age; }

    public void setId(int id) { this.id = id; }

    public void setLogin(String login) { this.login = login; }

    public void setMail(String mail) { this.mail = mail; }

    public void setMdp(String mdp) { this.mdp = mdp; }

    public void setProfil(String profil) { this.profil = profil; }

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

