/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

public class Tuple implements Comparable {    
    public String name;
    public double moyenne;

    public Tuple(String name,double moyenne) {
        this.moyenne = moyenne;
        this.name = name;
    }
    
    @Override
    public int compareTo(Object o) {
        int comparemoyenne = (int)((Tuple)o).moyenne;
        /* For Ascending order*/
        return comparemoyenne - (int)this.moyenne;
    }
}
