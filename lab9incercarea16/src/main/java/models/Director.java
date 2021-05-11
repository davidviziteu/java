package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(name = "Director.findDirectorByName",
        query = "SELECT d FROM Director d WHERE d.name=:name")})
@Table(name = "directors", schema = "public", catalog = "lab9")

public class Director {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "directors_id_seq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public Director(){}

    public Director(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director that = (Director) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Director{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
