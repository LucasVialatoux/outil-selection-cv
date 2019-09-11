package fr.univ_lyon1.info.m1.cv_search.view;

import java.io.File;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxView {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox comboBox;

    /**
     * Create the main view of the application.
     */
    public JfxView(Stage stage, int width, int height) {
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);
        
        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Normal Search",
                "Search All >= 60%",
                "Search Average >= 50%"
            );
        comboBox = new ComboBox(options);
        root.getChildren().add(comboBox);
        comboBox.setValue("Normal Search");
        
        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);
        

        Node search = createSearchWidget();
        root.getChildren().add(search);

        Node resultBox = createResultsWidget();
        root.getChildren().add(resultBox);
        
        
        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
    

    /**
     * Create the text field to enter a new skill.
     */
    private Node createNewSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textField.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }

                Button skillBtn = new Button(text);
                searchSkillsBox.getChildren().add(skillBtn);
                skillBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        searchSkillsBox.getChildren().remove(skillBtn);
                    }
                });

                textField.setText("");
                textField.requestFocus();
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }
    
    public class Tuple implements Comparable {
        
        public String name;
        public int moyenne;
        
        Tuple(String name,int moyenne) {
            this.moyenne = moyenne;
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            int comparemoyenne = ((Tuple)o).moyenne;
            /* For Ascending order*/
            return comparemoyenne - this.moyenne;
        }
    }
    
    
    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
                ApplicantList listApplicants
                    = new ApplicantListBuilder(new File(".")).build();
                resultBox.getChildren().clear();
                String searchType = comboBox.getValue().toString();
                ArrayList<Tuple> listOfTuple = new ArrayList();
                
                for (Applicant a : listApplicants) {
                    boolean selected = true;
                    int total = 0;
                    int compteur = 0;
                    int moyenne = 0;
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
                for (Tuple tpl : listOfTuple) {
                    resultBox.getChildren().add(new Label(tpl.name));
                }
            }
        });
        return search;
    }
    
    /**
     * Create the widget showing the list of skills currently searched
     * for.
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }
}
