package Client.Model;

import Shared.Model.Ticket;

public interface ITicketModel {
    /**
     * Get the ticket, if it exists
     * @param ticketID The ticket's ID which to fetch
     * @return The ticket with all of it's information, null if no ticket found
     */
    Ticket getTicket(int ticketID);

    /**
     * Cancel a given ticket
     * @param ticket The ticket which to cancel
     */
    void cancelTicket(Ticket ticket);

    /**
     * Make a ticket purchase
     * @param ticket Ticket containing its info
     * @return The purchased ticket, null if failed
     */
    Ticket purchaseTicket(Ticket ticket);
}
