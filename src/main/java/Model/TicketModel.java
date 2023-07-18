package Model;

import Model.Ticket;
import Util.ModelFactory;
import ViewModel.ITicketModel;

import java.rmi.RemoteException;
import java.util.HashMap;

public class TicketModel implements ITicketModel {
    private final HashMap<String, Ticket> tickets;
    private final ModelFactory factory;

    public TicketModel(ModelFactory factory) {
        tickets = new HashMap<>();
        this.factory = factory;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    @Override
    public void purchaseTicket(Ticket ticket) {
        try {
            factory.getServerRMI().addTicket(ticket);
        } catch (Exception e) {
            System.out.println("Purchasing is crying");
        }
    }

    @Override
    public Ticket getTicket(String ticketID) {
        if (tickets.containsKey(ticketID)) {
            return tickets.get(ticketID);
        }

        // Refreshing all tickets
        try {
            for (Ticket t: factory.getServerRMI().getAllTickets()) {
                tickets.put(t.getId(), t);
            }
        } catch (RemoteException e) {
            System.out.println("No ticket connection?");
        }

        return tickets.getOrDefault(ticketID, null);
    }
}
