package Client.Model;

import Shared.ClientI;
import Shared.Ticket;
import java.rmi.RemoteException;

public class RMIClientImpl implements ClientI {
    private final TicketModel ticketModel;

    public RMIClientImpl(TicketModel tm) throws RemoteException {
        ticketModel = tm;
    }

    @Override
    public void responsePurchaseTicket(Ticket providedTicket, boolean successful) throws RemoteException {

    }

    @Override
    public void responseGetTicketInfo(Ticket ticket) throws RemoteException {
        ticketModel.addTicket(ticket);
    }
}
