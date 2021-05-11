package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.findMovieByName",
                query = "SELECT m FROM Movie m WHERE m.title=:title")})
@Table(name = "movies", schema = "public", catalog = "lab9")
public class Movie {
    private int id;
    private String title;
    private Date launchDate;
    private Integer minutes;
    private Double rating;

    public Movie() {
    }

    public Movie(String title, Integer minutes) {
        this.title = title;
        this.minutes = minutes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "movies_id_seq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "launch_date")
    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date releaseDate) {
        this.launchDate = releaseDate;
    }

    @Basic
    @Column(name = "minutes")
    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer duration) {
        this.minutes = duration;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double score) {
        this.rating = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie that = (Movie) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title)
                && Objects.equals(launchDate, that.launchDate) && Objects.equals(minutes, that.minutes)
                && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, launchDate, minutes, rating);
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", launchDate=" + launchDate
                + ", minutes=" + minutes + ", rating=" + rating + '}';
    }
}
