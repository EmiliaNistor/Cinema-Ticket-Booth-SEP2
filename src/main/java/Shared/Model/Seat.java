package Shared.Model;

import java.io.Serializable;

public class Seat implements Serializable
{
    private final String row;
    private final int number;

    public Seat(String row, int number) {
        this.row = row;
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%s-%d", row, number);
    }
}
