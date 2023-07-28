package Shared.Model;


import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

public class Movie implements Serializable
{
    private final int movieId;
    private String name;
    private int length;
    private String genre;
    private Date date;

    public Movie(int movieId, String name, String genre, int length, Date date){
        this.movieId = movieId;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.date = date;


    }

    public int getMovieId() {return movieId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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
