package Shared.Model;



import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Movie implements Serializable
{
    private final int movieId;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int length;
    private String genre;


    public Movie(int movieId, String name, LocalDate date, LocalTime startTime, LocalTime endTime, String genre, int length) {
        this.movieId = movieId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
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

    /**
     * Compares is the object equal to the movie object, doesn't compare movie IDs
     * @param object The object to compare against
     * @return true if equal
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Movie provided)) {
            return false;
        }

        return provided.name.equals(name) &&
                provided.genre.equals(genre) &&
                provided.length == length &&
                provided.date.equals(date) &&
                provided.startTime.equals(startTime) &&
                provided.endTime.equals(endTime);
    }

    /**
     * Compares is the object equal to the movie object, doesn't compare movie IDs, date and times
     * @param object The object to compare against
     * @return true if equal
     */
    public boolean equalsWithoutTime(Object object) {
        if (!(object instanceof Movie provided)) {
            return false;
        }

        return provided.name.equals(name) &&
                provided.genre.equals(genre) &&
                provided.length == length;
    }
}
