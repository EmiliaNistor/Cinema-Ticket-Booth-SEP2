package Shared.Model;

import java.io.Serializable;

public class Seat implements Serializable
{
    private final int row;
    private final int number;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }
}
