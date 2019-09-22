/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
* TupleList class extending Observable, will create an ArrayList of Tuple.
*/
public class TupleList extends Observable {
    public ArrayList<Tuple> list = new ArrayList<Tuple>();

    public void add(Tuple a) {
        list.add(a);
    }

    public Object size() {
        return list.size();
    }
    
    /** update the result after search.
     * @param searchType strategy to apply
     * @param skillList list of selected skills
     */
    public void searchWidget(Strategy searchType,ArrayList<String> skillList) {
        ApplicantList listA = new ApplicantListBuilder(new File(".")).build();
        ArrayList<Tuple> listT = new ArrayList<Tuple>();
        for (Applicant a : listA) {
            boolean selected = searchType.calcul(a, skillList);
            if (selected) {
                Tuple t = new Tuple(a.getName(),searchType.getMoyenne());
                listT.add(t);
            }
        }
        //Sort of applicants
        Collections.sort(listT);
        if (!this.equals(listT)) {
            list = listT;
            notifyObservers(list);
        }
    }
    
    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
    }
    
}
