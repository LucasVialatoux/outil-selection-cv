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
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author lucas
 */
public class SearchWidget {
    /**
     * Create the widget used to trigger the search.
     */
    public static ArrayList<Tuple> searchWidget(String searchType, HBox searchSkillsBox) {
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
                //Cas recherche normale
                if (a.getSkill(skillName) < 50 
                        && searchType == "Normal Search") {
                    selected = false;
                    break;
                    //Cas recherche >=60%
                } else if (a.getSkill(skillName) <= 60 
                        && searchType == "Search All >= 60%") {
                    selected = false;
                    break;
                } 
                //Calcul moyenne
                total = total + a.getSkill(skillName);
                compteur++;
                moyenne = total / compteur;
            }
            //Cas recherche moyenne >=50%
            if (moyenne <= 50 
                    && searchType == "Search Average >= 50%") {
                selected = false;
            }
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
