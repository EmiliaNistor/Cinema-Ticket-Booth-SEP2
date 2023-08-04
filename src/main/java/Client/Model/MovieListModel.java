package Client.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Client.Core.PropertyChangeSubject;
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

    @Override
    public void refreshMovies()
    {
        try
        {
            ArrayList<Movie> movies = serverRMI.getAllMovies();
            if (movies != null) {
                // successful fetch
                Collection<Movie> oldMovieList = movieList.values();

                // populating movie list
                movieList.clear();
                ArrayList<Movie> movieAR = new ArrayList<>();
                for (Movie m: movies) {
                    movieList.put(m.getMovieId(), m);
                    movieAR.add(m);
                }

                propertyChangeSupport.firePropertyChange(
                        "MovieListChange", oldMovieList, movieAR
                );
            }
        } catch (RemoteException e)
        {
            System.out.println("Couldn't fetch movies from the server.");
            e.printStackTrace();
        }
    }

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

    @Override
    public Movie getMovieById(int movieId) {
        if (movieList.containsKey(movieId)) {
            return movieList.get(movieId);
        }

        // Attempting to get movie from server
        try {
            Movie receivedMovie = serverRMI.getMovieById(movieId);
            if (receivedMovie != null) {
                // populating movies list
                ArrayList<Movie> movieAR = new ArrayList<>(movieList.values());
                movieAR.add(receivedMovie);

                propertyChangeSupport.firePropertyChange(
                        "MovieListChange", new ArrayList<>(movieList.values()), movieAR
                );
                movieList.put(receivedMovie.getScreenId(), receivedMovie);
                return receivedMovie;
            }
        } catch (RemoteException e) {
            System.out.println("Error occurred during movie fetching process.");
            e.printStackTrace();
        }

        // movie not found or an error
        return null;
    }

    // Property Change Subject implementations
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
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
