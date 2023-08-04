package Client.ViewModel;

import Shared.Model.Ticket;
import javafx.beans.property.StringProperty;

public interface ICancelTicketViewModel {
    /**
     * Cancels the ticket
     */
    void confirmTicketCancellation();
    StringProperty title();
    StringProperty info();
}
