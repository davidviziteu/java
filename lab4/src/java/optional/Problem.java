package optional;

import com.github.javafaker.Faker;
import compulsory.School;
import compulsory.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem {
    public static void main(String[] args) {

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);

        List<Student> studentList = new LinkedList<Student>(Arrays.asList(students));

        studentList.sort((a, b) -> b.getName().compareTo(a.getName())); //reverse sorting, had no better idea

        System.out.println(studentList.toString());
        Set<School> schoolSet = new TreeSet<>(Arrays.asList(schools));
        System.out.println(schoolSet.toString());

        students[0].setPreferredSchools(Arrays.asList(schools));
        students[1].setPreferredSchools(Arrays.asList(schools));
        students[2].setPreferredSchools(Arrays.asList(schools[0], schools[1]));
        students[3].setPreferredSchools(Arrays.asList(schools[0], schools[1]));

        schools[0].setSudentPref(Arrays.asList(students[3], students[0], students[1], students[2]));
        schools[1].setSudentPref(Arrays.asList(students[0], students[2], students[1]));
        schools[2].setSudentPref(Arrays.asList(students[0], students[1], students[3]));

        Map<Student, List<School>> studPrefs = new HashMap<>();
        Map<School, List<Student>> schoolPrefs = new TreeMap<>();

//        var faker = new Faker();
//        Arrays.stream(students).forEach(s -> s.setName(faker.name().fullName()));
//        Arrays.stream(schools).forEach(s -> s.setName(faker.beer().name()));

        Arrays.stream(students).forEach(s -> studPrefs.put(s, s.getPreferredSchools()));
        Arrays.stream(schools).forEach(s -> schoolPrefs.put(s, s.getSudentPref()));
        System.out.println("");


        System.out.println("Student preferences:");
        studPrefs.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("School preferences:");
        schoolPrefs.forEach((key, value) -> System.out.println(key + ": " + value));

        // pt fiecare scoala sa vad daca un student o vrea
        // pt fiecare student sa vad daca se afla macar la o scoala
        schoolSet.forEach(school -> {
            var result = studentList.stream()
                    .filter(std -> std.getPreferredSchools().contains(school))
                    .collect(Collectors.toList());

            System.out.println("School " + school.toString() + " wanted by: " + result.toString());

        });
        studentList.forEach(student -> {
            var result = schoolSet.stream()
                    .filter(std -> std.getSudentPref().contains(student))
                    .collect(Collectors.toList());

            System.out.println("Student " + student.toString() + " wants the students: " + result.toString());
        });
        System.out.println();
        var solutionInstance = new Solution(studentList, schoolSet);
        solutionInstance.printSolution();
    }
}
