package optional;

import compulsory.School;
import compulsory.Student;

import java.util.List;
import java.util.Map;

public class Solution {
    Map<School, List<Student>> schoolPrefs;
    Map<Student, List<School>> studPrefs;
    public Solution(Map<Student, List<School>> studPrefs, Map<School, List<Student>> schoolPrefs){
        this.schoolPrefs = schoolPrefs;
        this.studPrefs = studPrefs;
    }

}
