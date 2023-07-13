package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Models.Menu;
import Models.Movie;
import Models.Ticket;


public interface CinemaRemote extends Remote {
    ArrayList<Ticket> getAllTickets() throws RemoteException;

    ArrayList<Movie> getAllMovies() throws RemoteException;

    ArrayList<Menu> getAllMenuItems() throws RemoteException;




    void addTicket(Ticket ticket) throws RemoteException;

    void updateTicket(Ticket ticket) throws RemoteException;

    void deleteTicket(Ticket ticket) throws RemoteException;




    void addMovie(Movie movie) throws RemoteException;

    void updateMovie(Movie movie) throws RemoteException;

    void deleteMovie(Movie movie) throws RemoteException;




    void addMenuItem(Menu menu) throws RemoteException;

    void updateMenuItem(Menu menu) throws RemoteException;

    void deleteMenuItem(Menu menu) throws RemoteException;
}
