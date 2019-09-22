package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* Applicant model using an HashMap and an ArrayList.
*/
public class Applicant {
    Map<String, Integer> skills = new HashMap<String, Integer>();
    private ArrayList<String> expSkill = new ArrayList<String>();
    String name;

    public int getSkill(String string) {
        return skills.getOrDefault(string, 0);
    }

    public void setSkill(String string, int value) {
        skills.put(string, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getExpSkill() {
        return expSkill;
    }

    public void setExpSkill(ArrayList<String> expSkill) {
        this.expSkill = expSkill;
    }
}
