package Client.Model;

import javafx.beans.property.SimpleStringProperty;

public class MovieList {
    private final String Movie;
    private final String Genre;
    private final int Length;
    private final int Screen;

    public MovieList(String Movie, String Genre, int Length, int Screen) {
        this.Movie = Movie;
        this.Genre = Genre;
        this.Length = Length;
        this.Screen = Screen;
    }

    public String getMovie() {
        return Movie;
    }

    public String getGenre() {
        return Genre;
    }

    public int getLength() {
        return Length;
    }

    public int getScreen() {
        return Screen;
    }
}
