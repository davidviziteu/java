package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "directors", schema = "main", catalog = "")
public class DirectorsEntity {
    private Short id;
    private Object name;
    private Object surname;
    private short age;
    private Object rating;

    @Id
    @Column(name = "id")
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public Object getSurname() {
        return surname;
    }

    public void setSurname(Object surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "age")
    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Basic
    @Column(name = "rating")
    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorsEntity that = (DirectorsEntity) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, rating);
    }
}
