package Entities;

import javax.persistence.*;

@Entity
@Table(name = "actors", schema = "main", catalog = "")
public class ActorsEntity {
    private Short id;
    private Object name;
    private Object surname;
    private short age;
    private Object popularity;

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
    @Column(name = "popularity")
    public Object getPopularity() {
        return popularity;
    }

    public void setPopularity(Object popularity) {
        this.popularity = popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorsEntity that = (ActorsEntity) o;

        if (age != that.age) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (popularity != null ? !popularity.equals(that.popularity) : that.popularity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (int) age;
        result = 31 * result + (popularity != null ? popularity.hashCode() : 0);
        return result;
    }
}
