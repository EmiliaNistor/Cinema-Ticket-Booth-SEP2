package Client.Model;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import Shared.Model.Movie;
import Shared.Network.IRMIServer;

public class MovieListModel implements IMovieListModel
{
    /**
     * List of saved movies within memory. Key is movie ID
     */
    private final HashMap<Integer, Movie> movieList;
    private final IRMIServer serverRMI;

    public MovieListModel(IRMIServer serverRMI)
    {
        this.movieList = new HashMap<>(); //FXCollections.observableArrayList();
        movieList.put(123, new Movie(123, ":(", LocalDate.now(), LocalTime.now(), LocalTime.now(), "sad", 1234));
        this.serverRMI = serverRMI;
    }

    /**
     * Get a list of all available movies
     * @return List of movies
     */
    @Override
    public ArrayList<Movie> getAllMovies()
    {
        try
        {
            ArrayList<Movie> moviesList = serverRMI.getAllMovies();
            System.out.printf("Movies in local storage pre-loop: %d\n",moviesList.size());
            for (Movie m: moviesList) {
                this.movieList.put(m.getMovieId(), m);
            }
            ArrayList<Movie> list = new ArrayList<>(this.movieList.values());
            System.out.printf("Movies in local storage: %d\n",list.size());
            return list;
        } catch (RemoteException e)
        {
            System.out.println("Couldn't fetch movies from the server."  );
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Get all the movies that are equal to provided movie and occur on the same date
     * @param movie The movie to match with
     * @param date  The date to match with
     * @return Movies which are equal to provided movie and occur on the same date
     */
    @Override
    public ArrayList<Movie> getSameMoviesByDate(Movie movie, LocalDate date) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();
        for (Movie m: movieList.values()) {
            if (m.equalsWithoutTime(movie) && m.getDate().equals(date)
            ) {
                matchingMovies.add(m);
            }
        }

        return matchingMovies;
    }

    /**
     * Get all the movies that are equal to provided movie
     * @param movie The movie to match with
     * @return Movies that are equal
     */
    @Override
    public ArrayList<Movie> getSameMovies(Movie movie) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();
        for (Movie m: movieList.values()) {
            if (m.equals(movie)) {
                matchingMovies.add(m);
            }
        }

        return matchingMovies;
    }
}
