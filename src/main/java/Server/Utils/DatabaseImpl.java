package Server.Utils;

import Server.Utils.Database;
import Shared.*;

import java.sql.*;

public class DatabaseImpl implements Database {
    Connection connection = null;

    public DatabaseImpl() throws SQLException {
        connection = DatabaseUtil.getConnection();
    }

    public void makePurchase(Ticket ticket, Seat seat, Movie movie) {
        try {
            // provides query with placeholders for the values.
            String query = "INSERT INTO ticket VALUES (?, ?, ?)";
            PreparedStatement s = connection.prepareStatement(query);

            String seatId = seat.getRow() + "-" + seat.getNumber();
            s.setString(1, seatId);
            s.setString(2, movie.getName());
            s.setString(3, String.valueOf(ticket.getMenu()));
            s.executeUpdate(); // inserting the row into the database
            s.close();
            connection.commit();
            System.out.println("Ticket with ID " + ticket.getId() + " has been created.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public Ticket getTicket(int ticketId)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ticket WHERE t.id = ?"
            );
            preparedStatement.setInt(1, ticketId);

            // executing the query returns a ResultSet which has the result of the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");

                int seatId = resultSet.getInt("seat_id");
                int seatRow = resultSet.getInt("row");
                int seatNumber = resultSet.getInt("number");
                Seat seat = new Seat(seatRow, seatNumber);

                int movieId = resultSet.getInt("movie_id");
                String name = resultSet.getString("name");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date date = resultSet.getDate("date");
                int length = resultSet.getInt("length");
                String genre = resultSet.getString("genre");
                String screen = resultSet.getString("screen");
                Movie movie = new Movie(name, genre, length, screen);

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(username, password);

                String food = resultSet.getString("food");
                double price = resultSet.getDouble("price");
                Menu menu = new Menu(food, price);

                return new Ticket(id, seat, movie, user, menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

