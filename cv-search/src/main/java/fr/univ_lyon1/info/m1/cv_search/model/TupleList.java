/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
        list.clear();
        for (Applicant a : listA) {
            boolean selected = true;
            int total = 0;
            int compteur = 0;
            double moyenne = 0;
            for (Node skill : searchSkillsBox.getChildren()) {
                String skillName = ((Button) skill).getText();
                if (!(searchType instanceof MoyenneSearch)) {
                    selected = searchType.calcul(a.getSkill(skillName));
                }
                total = total + a.getSkill(skillName);
                compteur++;
                moyenne = total / compteur;
            }
            //Cas recherche moyenne >=50%
            if (moyenne != 0) {
                selected = searchType.calcul((int)moyenne);
            }
            
            if (selected) {
                Tuple t = new Tuple(a.getName(),moyenne);
                list.add(t);
            }

        }
        //Tri des candidats
        Collections.sort(list);
        setChanged();
        notifyObservers(list);
    }

    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
    }
    
}
