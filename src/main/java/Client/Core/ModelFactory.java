package Client.Core;


import Client.Model.*;
import Shared.Network.IRMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModelFactory {
    private final ITicketModel ticketModel;
    private  final  IMovieListModel movieListModel;

    private final ISignupModel signupModel;

    private final IScreenModel screenModel;

    public ModelFactory() throws NotBoundException, RemoteException {
        // Setting up RMI
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // Getting server RMI Interface from registry
        IRMIServer serverRMI = (IRMIServer) registry.lookup("IRMIServer");

        // Creating models
        ticketModel = new TicketModel(serverRMI);
        movieListModel = new MovieListModel(serverRMI);
        screenModel = new ScreenModel(serverRMI, movieListModel);
        signupModel = new SignupModel(serverRMI);

    }

    /**
     * Returns the interface with available methods related to tickets
     * @return TicketModel interface
     */
    public ITicketModel getTicketModel() {
        return ticketModel;
    }

    /**
     * Returns the interface with available methods related to movies
     * @return MovieListModel interface
     */
    public IMovieListModel getMovieListModel() {
        return movieListModel;
    }

    /**
     * Returns the interface with methods related to screens and their movies
     * @return ScreenModel interface
     */
    public IScreenModel getScreenModel() {
        return screenModel;
    }

    public ISignupModel getSignupModel() {
        return signupModel;
    }

}
