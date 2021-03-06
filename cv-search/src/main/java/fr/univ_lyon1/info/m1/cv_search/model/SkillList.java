/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

/**
* SkillList creating an ArrayList of skills, extending Observable class.
*/
public class SkillList extends Observable {
    private ArrayList<String> skillList = new ArrayList<String>();
    
    /** 
     * Notify view to add skill button.
     * @param text represents skill
     */
    public void ajoutSkill(String text) {
        text = text.trim();
        if (!skillList.contains(text) && !text.equals("")) {
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
