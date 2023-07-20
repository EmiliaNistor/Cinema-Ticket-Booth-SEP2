package Model;

import javafx.scene.control.Button;

public class Movie {
    private final int movieId;
    private String name;
    private int length;
    private String genre;
    private String screen;
    private Button button;
    public Movie(int movieId, String name, String genre, int length,String screen) {
        this.movieId = movieId;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.screen = screen;
        this.button = new Button("Buy Ticket");
    }

    public int getMovieId() {return movieId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
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

    public Button getButton() {
        return button;
    }
    public void setButton(Button button) {
        this.button = button;
    }

}
