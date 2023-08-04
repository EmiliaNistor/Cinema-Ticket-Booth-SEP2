package Server;

import Server.Dbs.Database;
import Shared.Model.*;
import Server.Dbs.DatabaseUtil;
import Shared.Network.IRMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements IRMIServer
{
    private final Database database;

    public ServerImpl(Database database) throws RemoteException
    {
        this.database = database;
    }


    public Screen getScreenById(int screenId) throws RemoteException
    {
        return database.getScreenById(screenId);
    }
    @Override
    public User signUp(String username, String password) throws RemoteException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return database.createAccount(username, password);
    }

    /**
     * Allows to log in to a user account to access more features
     * @param username The username of the account
     * @param password The password of the account
     * @return User account's information, null if failed
     * @throws RemoteException
     */
    @Override
    public User logIn(String username, String password) throws RemoteException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return database.checkUserCredentials(username, password);
    }

    @Override
    public Ticket getTicketById(int ticketId) throws RemoteException {
        return database.getTicket(ticketId);
    }

    @Override
    public Movie getMovieById(int movieId)
    {
        return database.getMovieById(movieId);
    }


    @Override
    public ArrayList<Movie> getAllMovies() throws RemoteException
    {
        return database.getAllMovies();
    }

    @Override
    public ArrayList<Screen> getAllScreens() throws RemoteException {
        return database.getAllScreens();
    }


    @Override
    public ArrayList<Menu> getAllMenuItems() throws RemoteException
    {
        return database.getMenus();
    }

    @Override
    public Ticket purchase(Ticket ticket) throws RemoteException
    {
        return database.makePurchase(ticket);
    }

    @Override
    public void cancelTicket(int ticketId) throws RemoteException
    {
        database.cancelTicket(ticketId);
    }

    @Override
    public void addMovie(Movie movie) throws RemoteException
    {
        //movies.add(movie);
    }

    @Override
    public void updateMovie(Movie movie) throws RemoteException
    {

    }

    @Override
    public void addMenuItem(Menu menu) throws RemoteException
    {
        database.addMenu(menu.getFood(), menu.getPrice());
    }

    @Override
    public void updateMenuItem(Menu menu) throws RemoteException
    {

    }

    @Override
    public void deleteMenuItem(Menu menu) throws RemoteException
    {
        //menuItems.remove(menu);
    }
}
