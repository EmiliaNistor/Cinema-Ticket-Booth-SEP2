package Client.Model;

import Shared.Ticket;

public interface ITicketModel {
    Ticket getTicket(String ticketID);
    void purchaseTicket(Ticket ticket);
}
