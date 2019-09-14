/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.util.StringConverter;
 
public class StrategyConverter extends StringConverter<Strategy> {
    
    @Override
    public String toString(Strategy strat) {
        return strat.getName();
    }

    @Override
    public Strategy fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
