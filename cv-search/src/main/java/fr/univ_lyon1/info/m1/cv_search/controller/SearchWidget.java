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
import fr.univ_lyon1.info.m1.cv_search.model.NormalSearch;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.SuperieurSearch;
import fr.univ_lyon1.info.m1.cv_search.model.MoyenneSearch;
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
    public static ArrayList<Tuple> searchWidget(Strategy searchType,
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
                if (searchType instanceof NormalSearch) {
                    selected = searchType.calcul(a.getSkill(skillName));
                } else if (searchType instanceof SuperieurSearch) {
                    selected = searchType.calcul(a.getSkill(skillName));
                }
                total = total + a.getSkill(skillName);
                compteur++;
                moyenne = total / compteur;
            }
            //Cas recherche moyenne >=50%
            selected = searchType.calcul((int)moyenne);
            
            if (selected) {
                Tuple t = new Tuple(a.getName(),moyenne);
                listOfTuple.add(t);
            }

        }
        //Tri des candidats
        Collections.sort(listOfTuple);
        return listOfTuple;
    }
}
