package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.WidgetController;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.TupleList;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     * @param stage represents the stage
     * @throws java.lang.Exception when incorrect stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        TupleList t = new TupleList();
        SkillList s = new SkillList();
        WidgetController c = new WidgetController(t,s);
        JfxView vue = new JfxView(c,stage, 600, 600);
        JfxView vue2 = new JfxView(c,new Stage(), 600, 600);
        t.addObserver(vue);
        s.addObserver(vue);
        t.addObserver(vue2);
        s.addObserver(vue2);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     * @param args classic args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
