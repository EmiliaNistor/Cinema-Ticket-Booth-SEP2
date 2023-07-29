package Shared.Model;



import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;


public class Movie implements Serializable
{
    private final int movieId;
    private String name;
    private LocalDate date;
    private int length;
    private String genre;


    public Movie(int movieId, String name, LocalDate date, String genre, int length) {
        this.movieId = movieId;
        this.date = date;
        this.name = name;
        this.length = length;
        this.genre = genre;
    }

    public int getMovieId() {return movieId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString()
    {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", genre='" + genre + '\'' +
                '}';
    }
}
