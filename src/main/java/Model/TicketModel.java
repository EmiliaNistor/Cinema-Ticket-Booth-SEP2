package Model;

import Model.server.ServerI;
import ViewModel.ITicketModel;

import java.rmi.RemoteException;
import java.util.HashMap;

public class TicketModel implements ITicketModel {
    private final HashMap<String, Ticket> tickets;
    private final ServerI serverRMI;

    public TicketModel(ServerI serverRMI) {
        tickets = new HashMap<>();
        this.serverRMI = serverRMI;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    @Override
    public void purchaseTicket(Ticket ticket) {
        try {
            serverRMI.addTicket(ticket);
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
            for (Ticket t: serverRMI.getAllTickets()) {
                tickets.put(t.getId(), t);
            }
        } catch (RemoteException e) {
            System.out.println("No ticket connection?");
        }

        return tickets.getOrDefault(ticketID, null);
    }

    @Override
    public void cancelTicket(Ticket ticket) {
        tickets.remove(ticket.getId());
        try {
            serverRMI.deleteTicket(ticket);
        } catch (RemoteException e) {
            System.out.println("Couldn't cancel :(");
        }
    }
}
