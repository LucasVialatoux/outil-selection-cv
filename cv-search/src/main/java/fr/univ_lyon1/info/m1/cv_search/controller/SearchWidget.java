/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Tuple;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class SearchWidget {
    /**
     * Create the widget used to trigger the search.
     */
    public static ArrayList<Tuple> searchWidget(String searchType,
            HBox searchSkillsBox) {
        ApplicantList listApplicants
            = new ApplicantListBuilder(new File(".")).build();
        ArrayList<Tuple> listOfTuple = new ArrayList();
        for (Applicant a : listApplicants) {
            boolean selected = true;
            int total = 0;
            int compteur = 0;
            double moyenne = 0;
            for (Node skill : searchSkillsBox.getChildren()) {
                String skillName = ((Button) skill).getText();
                selected = selectedApplicant(searchType,a.getSkill(skillName));
                //Calcul moyenne
                total = total + a.getSkill(skillName);
                compteur++;
                moyenne = total / compteur;
            }
            //Cas recherche moyenne >=50%
            selected = selectedApplicant(searchType,(int)moyenne);
            
            if (selected) {
                Tuple t = new Tuple(a.getName(),moyenne);
                listOfTuple.add(t);
            }

        }
        //Tri des candidats
        Collections.sort(listOfTuple);
        return listOfTuple;
    }
    
    /** retourne faux si le candidat ne doit pas être sélectionné.*/
    public static boolean selectedApplicant(String type,int skill) {
        //Cas recherche >50%
        if (skill < 50 && type == "Normal Search") {
            return false;
            //Cas recherche >=60%
        } else if (skill <= 60 && type == "Search All >= 60%") {
            return false;
            //Cas recherche moyenne >=50%
        } else if (skill <= 50 && type == "Search Average >= 50%") {
            return false;
        } else {
            return true;
        }
    }
}
