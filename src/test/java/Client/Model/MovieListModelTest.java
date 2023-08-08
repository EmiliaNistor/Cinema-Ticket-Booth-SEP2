package Client.Model;

import Mocks.RMIServerMock;
import Shared.Model.Movie;
import Shared.Network.IRMIServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieListModelTest {

    private IRMIServer rmiServer;
    private IMovieListModel movieListModel;

    @BeforeEach
    void setUp() {
        rmiServer = new RMIServerMock();
        movieListModel = new MovieListModel(rmiServer);
    }

    @AfterEach
    void tearDown() {
        rmiServer = null;
        movieListModel = null;
    }

    @Test
    void getSameMoviesByDateSuccess() {
        // refreshing movies from server
        movieListModel.refreshMovies();
        Movie movie = new Movie(1, "Movie1",
                LocalDate.of(2023, 12, 1), LocalTime.now(), LocalTime.now(),
                "Genre", 120, 1);

        assertTrue(movieListModel.getSameMoviesByDate(movie, LocalDate.of(2023, 12, 1)).size() > 0);
    }

    @Test
    void getSameMoviesByDateFail() {
        // refreshing movies from server
        Movie movie = new Movie(1, "Movie1",
                LocalDate.of(2023, 12, 1), LocalTime.now(), LocalTime.now(),
                "Genre", 120, 1);

        assertFalse(movieListModel.getSameMoviesByDate(movie, LocalDate.of(2023, 12, 1)).size() > 0);
    }

    @Test
    void getSameMoviesByDateWrongDate() {
        // refreshing movies from server
        movieListModel.refreshMovies();
        Movie movie = new Movie(1, "Movie1",
                LocalDate.of(2000, 12, 1), LocalTime.now(), LocalTime.now(),
                "Genre", 120, 1);

        assertFalse(movieListModel.getSameMoviesByDate(movie, LocalDate.of(2000, 12, 1)).size() > 0);
    }

    @Test
    void getSameMoviesSuccess() {
        // refreshing movies from server
        movieListModel.refreshMovies();
        Movie movie = new Movie(1, "Movie1",
                LocalDate.of(2023, 12, 1), LocalTime.of(1,1,1), LocalTime.of(1,1,1),
                "Genre", 120, 1);

        assertTrue(
                movieListModel.getSameMovies(
                        movie).size() > 0);
    }

    @Test
    void getSameMoviesFail() {
        // refreshing movies from server
        movieListModel.refreshMovies();
        Movie movie = new Movie(1, "Movie123",
                LocalDate.of(2023, 12, 1), LocalTime.of(1,1,1), LocalTime.of(1,1,1),
                "Genre", 120, 1);

        assertFalse(movieListModel.getSameMovies(movie).size() > 0);
    }

    @Test
    void getMovieByIdFail() {
        assertNull(movieListModel.getMovieById(-1));
    }

    @Test
    void getMovieByIdSuccess() {
        assertEquals(1, movieListModel.getMovieById(1).getMovieId());
    }
}