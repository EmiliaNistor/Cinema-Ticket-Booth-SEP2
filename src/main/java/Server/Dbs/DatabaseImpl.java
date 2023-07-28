package Server.Dbs;

import Client.Model.TicketModel;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.*;


import java.sql.*;
import java.util.ArrayList;

public class DatabaseImpl implements Database {
    Connection connection = null;

    public DatabaseImpl() throws SQLException {
        connection = DatabaseUtil.getConnection();
    }

    //buy ticket
    public void makePurchase(Ticket ticket, Seat seat, Movie movie) {
        try {
            // provides query with placeholders for the values.
            String query = "INSERT INTO ticket VALUES (?, ?, ?)";
            PreparedStatement s = connection.prepareStatement(query);

            String seatId = seat.getRow() + "-" + seat.getNumber();
            s.setString(1, seatId);
            s.setString(2, movie.getName());
            s.setString(3, String.valueOf(TicketModel.getMenu()));
            s.executeUpdate(); // inserting the row into the database
            s.close();
            connection.commit();
            System.out.println("Ticket with ID " + ticket.getId() + " has been created.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //view ticket info
    public Ticket getTicket(int ticketId)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT t.id, t.seat_id, t.movie_id, t.menu_id, s.row, s.number, m.name, m.start_time, m.end_time, m.date, m.length, m.genre " +
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
               int id = resultSet.getInt("id");

                int seatRow = resultSet.getInt("row");
                int seatNumber = resultSet.getInt("number");
                Seat seat = new Seat(seatRow, seatNumber);

                int movieId = resultSet.getInt("movie_id");
                String name = resultSet.getString("name");
                Date date = resultSet.getDate("date");
                int length = resultSet.getInt("length");
                String genre = resultSet.getString("genre");

                Movie movie = new Movie(movieId,name,date,genre,length);

                ArrayList<Seat> seats = new ArrayList<>();

                Screen screen = new Screen(seats);

                return new Ticket(id, seat, movie,screen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //cancel ticket
    public void cancelTicket(int ticketId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ticket WHERE id = ?");
            // assigning value of the ticketId in the prepared statement
            preparedStatement.setInt(1, ticketId);
            //executing SQL query  and storing number of rows affected by the query in  rowsAffected
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            // committing changes to db
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

}

