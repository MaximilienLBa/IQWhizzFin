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
    private int numAmis = 0;

    private boolean demande;
    private int idask;
    private String loginAsk;
    private String friend;


    //petite arraylist pour ajouter facilement et simplement des amis
    //j'ai mis les id ça me paraissait mieux même si dans le rapport ça dit des strings


    private static String[] amis = new String[10];

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
        if((this.demande==true)&&(a.id==this.idask)&&(a.login==this.loginAsk)) {
            if(this.numAmis>amis.length)
            {
                amis = new String[amis.length+1];
            }
            int j = 0;
            while(j<this.amis.length)
            {
                j++;
            }
            this.amis[j]=(a.login);
        }
    }

    //User a doit être dans la liste sinon ça retourne false
    public boolean removeFriendUser(User a){
        int i=0;
        //si la liste est vide
        if (i > this.amis.length-1){return false;}

        while(this.amis[i]!=a.login){
            i++;

            //si on dépasse la liste
            if (i > this.amis.length-1){return false;}
        }
        this.amis[i]=null;
        return true;
    }

    public static String[] getAmis() { return amis; }

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

    public void setLoginAsk(String loginAmi){
        this.loginAsk=loginAsk;
    }

    public String getLoginAsk()
    {
        return loginAsk;
    }

    public String getFriend(){
        return friend;
    }

    public void setFriend(String friend){
        this.friend=friend;
    }

    public void setIdask(int idask) { this.idask=idask;}

    public int getIdask() {return idask;}

}

