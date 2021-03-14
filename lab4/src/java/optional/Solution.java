package optional;

import compulsory.School;
import compulsory.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    Map<School, List<Student>> schoolPrefs;
    Map<Student, List<School>> studPrefs;
    Set<School> schoolSet;
    List<Student> studentList;
    public Solution(Map<Student, List<School>> studPrefs, Map<School, List<Student>> schoolPrefs){
        this.schoolPrefs = schoolPrefs;
        this.studPrefs = studPrefs;
    }
    public Solution(List<Student> studentList, Set<School> schoolSet){
        this.studentList = studentList;
        this.schoolSet = schoolSet;
    }
    public void printSolution(){
        ArrayList<Student> assignedStudents = new ArrayList<>();
        schoolSet.forEach(school -> {
            var studPrefs = school.getSudentPref();
            System.out.println("School " + school.getName() + " has the following students: ");
            for(var stud : studPrefs){
                if(assignedStudents.contains(stud))
                    continue;
                if(school.getCapacity() == 0)
                    break;
                System.out.println(stud.getName());
                assignedStudents.add(stud);
                school.setCapacity(school.getCapacity() - 1);
            }
            System.out.println();
        });

    }
}
