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
* Recherche moyenne des skills >50.
*/
public class MoyenneSearch implements Strategy {
    public final String name;
    private double total = 0;
    private int compteur = 0;
    private double moyenne = 0;
            
    
    public MoyenneSearch() {
        this.name = "Search Average >=50%";
    }
    
    @Override
    public boolean calcul(Applicant a,HBox searchSkillsBox) {
        total = 0;
        compteur = 0;
        moyenne = 0;
        
        for (Node skill : searchSkillsBox.getChildren()) {
            HBox hb = (HBox)skill;
            Label l = (Label)hb.getChildren().get(0);
            String skillName = l.getText();
            total = total + a.getSkill(skillName);
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
    public String getName() {
        return name;
    }
}
