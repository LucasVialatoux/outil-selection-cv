/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

/**
* Skills search strategy with average >50.
*/
public class MoyenneSearch implements Strategy {
    private double total = 0;
    private int compteur = 0;
    private double moyenne = 0;
    
    @Override
    public boolean calcul(Applicant a,ArrayList<String> searchSkillList) {
        total = 0;
        compteur = 0;
        moyenne = 0;
        
        for (String skill : searchSkillList) {
            total = total + a.getSkill(skill);
            compteur++;
            moyenne = total / compteur;
        }
        return moyenne > 50;
    }
    
    @Override
    public int getMoyenne() {
        return (int)moyenne;
    }
    
    @Override
    public String toString() {
        return "Search Average >=50%";
    }
}
