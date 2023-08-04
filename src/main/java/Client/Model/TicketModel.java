package Client.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.HashMap;

import Client.Core.ModelFactory;
import Client.Core.PropertyChangeSubject;
import Shared.Model.Screen;
import Shared.Model.Seat;
import Shared.Model.Ticket;
import Shared.Network.IRMIServer;

public class TicketModel implements ITicketModel, PropertyChangeSubject {
    private final HashMap<Integer, Ticket> tickets;
    private final IRMIServer serverRMI;
    private final PropertyChangeSupport propertyChangeSupport;

    public TicketModel(IRMIServer serverRMI) {
        tickets = new HashMap<>();
        this.serverRMI = serverRMI;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public Ticket purchaseTicket(Ticket ticket) {
        try {
            Ticket purchasedTicket = serverRMI.purchase(ticket);
            if (purchasedTicket == null) {
                // purchase failed!
                System.out.println("Ticket purchase failed");
                return null;
            }

            tickets.put(purchasedTicket.getId(), purchasedTicket);
            propertyChangeSupport.firePropertyChange(
                    "TicketAdded", null, ticket
            );
            return purchasedTicket;
        } catch (Exception e) {
            System.out.println("Ticket purchase failed");
        }

        return null;
    }

    @Override
    public Ticket getTicket(int ticketID) {
        if (tickets.containsKey(ticketID)) {
            return tickets.get(ticketID);
        }

        try {
            Ticket ticket = serverRMI.getTicketById(ticketID);
            if (ticket != null) {
                tickets.put(ticket.getId(), ticket);
                propertyChangeSupport.firePropertyChange(
                        "TicketAdded", null, ticket
                );
            }

            return ticket;
        } catch (RemoteException e) {
            System.out.println("Getting information about a ticket failed!");
        }

        return null;
    }

    @Override
    public void cancelTicket(Ticket ticket) {
        try {
            serverRMI.cancelTicket(ticket.getId());

            propertyChangeSupport.firePropertyChange(
                    "TicketRemoved", tickets.get(ticket.getId()), null
            );
            tickets.remove(ticket.getId());
        } catch (RemoteException e) {
            System.out.println("Couldn't cancel the ticket");
        }
    }

    // Property Change Subject implementations
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
