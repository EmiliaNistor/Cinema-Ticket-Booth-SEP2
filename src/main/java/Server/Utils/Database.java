package Server.Utils;

import Shared.*;

public interface Database {
     void makePurchase(Ticket ticket, Seat seat, Movie movie);
     Ticket getTicket(int ticketId);
}
