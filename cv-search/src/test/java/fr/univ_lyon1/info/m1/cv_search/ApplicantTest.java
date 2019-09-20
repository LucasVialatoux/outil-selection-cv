package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.WidgetController;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import fr.univ_lyon1.info.m1.cv_search.model.AlsoExpSearch;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.MoyenneSearch;
import fr.univ_lyon1.info.m1.cv_search.model.NormalSearch;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.SuperieurSearch;
import fr.univ_lyon1.info.m1.cv_search.model.Tuple;
import fr.univ_lyon1.info.m1.cv_search.model.TupleList;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;
import java.util.ArrayList;
import org.junit.Before;

public class ApplicantTest {
    private TupleList tupleList1;
    private SkillList skillList1;
    private Tuple tple1,tple2;
    
    @Before
    public void setUpTupleList(){
        tupleList1 = new TupleList();
        tple1 = new Tuple("nomUn",50);
        tple2 = new Tuple("nomDeux",20);
        tupleList1.add(tple1);
    }
    
    @Test
    public void testController(){
        //Given
        skillList1 = new SkillList();
        ArrayList<String> returnList;
        WidgetController c = new WidgetController(tupleList1,skillList1);
        c.addButtonSkill("unSkill");
        c.addButtonSkill("");
        c.addButtonSkill(" ");
        c.addButtonSkill("deuxSkill");
        c.removeButtonSkill("unSkill");
        c.removeButtonSkill("unknown");
        
        //When
        returnList = skillList1.getSkillList();
        boolean dontContain_1 = returnList.contains("unSkill");
        boolean dontContain_2 = returnList.contains("unknown");
        boolean doContain_1 = returnList.contains("deuxSkill");
        
        //Then
        assertEquals(false,dontContain_1);
        assertEquals(false,dontContain_2);
        assertEquals(true,doContain_1);
    }
    
    @Test
    public void testSkillList(){
        //Given
        skillList1 = new SkillList();
        ArrayList<String> returnList;
        skillList1.ajoutSkill("unSkill");
        skillList1.ajoutSkill("unSkill");
        skillList1.ajoutSkill("");
        skillList1.ajoutSkill(" ");
        skillList1.ajoutSkill("deuxSkill");
        skillList1.ajoutSkill("troisSkill");
        skillList1.removeSkill("troisSkill");
        
        //When
        returnList = skillList1.getSkillList();
        boolean isTrue_1 = returnList.contains("unSkill");
        boolean isTrue_2 = returnList.contains("deuxSkill");
        boolean isFalse_1 = returnList.contains("troisSkill");
        
        //Then
        assertEquals(true,isTrue_1);
        assertEquals(true,isTrue_2);
        assertEquals(false,isFalse_1);
    }
    
    @Test
    public void testTupleList(){
        //Given
        
        //When
        tupleList1.add(tple1);
        boolean boolContains = tupleList1.list.contains(tple1);
        boolean boolDontContain = tupleList1.list.contains(tple2);
        
        //Then
        assertEquals(true,boolContains);
        assertEquals(false,boolDontContain);
    }
    
    @Test
    public void testTuple(){
        //Given
        
        //When
        int negativ = tple1.compareTo(tple2);
        int positiv = tple2.compareTo(tple1);
        
        //Then
        assertEquals(negativ,-30);
        assertEquals(positiv,30);
    }
    
    @Test
    public void testStrategies(){
        // Given
        NormalSearch nrlSearch = new NormalSearch();
        SuperieurSearch supSearch = new SuperieurSearch();
        MoyenneSearch moySearch = new MoyenneSearch();
        AlsoExpSearch expSearch = new AlsoExpSearch();
        ApplicantBuilder builderA1 = new ApplicantBuilder("applicant1.yaml");
        ApplicantBuilder builderA2 = new ApplicantBuilder("applicant2.yaml");
        Applicant a1 = builderA1.build();
        Applicant a2 = builderA2.build();
        ArrayList<String> searchSkillList = new ArrayList<String>();
        searchSkillList.add("c");
        searchSkillList.add("c++");
        
        // When
        boolean nrlSelected_a1 = nrlSearch.calcul(a1, searchSkillList);
        boolean supSelected_a1 = supSearch.calcul(a1, searchSkillList);
        boolean moySelected_a1 = moySearch.calcul(a1, searchSkillList);
        boolean expSelected_a1 = expSearch.calcul(a1, searchSkillList);
        
        boolean nrlSelected_a2 = nrlSearch.calcul(a2, searchSkillList);
        boolean supSelected_a2 = supSearch.calcul(a2, searchSkillList);
        boolean moySelected_a2 = moySearch.calcul(a2, searchSkillList);
        boolean expSelected_a2 = expSearch.calcul(a2, searchSkillList);
        
        //Then
        assertEquals(true,nrlSelected_a1);
        assertEquals(true,supSelected_a1);
        assertEquals(true,moySelected_a1);
        assertEquals(true,expSelected_a1);
        
        assertEquals(false,nrlSelected_a2);
        assertEquals(false,supSelected_a2);
        assertEquals(true,moySelected_a2);
        assertEquals(false,expSelected_a2);
    }

    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");

        // When
        Applicant a = builder.build();

        // Then
        assertEquals(70, a.getSkill("c++"));
        assertEquals("John Smith", a.getName());
    }

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));

        // When
        ApplicantList list = builder.build();

        // Then
        boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
                assertEquals(90, a.getSkill("c"));
                assertEquals(70, a.getSkill("c++"));
                johnFound = true;
            }
        }
        assertTrue(johnFound);
    }
}
