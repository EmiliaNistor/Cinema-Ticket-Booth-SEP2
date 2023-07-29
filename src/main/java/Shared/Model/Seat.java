package Shared.Model;

public class Seat {
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
}
