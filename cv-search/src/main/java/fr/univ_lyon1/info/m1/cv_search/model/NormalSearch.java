/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

/**
* Recherche normal (skill >50).
*/
public class NormalSearch implements Strategy {
    public final String name;

    public NormalSearch() {
        this.name = "Normal Search";
    }
    
    @Override
    public boolean calcul(int value) {
        return value >= 50;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
