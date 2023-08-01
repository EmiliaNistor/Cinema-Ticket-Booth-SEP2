package Client.ViewModel;

import Shared.Model.Ticket;

public interface IViewTicketPopupViewModel {
    /**
     * Open the view containing information about the ticket
     * @param ticketID Ticket ID whose information to show
     */
    Ticket getTicket(int ticketID);
}
