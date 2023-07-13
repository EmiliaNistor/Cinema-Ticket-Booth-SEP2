package main.RMI;

import RMI.CinemaRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            while(true){
                {
                    // Get reference to rmi registry
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);

                    // Lookup the remote object
                    CinemaRemote remote = (CinemaRemote) registry.lookup("CinemaRemote");

                    // Call the remote methods
                    System.out.println(remote.getAllTickets());
                    System.out.println(remote.getAllMovies());
                    System.out.println(remote.getAllMenuItems());

                    //Keep the client running
                }
            }



        } catch (Exception e) {
            System.err.println("RMI client exception: " + e.getMessage());
        }
    }
}
