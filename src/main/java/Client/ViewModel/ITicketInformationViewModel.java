package Client.ViewModel;

import javafx.beans.property.StringProperty;

public interface ITicketInformationViewModel {
    StringProperty title();
    StringProperty movieName();
    StringProperty date();
    StringProperty startTime();
    StringProperty endTime();
    StringProperty length();
    StringProperty seatInfo();
    StringProperty chosenMenu();

    /**
     * Open cancel window to cancel the ticket
     */
    void openCancelTicket();
}
