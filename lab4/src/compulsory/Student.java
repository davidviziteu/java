package compulsory;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    List<School> preferredSchools = new ArrayList<School>();

    public Student(String name){
        this.name = name;
    }

    public Student addSchool(School s) {
        this.preferredSchools.add(s);
        return this;
    }

    public Student removeSchool(School s) {
        this.preferredSchools.remove(s);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreferredSchools(List<School> preferredSchools) {
        this.preferredSchools = preferredSchools;
    }

    public String getName() {
        return name;
    }

    public List<School> getPreferredSchools() {
        return preferredSchools;
    }

    @Override
    public String toString() {

        return name;
    }
}