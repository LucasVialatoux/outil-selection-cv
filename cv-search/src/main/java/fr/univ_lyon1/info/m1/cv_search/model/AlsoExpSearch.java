/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
* Search with skill >50 and skill in experience.
*/
public class AlsoExpSearch implements Strategy {
    public final String name;
    private int value;
    private boolean checkSelected = true;
    
    public AlsoExpSearch() {
        this.name = "Skill > 50 and also experience";
    }

    @Override
    public boolean calcul(Applicant a, HBox searchSkillsBox) {
        checkSelected = true;
        for (Node skill : searchSkillsBox.getChildren()) {
            HBox hb = (HBox)skill;
            Label l = (Label)hb.getChildren().get(0);
            String skillName = l.getText();
            value = a.getSkill(skillName);
            if (value < 50) {
                checkSelected = false;
            }
            if (checkSelected) {
                ArrayList<String> expList = a.getExpSkill();
                for (String exp : expList) {
                    if (skillName == exp) {
                        checkSelected = true;
                    }
                }
            }
        }
        
        return checkSelected;
    }

    @Override
    public int getMoyenne() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
