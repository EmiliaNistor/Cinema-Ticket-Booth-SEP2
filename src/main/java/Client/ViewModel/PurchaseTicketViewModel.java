package Client.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PurchaseTicketViewModel {

    private StringProperty movieName;
    private StringProperty movieScreen;
    private StringProperty movieHour1;
    private StringProperty movieHour2;
    private StringProperty moviePrice;

    public void PurchaseTicketViewModel() {
        movieName = new SimpleStringProperty();
        movieScreen = new SimpleStringProperty();
        movieHour1 = new SimpleStringProperty();
        movieHour2 = new SimpleStringProperty();
        moviePrice = new SimpleStringProperty();
    }

    public String getMovieName() {
        return movieName.get();
    }

    public void setMovieName(String name) {
        movieName.set(name);
    }

    public StringProperty movieNameProperty() {
        return movieName;
    }

    public String getMovieScreen() {
        return movieScreen.get();
    }

    public void setMovieScreen(String screen) {
        movieScreen.set(screen);
    }

    public StringProperty movieScreenProperty() {
        return movieScreen;
    }

    public String getMovieHour1() {
        return movieHour1.get();
    }

    public void setMovieHour1(String hour) {
        movieHour1.set(hour);
    }

    public StringProperty movieHour1Property() {
        return movieHour1;
    }

    public String getMovieHour2() {
        return movieHour2.get();
    }

    public void setMovieHour2(String hour) {
        movieHour2.set(hour);
    }

    public StringProperty movieHour2Property() {
        return movieHour2;
    }

    public String getMoviePrice() {
        return moviePrice.get();
    }

    public void setMoviePrice(String price) {
        moviePrice.set(price);
    }

    public StringProperty moviePriceProperty() {
        return moviePrice;
    }
}


