package Client.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Client.Core.PropertyChangeSubject;
import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Network.IRMIServer;

public class MovieListModel implements IMovieListModel, PropertyChangeSubject
{
    /**
     * List of saved movies within memory. Key is movie ID
     */
    private final HashMap<Integer, Movie> movieList;
    private final IRMIServer serverRMI;
    private final PropertyChangeSupport propertyChangeSupport;

    public MovieListModel(IRMIServer serverRMI)
    {
        this.movieList = new HashMap<>();
        this.serverRMI = serverRMI;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Refreshes the available list of movies
     */
    @Override
    public void refreshMovies()
    {
        try
        {
            System.out.println("Fetching movies from server");
            ArrayList<Movie> movies = serverRMI.getAllMovies();
            if (movies != null) {
                System.out.println("Successful fetch!");
                // successful fetch
                Collection<Movie> oldMovieList = movieList.values();

                // populating movie list
                System.out.println("Clearing movie list and repopulating it!");
                movieList.clear();
                for (Movie m: movies) {
                    movieList.put(m.getMovieId(), m);
                }
                System.out.println("Movies in list: "+movieList.values().size());

                System.out.println("Movies refreshed! Notifying listeners!");
                propertyChangeSupport.firePropertyChange(
                        "MovieListChange", oldMovieList, movieList.values()
                );
            }
        } catch (RemoteException e)
        {
            System.out.println("Couldn't fetch movies from the server.");
            e.printStackTrace();
        }
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

    // Property Change Subject implementations
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        System.out.println("New listener, listening to: "+name);
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
