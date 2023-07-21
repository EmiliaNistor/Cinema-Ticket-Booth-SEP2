package Client.network;

import Client.core.ClientFactory;
import Client.core.ModelFactory;
import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class RMIClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the ClientFactory, ModelFactory, and ViewModelFactory
        ClientFactory clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);

        // Create the ViewHandler and start the client application
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}