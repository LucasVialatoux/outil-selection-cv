/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

/**
* Recherche superieur (skill >=60).
*/
public class SuperieurSearch implements Strategy {
    public final String name;
    
    public SuperieurSearch() {
        this.name = "Search >=60%";
    }
    
    @Override
    public boolean calcul(int value) {
        return value > 60;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
