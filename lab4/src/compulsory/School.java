package compulsory;

import java.util.ArrayList;
import java.util.List;

public class School implements Comparable<School>{
    String name;
    int capacity;
    List<Student> sudentPref = new ArrayList<Student>();
    public School(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public School setName(String name) {
        this.name = name;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public School setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public List<Student> getSudentPref() {
        return sudentPref;
    }

    public School addStudent(Student student) {
        this.sudentPref.add(student);
        return this;
    }

    public void setSudentPref(List<Student> sudentPref) {
        this.sudentPref = sudentPref;
    }

    public School removeStudent(Student student) {
        this.sudentPref.remove(student);
        return this;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(School o) {
        return name.compareTo(o.getName());
    }
}
