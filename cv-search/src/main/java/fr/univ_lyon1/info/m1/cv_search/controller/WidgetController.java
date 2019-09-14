/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.TupleList;
import javafx.scene.layout.HBox;


public class WidgetController {
    
    public TupleList list;
    
    public WidgetController(TupleList list) {
        this.list = list;
    }
    
    public void search(Strategy searchType,HBox searchSkillsBox) {
        list.searchWidget(searchType, searchSkillsBox);
    }

}
