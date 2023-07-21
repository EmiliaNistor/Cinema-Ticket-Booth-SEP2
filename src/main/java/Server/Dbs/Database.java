package Server.Dbs;

import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.Ticket;

public interface Database {
     void makePurchase(Ticket ticket, Seat seat, Movie movie);
     Ticket getTicket(int ticketId);
}
