package com.lsinf1225.iqwhizz;

public class Category { //classe sur les catégories
    private int id;
    private String name;

    public static final int PROGRAMMING = 1;
    public static final int GEOGRAPHY = 2;
    public static final int MATH = 3;

    public Category(){

    }

    //Bloc de getter and setter
    public Category(String name) {
        this.name = name;
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

    //méthode permettant d'avoir le nom de la catégorie pour pouvoir l'afficher par la suite
    @Override
    public String toString () {
        return getName();
    }
}
