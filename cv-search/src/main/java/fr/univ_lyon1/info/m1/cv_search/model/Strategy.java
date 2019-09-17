/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.layout.HBox;

/**
* Interface to calculate a strategy.
*/
public interface Strategy {
    public boolean calcul(Applicant a,HBox searchSkillsBox);
    
    public int getMoyenne();
    
    @Override
    public String toString();
    
    public String getName();
}