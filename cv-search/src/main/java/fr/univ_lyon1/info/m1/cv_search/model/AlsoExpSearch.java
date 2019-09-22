/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

/**
 * Search strategy with skill >50 and skill in experience.
*/
public class AlsoExpSearch implements Strategy {
    private int value;
    private boolean checkSelected = true;

    @Override
    public boolean calcul(Applicant a, ArrayList<String> searchSkillList) {
        checkSelected = true;
        for (String skill : searchSkillList) {
            value = a.getSkill(skill);
            if (value < 50) {
                checkSelected = false;
            }
            if (checkSelected) {
                ArrayList<String> expList = a.getExpSkill();
                for (String exp : expList) {
                    if (skill == exp) {
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
    public String toString() {
        return "Skill > 50 and also experience";
    }
    
}
