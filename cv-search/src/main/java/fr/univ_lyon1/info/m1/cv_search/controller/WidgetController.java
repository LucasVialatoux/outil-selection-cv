/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.TupleList;

/**
* Main controller of the application.
*/
public class WidgetController {
    
    public TupleList list;
    public SkillList s;
    
    /**
     * WidgetController constructor.
     * @param list represents the list of applicants.
     * @param s represents list of skills entered by the user.
     */
    public WidgetController(TupleList list,SkillList s) {
        this.list = list;
        this.s = s;
    }
    
    public void addButtonSkill(String text) {
        s.ajoutSkill(text);
    }
    
    public void removeButtonSkill(String l) {
        s.removeSkill(l);
    }
    
    public void search(Strategy searchType) {
        list.searchWidget(searchType, s.getSkillList());
    }

}
