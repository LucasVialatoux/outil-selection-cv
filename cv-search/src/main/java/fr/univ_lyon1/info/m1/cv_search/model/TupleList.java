/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            boolean selected = true;
            int total = 0;
            int compteur = 0;
            double moyenne = 0;
            
            for (Node skill : searchSkillsBox.getChildren()) {
                HBox hb = (HBox)skill;
                Label l = (Label)hb.getChildren().get(0);
                String skillName = l.getText();
                if (!(searchType instanceof MoyenneSearch)) {
                    selected = searchType.calcul(a.getSkill(skillName));
                }
                total = total + a.getSkill(skillName);
                compteur++;
                moyenne = total / compteur;
            }
            //Cas recherche moyenne >=50%
            if (searchType instanceof MoyenneSearch) {
                selected = searchType.calcul((int)moyenne);
            }
            if (selected) {
                Tuple t = new Tuple(a.getName(),moyenne);
                listT.add(t);
            }

        }
        //Tri des candidats
        Collections.sort(listT);
        if (!this.identicList(listT)) {
            list = listT;
            notifyObservers(list);
        }
    }
    
    /** return true if the list contains the same tuple. */
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
