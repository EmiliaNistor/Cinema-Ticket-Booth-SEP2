package Client.Model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Shared.Model.Movie;
import Shared.Network.IRMIServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieListModel implements IMovieListModel
{
    private ObservableList<Movie> movieList;
    private final IRMIServer serverRMI;

    public MovieListModel(IRMIServer serverRMI)
    {
        this.movieList = FXCollections.observableArrayList();
        this.serverRMI = serverRMI;
    }

    /**
     * Get a list of all available movies
     *
     * @return List of movies
     */
    @Override
    public ObservableList<Movie> getAllMovies()
    {
        try
        {
            List<Movie> moviesList = serverRMI.getAllMovies();
            ObservableList<Movie> movies = FXCollections.observableArrayList(moviesList);
            return movies;
        } catch (RemoteException e)
        {
            System.out.println("Couldn't fetch movies from the server."  );
            e.printStackTrace();

        }
        return null;
    }
}
