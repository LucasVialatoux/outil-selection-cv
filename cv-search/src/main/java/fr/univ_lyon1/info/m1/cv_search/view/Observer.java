/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.model.Observable;

/**
* Observer interface.
*/
public interface Observer {
    public void update(Observable o, Object arg);
}
