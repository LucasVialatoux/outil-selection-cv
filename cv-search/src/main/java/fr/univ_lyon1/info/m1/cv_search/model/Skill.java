/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.control.Label;

public class Skill extends Observable {
    
    /** Notify view to add skill button. */
    public void ajoutSkill(String text) {
        notifyObservers(text);
    }
    
    public void removeSkill(Label l) {
        notifyObservers(l);
    }
}
