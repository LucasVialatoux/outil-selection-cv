/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.Observable;
import javafx.scene.control.Button;

public class Skill extends Observable {
    
    /** notify view to add skill button. */
    public void ajoutSkill(String text) {
        setChanged();
        notifyObservers(text);
    }
    
    public void removeSkill(Button b) {
        setChanged();
        notifyObservers(b);
    }
}
