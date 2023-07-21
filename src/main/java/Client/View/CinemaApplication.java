package Client.View;

import Client.core.ModelFactory;
import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CinemaApplication extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
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

