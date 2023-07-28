package Shared.Model;


import java.util.Date;

public class Movie {
    private final int movieId;
    private String name;
    private Date date;
    private int length;
    private String genre;


    public Movie(int movieId, String name, Date date, String genre, int length) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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


}
