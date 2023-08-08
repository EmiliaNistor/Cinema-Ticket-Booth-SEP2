package Shared.Model;

import java.io.Serializable;

public class Seat implements Serializable
{
    private final String row;
    private final int number;

    /**
     * A seat within a screen
     * @param row Row where seat is located, suggested to use words or letters
     * @param number Seat's number within row
     */
    public Seat(String row, int number) {
        this.row = row;
        this.number = number;
    }

    /**
     * The row where the seat is located
     * @return Seat's row
     */
    public String getRow() {
        return row;
    }

    /**
     * Seat's number within a row
     * @return The seat's number
     */
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%s-%d", row, number);
    }
}
