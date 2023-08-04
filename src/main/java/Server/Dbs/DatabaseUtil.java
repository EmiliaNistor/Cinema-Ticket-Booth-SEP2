package Server.Dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/sep2reexam_database";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static Connection connection;

    public static void main(String[] args) {
        String dropSchemaQuery = "DROP SCHEMA IF EXISTS sep2reexam_database CASCADE";
        String createSchemaQuery = "CREATE SCHEMA sep2reexam_database";

        String[] tableCreationQueries = {
                "CREATE TABLE sep2reexam_database.menu (id SERIAL PRIMARY KEY, food VARCHAR(255), price DECIMAL(10,2));",

                "CREATE TABLE sep2reexam_database.screen (id SERIAL PRIMARY KEY, name VARCHAR(255));",

                "CREATE TABLE sep2reexam_database.movie (id SERIAL PRIMARY KEY, name VARCHAR(255), " +
                        "start_time TIME, end_time TIME, date DATE, length INTEGER, genre VARCHAR(255), "+
                        "screen_id INTEGER REFERENCES sep2reexam_database.screen(id));",

                "CREATE TABLE sep2reexam_database.seat (id SERIAL PRIMARY KEY, row VARCHAR(10), " +
                        "number INTEGER, screen_id INTEGER REFERENCES sep2reexam_database.screen(id));",

                "CREATE TABLE sep2reexam_database.ticket (id SERIAL PRIMARY KEY, seat_id INTEGER " +
                        "REFERENCES sep2reexam_database.seat(id), movie_id INTEGER REFERENCES sep2reexam_database.movie(id), " +
                        "menu_id INTEGER REFERENCES sep2reexam_database.menu(id));",

                "CREATE TABLE sep2reexam_database.users (" +
                        "id SERIAL PRIMARY KEY, username VARCHAR(255) UNIQUE, password VARCHAR(255), administrator BOOLEAN DEFAULT false);",

                // Add default template stuff
        };

        try {
            connection = getConnection();

            executeQuery(dropSchemaQuery);
            executeQuery(createSchemaQuery);

            // creating tables
            for (String query : tableCreationQueries) {
                executeQuery(query);
            }

            System.out.println("Successfully created the tables!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection;
    }

    public static void executeQuery(String query) throws SQLException {
        connection.createStatement().executeUpdate(query);
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("The connection is successfully closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
