/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

public class SkillList extends Observable {
    private ArrayList<String> skillList = new ArrayList<String>();
    /** Notify view to add skill button. */
    
    public void ajoutSkill(String text) {
        if (!skillList.contains(text)) {
            skillList.add(text);
            notifyObservers(text);
        }
    }
    
    public void removeSkill(String text) {
        skillList.remove(text);
        notifyObservers("dl button " + text);
    }

    public ArrayList<String> getSkillList() {
        return skillList;
    }
}
