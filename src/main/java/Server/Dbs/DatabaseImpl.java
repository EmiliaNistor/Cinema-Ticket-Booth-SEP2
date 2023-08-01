package Server.Dbs;

import Client.Model.TicketModel;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.*;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class DatabaseImpl implements Database {
    Connection connection = null;

    public DatabaseImpl() throws SQLException {
        connection = DatabaseUtil.getConnection();
    }

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
    //buy ticket
    public Ticket makePurchase(Ticket ticket) {
        try {
            // provides query with placeholders for the values
            String query = "INSERT INTO ticket VALUES (?, ?, ?)";
            //creating a PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //setting 1st placeholder to seat id
            preparedStatement.setInt(1, getSeatId(ticket.getScreen().getScreenId(),ticket.getSeat().getRow(), ticket.getSeat().getNumber()));
            //setting 2nd placeholder to movie name
            preparedStatement.setString(2, ticket.getMovie().getName());
            //setting 3rd placeholder to menu
            preparedStatement.setString(3, String.valueOf(ticket.getMenu()));
            preparedStatement.executeUpdate(); // inserting the row into the database
            preparedStatement.close();
            connection.commit();
            System.out.println("Ticket " + ticket.getId() + " was created.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    //view ticket info
    public Ticket getTicket(int ticketId)
    {
        try {
            String query = "SELECT t.id, t.seat_id, t.movie_id, t.menu_id, s.row, s.number, m.name, m.start_time, m.end_time, m.date, m.length, m.genre " +
                    "FROM ticket t " +
                    "JOIN seat s ON t.seat_id = s.id " +
                    "JOIN movie m ON t.movie_id = m.id " +
                    "JOIN menu f ON t.menu_id = f.id" +
                    "WHERE t.id = ?";
            //creating PreparedStatement obj with query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //setting 1st placeholder to "ticketId"
            preparedStatement.setInt(1, ticketId);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
               int id = results.getInt("id");

                String seatRow = results.getString("row");
                int seatNumber = results.getInt("number");
                Seat seat = new Seat(seatRow, seatNumber);

                int movieId = results.getInt("movie_id");
                String name = results.getString("name");
                LocalDate date = results.getDate("date").toLocalDate();
                int length = results.getInt("length");
                String genre = results.getString("genre");

                Movie movie = new Movie(movieId,name,date, LocalTime.now(), LocalTime.now(), genre,length);

                ArrayList<Seat> seats = new ArrayList<>();
                int screenId = results.getInt("screenId");

                Screen screen = new Screen(seats, screenId);

                return new Ticket(id, seat, movie,screen, null);
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
                    return new Menu(menuId, food, price);
                } else {
                    System.err.println("Unable to recover generated keys after menu was added.");
                }
            } else {
                System.err.println("No rows were affected.");
            }
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

