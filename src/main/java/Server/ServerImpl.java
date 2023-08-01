package Server;

//import Dao.MovieDao;
//import Dao.SeatDao;
//import Dao.UserDao;

import Shared.Model.*;
import Server.Dbs.DatabaseUtil;
import Shared.Network.IRMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerImpl extends UnicastRemoteObject implements IRMIServer
{
    private ArrayList<Ticket> tickets;
    private ArrayList<Movie> movies;
    private ArrayList<Menu> menuItems;
    private HashMap<Integer, Screen> screens; // Store screens with their IDs as keys

    public ServerImpl() throws RemoteException
    {
        tickets = new ArrayList<>();
        movies = new ArrayList<>();
        menuItems = new ArrayList<>();
        screens = new HashMap<>();
    }

    // Other methods in the ServerImpl class...


    public Screen getScreenById(int screenId) throws RemoteException
    {
        // Check if the screen is already cached in the "screens" map
        if (screens.containsKey(screenId))
        {
            return screens.get(screenId);
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement screenStatement = connection.prepareStatement("SELECT * FROM sep2reexam_database.screen WHERE id = ?");
             PreparedStatement seatStatement = connection.prepareStatement("SELECT * FROM sep2reexam_database.seat WHERE screen_id = ?"))
        {

            // Get the screen details
            screenStatement.setInt(1, screenId);
            ResultSet screenResult = screenStatement.executeQuery();

            // If the screen is found, create the Screen object
            if (screenResult.next())
            {
                int id = screenResult.getInt("id");
                // Other fields to retrieve from the database, such as name, etc.

                // Get the associated seats for the screen
                seatStatement.setInt(1, id);
                ResultSet seatResult = seatStatement.executeQuery();
                ArrayList<Seat> seats = new ArrayList<>();

                while (seatResult.next())
                {
                    int seatId = seatResult.getInt("id");
                    int row = seatResult.getInt("row");
                    int number = seatResult.getInt("number");
                    // Other fields to retrieve from the database, such as seat number, etc.

                    // Create the Seat object and add it to the seats list
                    Seat seat = new Seat(row,number);
                    seats.add(seat);
                }

                // Create the Screen object with the retrieved data
                Screen screen = new Screen(id, seats);

                // Cache the screen in the "screens" map for future use
                screens.put(screenId, screen);

                return screen;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null; // Return null if the screen is not found in the database
    }
    @Override
    public boolean signup(String username, String password) throws RemoteException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO sep2reexam_database.users (username, password) VALUES (?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {

                System.out.println("Signup successful for user: " + username);
                return true;
            } else {

                System.out.println("Signup failed for user: " + username);

            }
        } catch (SQLException e) {
            System.out.println("Error occurred during signup process for user: " + username);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Ticket> getAllTickets() throws RemoteException {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sep2reexam_database.ticket")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int seatId = resultSet.getInt("seat_id");
                int movieId = resultSet.getInt("movie_id");


                // Retrieve the Seat, Movie, and Menu objects based on their IDs (you need to implement these methods)
                Seat seat = getSeatById(seatId);
                Movie movie = getMovieById(movieId);
                Screen screen = getScreenById(1);

                System.out.println("Retrieved data for Ticket with ID: " + id);
                System.out.println("Seat: " + seat);
                System.out.println("Movie: " + movie);
                System.out.println("Screen: " + screen);

                Ticket ticket = new Ticket(id, seat, movie, screen, menu);
                tickets.add(ticket);
                System.out.println(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }


//    private Screen getScreenById(int screenId) {
//        // Fetch the Screen object from the database based on the screenId
//        // Assuming you have a 'screens' table in the database with columns 'id', 'name', 'capacity', etc.
//        String query = "SELECT * FROM sep2reexam.screen WHERE id = ?";
//        try (Connection connection = DatabaseUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, screenId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    int capacity = resultSet.getInt("capacity");
//                    // Other relevant data for Screen
//
//                    // Create and return the Screen object
//                    return new Screen();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // Return null if screen with the given ID is not found
//    }

    private Seat getSeatById(int seatId)
    {
        // Fetch the Seat object from the database based on the seatId
        // Assuming you have a 'seats' table in the database with columns 'id', 'seat_number', etc.
        String query = "SELECT * FROM sep2reexam_database.seat WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, seatId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    int seatNumber = resultSet.getInt("number");
                    int rowNumber = resultSet.getInt("row");

                    // Other relevant data for Seat

                    // Create and return the Seat object
                    return new Seat(rowNumber, seatNumber);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; // Return null if seat with the given ID is not found
    }

    private Movie getMovieById(int movieId)
    {
        // Fetch the Movie object from the database based on the movieId
        // Assuming you have a 'movies' table in the database with columns 'id', 'name', 'genre', etc.
        String query = "SELECT * FROM sep2reexam_database.movie WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, movieId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String genre = resultSet.getString("genre");
                    int length = resultSet.getInt("length");
                    Date date = resultSet.getDate("date");

                    Movie movie = new Movie(id, name, genre, length, date);
                    System.out.println(movie);
                    return movie;
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; // Return null if movie with the given ID is not found
    }

    private Menu getMenuById(int menuId)
    {
        // Fetch the Menu object from the database based on the menuId
        // Assuming you have a 'menus' table in the database with columns 'id', 'name', 'price', etc.
        String query = "SELECT * FROM sep2reexam.menu WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, menuId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String food = resultSet.getString("food");
                    double price = resultSet.getDouble("price");
                    // Other relevant data for Menu

                    // Create and return the Menu object
                    return new Menu(id, food, price);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; // Return null if menu with the given ID is not found
    }


    @Override
    public ArrayList<Movie> getAllMovies() throws RemoteException
    {
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sep2reexam_database.movie"))
        {

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                int length = resultSet.getInt("length");
                Date date = resultSet.getDate("date");

                Movie movie = new Movie(id, name, genre, length, date);
                System.out.println(movie);
                movies.add(movie);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return movies;
    }


    @Override
    public ArrayList<Menu> getAllMenuItems() throws RemoteException
    {
        return menuItems;
    }

    @Override
    public void addTicket(Ticket ticket) throws RemoteException
    {
        tickets.add(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) throws RemoteException
    {

    }

    @Override
    public void deleteTicket(Ticket ticket) throws RemoteException
    {
        tickets.remove(ticket);
    }

    @Override
    public void addMovie(Movie movie) throws RemoteException
    {
        movies.add(movie);
    }

    @Override
    public void updateMovie(Movie movie) throws RemoteException
    {

    }

    @Override
    public void deleteMovie(Movie movie) throws RemoteException
    {
        movies.remove(movie);
    }

    @Override
    public void addMenuItem(Menu menu) throws RemoteException
    {
        menuItems.add(menu);
    }

    @Override
    public void updateMenuItem(Menu menu) throws RemoteException
    {

    }

    @Override
    public void deleteMenuItem(Menu menu) throws RemoteException
    {
        menuItems.remove(menu);
    }
}
