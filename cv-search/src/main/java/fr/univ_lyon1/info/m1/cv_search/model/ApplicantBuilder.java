package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class ApplicantBuilder {

    File file;

    public ApplicantBuilder(File f) {
        this.file = f;
    }

    public ApplicantBuilder(String filename) {
        this.file = new File(filename);
    }

    /**
     * Build the applicant from the Yaml file provided to the constructor.
     */
    public Applicant build() {
        Applicant a = new Applicant();
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }
        Map<String, Object> exp = (Map<String, Object>)map.get("experience");
        ArrayList<String> expSkill = expSkill(exp);
        a.setExpSkill(expSkill);
        a.setName((String)map.get("name"));

        // Cast may fail if the Yaml is incorrect. Ideally we should provide
        // clean error messages.
        @SuppressWarnings("unchecked")
        Map<String, Integer> skills = (Map<String, Integer>)map.get("skills");
        for (String skill : skills.keySet()) {
            Integer value = skills.get(skill);
            a.setSkill(skill, value);
        }
        return a;
    }
    
    /** Get all experience skill.*/
    private ArrayList<String> expSkill(Map<String, Object> exp) {
        ArrayList<String> exS = new ArrayList<>();
        for (String o :exp.keySet()) {
            Map<String, ArrayList> ex = (Map<String, ArrayList>)exp.get(o);
            for (Object s : ex.get("keywords")) {
                if (!exS.contains(s)) {
                    exS.add((String) s);
                }
            }
        }
        return exS;
    }
}
