package Server.RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            CinemaRemoteImpl remoteImpl = new CinemaRemoteImpl();

            // Create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object's stub in the registry
            registry.rebind("CinemaRemote", remoteImpl);

            System.out.println("RMI server is ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

