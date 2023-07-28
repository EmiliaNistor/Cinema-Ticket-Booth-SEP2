package Client.Core;


import Client.Model.IMovieListModel;
import Client.Model.ITicketModel;
import Client.Model.MovieListModel;
import Client.Model.TicketModel;
import Shared.Network.IRMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModelFactory {
    private final ITicketModel ticketModel;
    private  final  IMovieListModel movieListModel;

    public ModelFactory() throws NotBoundException, RemoteException {
        // Setting up RMI
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // Getting server RMI Interface from registry
        IRMIServer serverRMI = (IRMIServer) registry.lookup("IRMIServer");

        // Creating models
        ticketModel = new TicketModel(serverRMI);
        movieListModel = new MovieListModel(serverRMI);


    }

    /**
     * Returns the interface with available methods related to tickets
     * @return TicketModel interface
     */
    public ITicketModel getTicketModel() {
        return ticketModel;
    }
    public IMovieListModel getMovieListModel() {
        return movieListModel;
    }

}
