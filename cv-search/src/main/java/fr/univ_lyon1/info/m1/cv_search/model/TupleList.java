/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.HBox;

public class TupleList extends Observable {
    public ArrayList<Tuple> list = new ArrayList<Tuple>();

    void add(Tuple a) {
        list.add(a);
    }

    public Object size() {
        return list.size();
    }
    
    /** update the result after search. */
    public void searchWidget(Strategy searchType,HBox searchSkillsBox) {
        ApplicantList listA = new ApplicantListBuilder(new File(".")).build();
        ArrayList<Tuple> listT = new ArrayList<Tuple>();
        for (Applicant a : listA) {
            boolean selected = searchType.calcul(a, searchSkillsBox);
            if (selected) {
                Tuple t = new Tuple(a.getName(),searchType.getMoyenne());
                listT.add(t);
            }
        }
        //Sort of applicants
        Collections.sort(listT);
        if (!this.identicList(listT)) {
            list = listT;
            notifyObservers(list);
        }
    }
    
    /** Return true if the list contains the same tuple. */
    public boolean identicList(ArrayList<Tuple> b) {
        if (list == null && b == null) {
            return true;
        }
            
        if ((list == null && b != null) || (list != null && b == null)) {
            return false;
        }

        if (list.size() != b.size()) {
            return false;
        }
            
        for (Tuple t : list) {
            for (Tuple j : b) {
                if (t.equals(j)) {
                    return false;
                }
            }  
        }

        return true;
    }

    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
    }
    
}
