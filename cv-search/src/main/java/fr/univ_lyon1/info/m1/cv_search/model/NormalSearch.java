/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

/**
* Normal search (skill >50).
*/
public class NormalSearch implements Strategy {
    private double total = 0;
    private int compteur = 0;
    private double moyenne = 0;
    private int value;
    private boolean checkSelected = true;
    
    @Override
    public boolean calcul(Applicant a,ArrayList<String> searchSkillList) {
        total = 0;
        compteur = 0;
        moyenne = 0;
        checkSelected = true;
        for (String skill : searchSkillList) {
            value = a.getSkill(skill);
            total = total + value;
            compteur++;
            moyenne = total / compteur;
            if (value < 50) {
                checkSelected = false;
            }
        }
        return checkSelected;
    }
    
    @Override
    public int getMoyenne() {
        return (int)moyenne;
    }
    
    @Override
    public String toString() {
        return "Normal Search";
    }
}
