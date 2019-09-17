/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
* Recherche superieur (skill >=60).
*/
public class SuperieurSearch implements Strategy {
    public final String name;
    private double total = 0;
    private int compteur = 0;
    private double moyenne = 0;
    private int value;
    private boolean checkSelected = true;
    
    public SuperieurSearch() {
        this.name = "Search >=60%";
    }
    
    @Override
    public boolean calcul(Applicant a,HBox searchSkillsBox) {
        total = 0;
        compteur = 0;
        moyenne = 0;
        checkSelected = true;
        for (Node skill : searchSkillsBox.getChildren()) {
            HBox hb = (HBox)skill;
            Label l = (Label)hb.getChildren().get(0);
            String skillName = l.getText();
            value = a.getSkill(skillName);
            total = total + value;
            compteur++;
            moyenne = total / compteur;
            if (value < 60) {
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
    public String getName() {
        return name;
    }
}
