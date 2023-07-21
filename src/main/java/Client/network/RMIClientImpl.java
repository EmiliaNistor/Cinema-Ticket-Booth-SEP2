package Client.network;

import Shared.Model.TicketModel;
import Shared.Model.Ticket;
import Shared.network.IRMIClient;
import Shared.network.IRMIServer;

import java.rmi.RemoteException;

public class RMIClientImpl implements IRMIClient {
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
