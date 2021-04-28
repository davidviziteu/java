package Model;


public class Director {
    private String familyName, surname;
    private int age;
    private double rating;

    public Director() {

    }

    public Director(String familyName, String surname, int age, double rating) {
        this.familyName = familyName;
        this.surname = surname;
        this.age = age;
        this.rating = rating;
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
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Director{" +
                "familyName='" + familyName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", rating=" + rating +
                '}';
    }
}
