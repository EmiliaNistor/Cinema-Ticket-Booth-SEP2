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
        // Check if the screen is already cached in the "screens" map
        /*if (screens.containsKey(screenId))
        {
            return screens.get(screenId);
        }*/

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
                    String row = seatResult.getString("row");
                    int number = seatResult.getInt("number");
                    // Other fields to retrieve from the database, such as seat number, etc.

                    // Create the Seat object and add it to the seats list
                    Seat seat = new Seat(row,number);
                    seats.add(seat);
                }

                // Create the Screen object with the retrieved data
                Screen screen = new Screen(id, seats);

                // Cache the screen in the "screens" map for future use
                //screens.put(screenId, screen);

                return screen;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null; // Return null if the screen is not found in the database
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
                    LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                    LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                    String genre = resultSet.getString("genre");
                    int length = resultSet.getInt("length");
                    LocalDate date = resultSet.getDate("date").toLocalDate();

                    Movie movie = new Movie(id, name, date, startTime, endTime, genre, length);
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


    @Override
    public ArrayList<Movie> getAllMovies() throws RemoteException
    {
        return database.getAllMovies();
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
