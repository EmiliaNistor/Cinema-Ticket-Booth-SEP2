package Client.Model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import Shared.Model.Movie;
import Shared.Network.IRMIServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        this.serverRMI = serverRMI;
    }

    /**
     * Get a list of all available movies
     *
     * @return List of movies
     */
    @Override
    public ArrayList<Movie> getAllMovies()
    {
        try
        {
            ArrayList<Movie> moviesList = serverRMI.getAllMovies();
            for (Movie m: moviesList) {
                this.movieList.put(m.getMovieId(), m);
            }
            //ObservableList<Movie> movies = FXCollections.observableArrayList(moviesList);
            return new ArrayList<>(this.movieList.values());
        } catch (RemoteException e)
        {
            System.out.println("Couldn't fetch movies from the server."  );
            e.printStackTrace();
        }
        return null;
    }
}
