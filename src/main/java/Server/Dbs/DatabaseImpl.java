package Server.Dbs;

import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.*;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DatabaseImpl implements Database {
    Connection connection;

    public DatabaseImpl() throws SQLException {
        connection = DatabaseUtil.getConnection();
    }

    @Override
    public int getSeatId(int screen, String row, int number)
    {
        try {
            String query = "SELECT id" +
                    "FROM seat" +
                    "WHERE screen_id = ? AND row = ? AND number = ?" +
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
            String query = "INSERT INTO ticket VALUES (?, ?, ?)";
            //creating a PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //setting 1st placeholder to seat id
            preparedStatement.setInt(1,
                    getSeatId(ticket.getScreen().getScreenId(),
                            ticket.getSeat().getRow(),
                            ticket.getSeat().getNumber()
                    )
            );
            //setting 2nd placeholder to movie name
            preparedStatement.setString(2, ticket.getMovie().getName());
            //setting 3rd placeholder to menu
            preparedStatement.setString(3, String.valueOf(ticket.getMenu()));

            //executing SQL query and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet results = preparedStatement.getGeneratedKeys();
                if (results.next()) {
                    // fetching newly made ticket info
                    int newTicketId = results.getInt(1);
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
            //connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            return null;
        }
    }
    public Ticket getTicket(int ticketId)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT t.id, t.seat_id, t.movie_id, t.menu_id, " +
                            "s.row, s.number, " +
                            "m.name, m.start_time, m.end_time, m.date, m.length, m.genre, " +
                            "f.food, f.price " +
                            "FROM ticket t " +
                            "JOIN seat s ON t.seat_id = s.id " +
                            "JOIN movie m ON t.movie_id = m.id " +
                            "JOIN menu f ON t.menu_id = f.id" +
                            "WHERE t.id = ?"
            );
            preparedStatement.setInt(1, ticketId);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int seatId = resultSet.getInt("seat_id");
                String seatRow = resultSet.getString("row");
                int seatNumber = resultSet.getInt("number");
                Seat seat = new Seat(seatRow, seatNumber);

                int movieId = resultSet.getInt("movie_id");
                String name = resultSet.getString("name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                int length = resultSet.getInt("length");
                String genre = resultSet.getString("genre");

                Movie movie = new Movie(movieId, name, date, startTime, endTime, genre, length);

                // ???
                /*String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(username, password);*/

                int menuId = resultSet.getInt("menu_id");
                String food = resultSet.getString("food");
                double price = resultSet.getDouble("price");
                Menu menu = new Menu(menuId,food, price);


                ArrayList<Seat> seats = new ArrayList<>();

                Screen screen = new Screen(1,seats);

                return new Ticket(ticketId, seat, movie,screen, menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //cancel ticket
    public void cancelTicket(int ticketId) {
        try {
            String query = "DELETE FROM ticket WHERE id = ?";
            //creating PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //assigning value of the ticketId in the prepared statement
            preparedStatement.setInt(1, ticketId);
            //executing SQL query  and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            //committing changes to db
            connection.commit();

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
            String query = "INSERT INTO menu (food, price) VALUES (?, ?)";
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
            String query = "SELECT id, food, price FROM menu";
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
        }

        return menus;
    }
}

