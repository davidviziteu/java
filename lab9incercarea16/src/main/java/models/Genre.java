package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(name = "Genre.findGenreByName",
        query = "SELECT g FROM Genre g WHERE g.name=:name")})
@Table(name = "genres", schema = "public", catalog = "lab9")
public class Genre {
    private int id;
    private String name;

    public Genre(){}

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre that = (Genre) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GenresEntity{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
