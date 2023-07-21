package Client.core;


import Client.ViewModel.ITicketModel;
import Shared.Model.TicketModel;
import Shared.network.IRMIServer;

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
        IRMIServer serverRMI = (IRMIServer) registry.lookup("ServerI");

        // Creating models

        ticketModel = new TicketModel(serverRMI);
    }

    /*public ServerI getServerRMI() {
        return serverRMI;
    }*/

    public ITicketModel getTicketModel() {
        return ticketModel;
    }
}
