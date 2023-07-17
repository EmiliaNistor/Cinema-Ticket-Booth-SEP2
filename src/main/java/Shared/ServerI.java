package Shared;

import Shared.Menu;
import Shared.Movie;
import Shared.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ServerI extends Remote {
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
