package Client.Core;


import Client.Model.ITicketModel;
import Client.Model.TicketModel;
import Shared.Network.IRMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModelFactory {
    private final ITicketModel ticketModel;
    //private final ServerI serverRMI;

    public ModelFactory() throws NotBoundException, RemoteException {
        // Setting up RMI
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // Getting server RMI Interface from registry
        IRMIServer serverRMI = (IRMIServer) registry.lookup("IRMIServer");

        // Creating models

        ticketModel = new TicketModel(serverRMI);
    }

    public ITicketModel getTicketModel() {
        return ticketModel;
    }
}
