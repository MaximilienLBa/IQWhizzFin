package com.lsinf1225.iqwhizz;

import java.util.ArrayList;

public class User {

    private static int id;
    private String login;
    private int age;
    private String mdp;
    private String profil;
    private String mail;

    //petite arraylist pour ajouter facilement et simplement des amis
    //j'ai mis les id ça me paraissait mieux même si dans le rapport ça dit des strings
    private ArrayList<Integer>  amis = new ArrayList<Integer>();


    public int getScore(Question q){
        return 0;//faut d'abord faire la classe score
    }

    //pas encore implémenter, je sais pas encore comment faire :(
    public void askFriendUser(User a){
    }


    //assez simple à faire
    public void addFriendUser(User a){
        this.amis.add(a.id);
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

    public int getAge() { return age; }

    public static int getId() {return id; }

    public String getLogin() { return login; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getProfil() { return profil; }


    public void setAge(int age) { this.age = age; }

    public static void setId(int id) { User.id = id; }

    public void setLogin(String login) { this.login = login; }

    public void setMail(String mail) { this.mail = mail; }

    public void setMdp(String mdp) { this.mdp = mdp; }

    public void setProfil(String profil) { this.profil = profil; }


}

