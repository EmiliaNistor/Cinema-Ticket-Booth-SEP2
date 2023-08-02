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

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    @Override
    public void purchaseTicket(Ticket ticket) {
        try {
            Ticket purchasedTicket = serverRMI.purchase(ticket);
            if (purchasedTicket == null) {
                // purchase failed!
                System.out.println("Ticket purchase failed");
                return;
            }

            tickets.put(purchasedTicket.getId(), purchasedTicket);
        } catch (Exception e) {
            System.out.println("Ticket purchase failed");
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
            Ticket ticket = serverRMI.getTicketById(ticketID);
            return ticket;
        } catch (RemoteException e) {
            System.out.println("Getting information about a ticket failed!");
        }

        return null;
    }

    /**
     * Cancel a given ticket
     * @param ticket The ticket which to cancel
     */
    @Override
    public void cancelTicket(Ticket ticket) {
        try {
            serverRMI.cancelTicket(ticket.getId());
            tickets.remove(ticket.getId());
        } catch (RemoteException e) {
            System.out.println("Couldn't cancel the ticket");
        }
    }
}
