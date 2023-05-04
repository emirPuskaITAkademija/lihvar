package ba.lihvarenje.movie;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "movies", catalog = "sakila")
@NamedQueries({
        @NamedQuery(name="Movie.findAll", query = "SELECT m FROM Movie m"),
        @NamedQuery(name="Movie.findByMovieId", query = "SELECT m FROM Movie m WHERE m.movieId=:filmId"),
})
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movie_id")
    private Integer movieId;
    @Column(name = "movie_title")
    @Basic(optional = false)
    private String movieTitle;
    private String director;
    //YEAR java.util.Date
    @Basic(optional = false)
    @Column(name = "year")
//    @Temporal(TemporalType.DATE)
    @Convert(converter = MovieYearConverter.class)
    private LocalDate year;

    @Column(name = "genre_id")
    private Integer genreId;

    public Movie() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", genreId=" + genreId +
                '}';
    }
}
