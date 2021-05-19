package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "lab10")
@Getter
@Setter
public class Person {
    private Integer id;
    private String name;

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_id_seq")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person {" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
