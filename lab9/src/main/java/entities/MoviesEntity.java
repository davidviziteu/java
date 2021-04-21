package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movies", schema = "main", catalog = "")
public class MoviesEntity {
    private Short id;
    private Object title;
    private Object releaseDate;
    private Object duration;
    private Object score;

    @Id
    @Column(name = "id")
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    @Basic
    @Column(name = "release_date")
    public Object getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Object releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "duration")
    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "score")
    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesEntity that = (MoviesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(duration, that.duration) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, duration, score);
    }
}
