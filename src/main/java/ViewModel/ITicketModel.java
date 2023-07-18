package ViewModel;

import Model.Ticket;

public interface ITicketModel {
    Ticket getTicket(String ticketID);
    void purchaseTicket(Ticket ticket);
}