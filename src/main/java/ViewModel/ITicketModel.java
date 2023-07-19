package ViewModel;

import Model.Ticket;

public interface ITicketModel {
    Ticket getTicket(String ticketID);
    void cancelTicket(Ticket ticket);
    void purchaseTicket(Ticket ticket);
}
