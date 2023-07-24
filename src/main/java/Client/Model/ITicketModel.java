package Client.Model;

import Shared.Model.Ticket;

public interface ITicketModel {
    Ticket getTicket(String ticketID);

    Ticket getTicket(int ticketID);

    void cancelTicket(Ticket ticket);
    void purchaseTicket(Ticket ticket);
}
