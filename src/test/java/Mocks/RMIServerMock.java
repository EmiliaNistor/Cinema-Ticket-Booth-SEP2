package Mocks;

import Shared.Model.*;
import Shared.Network.IRMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Mock test class used to simulate RMIServer
 */
public class RMIServerMock implements IRMIServer {

    @Override
    public Ticket getTicketById(int ticketId) throws RemoteException {
        if (ticketId < 1) {
            return null;
        }
        return new Ticket(ticketId, new Seat("B", 2), 2, 1);
    }

    @Override
    public ArrayList<Movie> getAllMovies() throws RemoteException {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i=0; i<5; i++) {
            movies.add(
                    new Movie(i, "Movie"+i,
                            LocalDate.of(2023, 12, 1), LocalTime.of(i,i,i), LocalTime.of(i,i,i),
                            "Genre", 120, 1
                    )
            );
        }

        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) throws RemoteException {
        if (movieId < 1) {
            return null;
        }

        return new Movie(movieId, "Movie"+movieId,
                LocalDate.of(2023, 12, 1), LocalTime.of(0,0,0), LocalTime.of(0,0,0),
                "Genre", 120, 1
        );
    }

    @Override
    public ArrayList<Screen> getAllScreens() throws RemoteException {
        ArrayList<Screen> screens = new ArrayList<>();
        for (int i=0; i<5; i++) {
            ArrayList<Seat> seats = new ArrayList<>();
            seats.add(new Seat("A", 1));
            seats.add(new Seat("B", 2));
            screens.add(
                    new Screen(i,"Screen"+i,
                            seats)
            );
        }

        return screens;
    }

    @Override
    public Screen getScreenById(int screenId) throws RemoteException {
        if (screenId < 1) {
            return null;
        }

        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A", 1));
        seats.add(new Seat("B", 2));
        return new Screen(screenId,"Screen"+screenId,
                seats);
    }

    @Override
    public User signUp(String username, String password) throws RemoteException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return new User(1, username, password, false);
    }

    @Override
    public User logIn(String username, String password) throws RemoteException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return new User(1, username, password, false);
    }

    @Override
    public ArrayList<Menu> getAllMenuItems() throws RemoteException {
        ArrayList<Menu> menus = new ArrayList<>();
        for (int i=0; i<5; i++) {
            menus.add(
                    new Menu(i, "Food"+i, i)
            );
        }

        return menus;
    }

    @Override
    public Ticket purchase(Ticket ticket) throws RemoteException {
        if (ticket.getSeat() == null || ticket.getMovieId() < 1) {
            return null;
        }

        return new Ticket(1, ticket.getSeat(), ticket.getMovieId(), ticket.getMenuId());
    }

    @Override
    public void cancelTicket(int ticketId) throws RemoteException {}

    @Override
    public void addMovie(Movie movie) throws RemoteException {}

    @Override
    public void updateMovie(Movie movie) throws RemoteException {}

    @Override
    public void addMenuItem(Menu menu) throws RemoteException {}

    @Override
    public void updateMenuItem(Menu menu) throws RemoteException {}

    @Override
    public void deleteMenuItem(Menu menu) throws RemoteException {}
}
