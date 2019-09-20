/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.view.Observer;
import java.util.ArrayList;

public abstract class Observable {
    
    private ArrayList<Observer> obs = new ArrayList();
    
    /** Change all Observer.*/
    public void notifyObservers(Object arg) {
        if (!obs.isEmpty()) {
            for (Observer ob : obs) {
                ob.update(this, arg);
            }
        }
    }
    
    public void addObserver(Observer ob) {
        obs.add(ob);
    }
}
