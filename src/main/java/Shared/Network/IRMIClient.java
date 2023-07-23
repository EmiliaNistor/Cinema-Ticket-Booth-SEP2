package Shared.Network;

import Shared.Model.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client RMI interface for the server to call, used for responses
 */
public interface IRMIClient extends Remote {
    /**
     * Server response to a ticket purchase
     * @param providedTicket The ticket info provided by client
     * @param successful True when ticket purchase has been successful
     */
    void responsePurchaseTicket(Ticket providedTicket, boolean successful) throws RemoteException;

    /**
     * Server response to an information request about a ticket
     * @param ticket Information about the ticket
     */
    void responseGetTicketInfo(Ticket ticket) throws RemoteException;
}
