/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

/**
* Interface for calculate strategy.
*/
public interface Strategy {
    public boolean calcul(int value);
    
    @Override
    public String toString();
    
    public String getName();
}