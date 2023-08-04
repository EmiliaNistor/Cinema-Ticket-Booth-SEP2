package Server.Dbs;

import Shared.Model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DatabaseImpl implements Database {
    private Connection connection;

    public DatabaseImpl() throws SQLException {
        connection = DatabaseUtil.getConnection();
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sep2reexam_database.movie");

            ArrayList<Movie> movies = new ArrayList<>();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                String genre = resultSet.getString("genre");
                int length = resultSet.getInt("length");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                int screenId = resultSet.getInt("screen_id");

                Movie movie = new Movie(id, name, date, startTime, endTime, genre, length, screenId);
                movies.add(movie);
            }

            statement.close();
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM sep2reexam_database.movie WHERE id = ?");
            preparedStatement.setInt(1, movieId);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                String genre = resultSet.getString("genre");
                int length = resultSet.getInt("length");
                int screenId = resultSet.getInt("screen_id");

                preparedStatement.close();
                return new Movie(id, name, date, startTime, endTime, genre, length, screenId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Screen> getAllScreens() {
        try {
            Statement screenStatement = connection.createStatement();
            Statement seatStatement = connection.createStatement();
            ResultSet screenResultSet = screenStatement.executeQuery("SELECT * FROM sep2reexam_database.screen");

            ArrayList<Screen> screens = new ArrayList<>();
            while (screenResultSet.next())
            {
                int id = screenResultSet.getInt("id");
                String name = screenResultSet.getString("name");

                ResultSet seatResultSet = seatStatement.executeQuery("SELECT * FROM sep2reexam_database.seat  WHERE screen_id = ?");
                ArrayList<Seat> seats = new ArrayList<>();
                while (seatResultSet.next()) {
                    String row = screenResultSet.getString("row");
                    int number = screenResultSet.getInt("number");
                    seats.add(new Seat(row, number));
                }

                screens.add(
                        new Screen(id, name, seats)
                );
            }

            screenStatement.close();
            seatStatement.close();

            return screens;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Screen getScreenById(int screenId) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement screenStatement = connection.prepareStatement("SELECT * FROM sep2reexam_database.screen WHERE id = ? LIMIT 1");
             PreparedStatement seatStatement = connection.prepareStatement("SELECT * FROM sep2reexam_database.seat WHERE screen_id = ?"))
        {
            // Get the screen details
            screenStatement.setInt(1, screenId);
            ResultSet screenResult = screenStatement.executeQuery();

            // If the screen is found, create the Screen object
            if (screenResult.next())
            {
                int id = screenResult.getInt("id");
                String name = screenResult.getString("name");
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
                Screen screen = new Screen(id, name, seats);

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
    public User createAccount(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO sep2reexam_database.users (username, password) VALUES (?, ?)"
            );
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Signup successful for user: " + username);

                statement.close();
                return checkUserCredentials(username,password);
            } else {
                System.out.println("Signup failed for user: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during signup process for user: " + username);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User checkUserCredentials(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM sep2reexam_database.users u " +
                            "WHERE u.username = ? AND u.password = ? LIMIT 1"
            );
            statement.setString(1, username);
            statement.setString(2, password);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                boolean administrator = resultSet.getBoolean("administrator");
                statement.close();

                return new User(id, userUsername, userPassword, administrator);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during log in process for user: " + username);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getSeatId(int screen, String row, int number)
    {
        try {
            String query = "SELECT s.id " +
                    "FROM sep2reexam_database.seat s " +
                    "WHERE screen_id = ? AND row = ? AND number = ? " +
                    "LIMIT 1";
            //creating a PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //setting 1st placeholder to "screen"
            preparedStatement.setInt(1, screen);
            //setting 2nd placeholder to "row"
            preparedStatement.setString(2, row);
            //setting 3rd placeholder to "number"
            preparedStatement.setInt(3, number);
            ResultSet results = preparedStatement.executeQuery();
            if(results.next()) {
                return results.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Ticket makePurchase(Ticket ticket) {
        try {
            // provides query with placeholders for the values.
            String query = "INSERT INTO sep2reexam_database.ticket (seat_id, movie_id, menu_id) VALUES (?, ?, ?);";
            //creating a PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[] {"id"});

            Movie movie = getMovieById(ticket.getMovieId());

            //setting 1st placeholder to seat id
            preparedStatement.setInt(1,
                    getSeatId(movie.getScreenId(),
                            ticket.getSeat().getRow(),
                            ticket.getSeat().getNumber()
                    )
            );
            //setting 2nd placeholder to movie name
            preparedStatement.setInt(2, ticket.getMovieId());
            //setting 3rd placeholder to menu
            if (ticket.getMenuId() < 1) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, ticket.getMenuId());
            }


            //executing SQL query and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet results = preparedStatement.getGeneratedKeys();
                if (results.next()) {
                    // fetching newly made ticket info
                    int newTicketId = results.getInt("id");
                    preparedStatement.close();
                    return getTicket(newTicketId);
                } else {
                    System.err.println("Unable to recover generated keys after ticket was created.");
                }
            } else {
                System.err.println("No rows were affected.");
            }

            preparedStatement.close();
            return null; // no ticket found after purchase?
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    public Ticket getTicket(int ticketId)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT t.id, t.seat_id, t.movie_id, t.menu_id, " +
                            "s.row, s.number " +
                            "FROM sep2reexam_database.ticket t " +
                            "JOIN sep2reexam_database.seat s ON t.seat_id = s.id " +
                            "WHERE t.id = ?");
            preparedStatement.setInt(1, ticketId);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String seatRow = resultSet.getString("row");
                int seatNumber = resultSet.getInt("number");
                Seat seat = new Seat(seatRow, seatNumber);

                int movieId = resultSet.getInt("movie_id");
                int menuId = resultSet.getInt("menu_id");

                preparedStatement.close();
                return new Ticket(ticketId, seat, movieId, menuId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //cancel ticket
    public void cancelTicket(int ticketId) {
        try {
            String query = "DELETE FROM sep2reexam_database.ticket WHERE id = ?";
            //creating PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //assigning value of the ticketId in the prepared statement
            preparedStatement.setInt(1, ticketId);
            //executing SQL query  and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (rowsAffected > 0) {
                System.out.println("Ticket " + ticketId + " was cancelled successfully.");
            } else {
                System.out.println("Ticket " + ticketId + " not found.");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //add menu
    public Menu addMenu(String food, double price) {
        try {
            //provides query with placeholders for the values
            String query = "INSERT INTO sep2reexam_database.menu (food, price) VALUES (?, ?)";
            //creating PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //setting 1st placeholder to "food"
            preparedStatement.setString(1, food);
            //setting 2nd placeholder to "price"
            preparedStatement.setDouble(2, price);
            //executing SQL query  and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet results = preparedStatement.getGeneratedKeys();
                if (results.next()) {
                    int menuId = results.getInt(1);
                    preparedStatement.close();
                    return new Menu(menuId, food, price);
                } else {
                    System.err.println("Unable to recover generated keys after menu was added.");
                }
            } else {
                System.err.println("No rows were affected.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add menu: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Menu> getMenus() {
        //creating ArrayList to store Menu obj from db
        ArrayList<Menu> menus = new ArrayList<>();

        try {
            String query = "SELECT id, food, price FROM sep2reexam_database.menu";
            //creating PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //executing query and getting the results in a ResultSet
            ResultSet results = preparedStatement.executeQuery();

            //loop for processing each row of the results
            while (results.next()) {
                int menuId = results.getInt("id");
                String food = results.getString("food");
                double price = results.getDouble("price");

                Menu menu = new Menu(menuId, food, price);
                menus.add(menu);
            }

            results.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to get menu: " + e.getMessage());
            return null;
        }

        return menus;
    }
}

