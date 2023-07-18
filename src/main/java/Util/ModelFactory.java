package Util;

import Model.TicketModel;
import Model.client.ClientI;
import Model.client.RMIClientImpl;
import Model.server.ServerI;
import ViewModel.ITicketModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModelFactory {
    private final ITicketModel ticketModel;
    private final ClientI clientRMI;
    private final ServerI serverRMI;

    public ModelFactory() throws NotBoundException, RemoteException {
        // Setting up RMI
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // Getting server RMI Interface from registry
        serverRMI = (ServerI) registry.lookup("ServerI");

        // Creating models
        var tm = new TicketModel(this);
        ticketModel = tm;

        // Client RMI setup
        clientRMI = new RMIClientImpl(tm);
    }

    public ClientI getClientRMI() {
        return clientRMI;
    }

    public ServerI getServerRMI() {
        return serverRMI;
    }
}
