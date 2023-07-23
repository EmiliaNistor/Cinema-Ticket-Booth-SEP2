package Client.ViewModel;

import Client.Model.ITicketModel;
import Shared.Model.Ticket;

public class CancelTicketViewModel implements ICancelTicketViewModel {
    /**
     * The ticket model which handles ticket cancellations
     */
    private final ITicketModel ticketModel;

    public CancelTicketViewModel(ITicketModel ticketModel) {
        this.ticketModel = ticketModel;
    }

    /**
     * Cancels the ticket
     * @param ticket The ticket to cancel
     */
    @Override
    public void confirmTicketCancellation(Ticket ticket) {
        ticketModel.cancelTicket(ticket);
    }
}
