package Model.server;

//import Dao.MovieDao;
//import Dao.SeatDao;
//import Dao.UserDao;
import Model.Menu;
import Model.Movie;
import Model.Ticket;
import Util.DatabaseUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements ServerI {
    private ArrayList<Ticket> tickets;
    private ArrayList<Movie> movies;
    private ArrayList<Menu> menuItems;

    public ServerImpl() throws RemoteException {
        tickets = new ArrayList<>();
        movies = new ArrayList<>();
        menuItems = new ArrayList<>();
    }

    @Override
    public ArrayList<Ticket> getAllTickets() throws RemoteException {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets")) {

            //SeatDao seatDao = new SeatDao();
            //MovieDao movieDao = new MovieDao();
            //UserDao userDao = new UserDao();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String seatId = resultSet.getString("seat_id");
                String movieId = resultSet.getString("movie_id");
                String userId = resultSet.getString("user_id");

                //Seat seat = seatDao.getSeatById(seatId);
                //Movie movie = movieDao.getMovieById(movieId);
                //User user = userDao.getUserById(userId);

                //Ticket ticket = new Ticket(id, seat, movie, user);
                //tickets.add(ticket);
            }
        } catch (SQLException e) {
            // Handle any potential SQLExceptions
            e.printStackTrace();
        }

        return tickets;
    }



    @Override
    public ArrayList<Movie> getAllMovies() throws RemoteException {

        ArrayList<Movie> movies = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM movies")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                //length
                int length = resultSet.getInt("length");
                //screen name
                String screen = resultSet.getString("screen");

                Movie movie = new Movie(id, name, length, genre);
                movies.add(movie);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return movies;
    }


    @Override
    public ArrayList<Menu> getAllMenuItems() throws RemoteException {
        return menuItems;
    }

    @Override
    public void addTicket(Ticket ticket) throws RemoteException {
        tickets.add(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) throws RemoteException {

    }

    @Override
    public void deleteTicket(Ticket ticket) throws RemoteException {
        tickets.remove(ticket);
    }

    @Override
    public void addMovie(Movie movie) throws RemoteException {
        movies.add(movie);
    }

    @Override
    public void updateMovie(Movie movie) throws RemoteException {

    }

    @Override
    public void deleteMovie(Movie movie) throws RemoteException {
        movies.remove(movie);
    }

    @Override
    public void addMenuItem(Menu menu) throws RemoteException {
        menuItems.add(menu);
    }

    @Override
    public void updateMenuItem(Menu menu) throws RemoteException {

    }

    @Override
    public void deleteMenuItem(Menu menu) throws RemoteException {
        menuItems.remove(menu);
    }
}
