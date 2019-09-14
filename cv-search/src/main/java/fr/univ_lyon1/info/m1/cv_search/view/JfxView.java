package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.WidgetController;
import fr.univ_lyon1.info.m1.cv_search.model.MoyenneSearch;
import fr.univ_lyon1.info.m1.cv_search.model.NormalSearch;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyConverter;
import fr.univ_lyon1.info.m1.cv_search.model.SuperieurSearch;

import fr.univ_lyon1.info.m1.cv_search.model.Tuple;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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

public class JfxView implements Observer {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox comboBox;
    private WidgetController c;

    /**
     * Create the main view of the application.
     */
    public JfxView(WidgetController c,Stage stage, int width, int height) {
        // Name of window
        stage.setTitle("Search for CV");
        this.c = c;
        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);
        NormalSearch noSearch = new NormalSearch();
        SuperieurSearch suSearch = new SuperieurSearch();
        MoyenneSearch moSearch = new MoyenneSearch();
        ObservableList<Strategy> options = 
            FXCollections.observableArrayList(
                noSearch,
                suSearch,
                moSearch
            );
        comboBox = new ComboBox(options);
        root.getChildren().add(comboBox);
        comboBox.setValue(noSearch);
        comboBox.setConverter(new StrategyConverter());
        
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
    
    
    
    
    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultBox.getChildren().clear();
                Strategy searchType  = (Strategy)comboBox.getValue();
                c.search(searchType,searchSkillsBox);
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

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Tuple> listOfTuple = (ArrayList<Tuple>)arg;
        for (Tuple tpl : listOfTuple) {
            resultBox.getChildren().add(new Label(tpl.name));
        }
    }
}
