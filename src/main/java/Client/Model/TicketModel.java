package Client.Model;

import java.rmi.RemoteException;
import java.util.HashMap;

import Shared.Model.Ticket;
import Shared.Network.IRMIServer;

public class TicketModel implements ITicketModel {
    private final HashMap<Integer, Ticket> tickets;
    private final IRMIServer serverRMI;

    public TicketModel(IRMIServer serverRMI) {
        tickets = new HashMap<>();
        this.serverRMI = serverRMI;
    }

    public static char[] getMenu() {
        return new char[0];
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

    /**
     * Get the ticket, if it exists
     * @param ticketID The ticket's ID which to fetch
     * @return The ticket with all of it's information, null if no ticket found
     */
    @Override
    public Ticket getTicket(int ticketID) {
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

    /**
     * Cancel a given ticket
     * @param ticket The ticket which to cancel
     */
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
