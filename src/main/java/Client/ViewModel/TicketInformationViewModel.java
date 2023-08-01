package Client.ViewModel;

import javafx.beans.property.*;

public class TicketInformationViewModel {
    // Properties for ticket information
    private final StringProperty ticketNumber = new SimpleStringProperty("");
    private final StringProperty movieName = new SimpleStringProperty("");
    private final StringProperty seatNumber = new SimpleStringProperty("");
    private final StringProperty customerName = new SimpleStringProperty("");
    private final DoubleProperty ticketPrice = new SimpleDoubleProperty(0.0);

    // Constructor (You can add parameters as needed)
    public TicketInformationViewModel() {
        // Initialize any default values or perform any necessary setup here
    }

    // Getter and Setter methods for ticketNumber property
    public String getTicketNumber() {
        return ticketNumber.get();
    }

    public StringProperty ticketNumberProperty() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber.set(ticketNumber);
    }

    // Getter and Setter methods for movieName property
    public String getMovieName() {
        return movieName.get();
    }

    public StringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }

    // Getter and Setter methods for seatNumber property
    public String getSeatNumber() {
        return seatNumber.get();
    }

    public StringProperty seatNumberProperty() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber.set(seatNumber);
    }

    // Getter and Setter methods for customerName property
    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    // Getter and Setter methods for ticketPrice property
    public double getTicketPrice() {
        return ticketPrice.get();
    }

    public DoubleProperty ticketPriceProperty() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice.set(ticketPrice);
    }

    // You can add other methods relevant to the ticket information view model here
}
