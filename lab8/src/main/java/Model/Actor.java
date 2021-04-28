package Model;

public class Actor {
    private String familyName, surname;
    private int age;
    private double popularity;

    public Actor() {

    }

    public Actor(String familyName, String surname, int age, double popularity) {
        this.familyName = familyName;
        this.surname = surname;
        this.age = age;
        this.popularity = popularity;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRating(double rating) {
        this.popularity = popularity;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public double getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "familyName='" + familyName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", popularity=" + popularity +
                '}';
    }
}