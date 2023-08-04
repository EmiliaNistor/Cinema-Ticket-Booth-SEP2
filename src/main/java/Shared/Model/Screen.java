package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Screen implements Serializable {
    private final int screenId; // You may need to add a screenId field based on your database schema
    private final String name;
    private final ArrayList<Seat> seats;

    public Screen(int screenId, String name, ArrayList<Seat> seats) {
        this.screenId = screenId;
        this.name = name;
        this.seats = seats;
    }

    public int getScreenId() {
        return screenId;
    }

    public String getName() {return name;}

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
