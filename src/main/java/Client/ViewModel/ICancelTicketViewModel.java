package Client.ViewModel;

import Shared.Model.Ticket;

public interface ICancelTicketViewModel {
    /**
     * Cancels the ticket
     * @param ticket The ticket to cancel
     */
    void confirmTicketCancellation(Ticket ticket);
}
