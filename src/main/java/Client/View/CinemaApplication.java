package Client.View;

import Client.Core.ModelFactory;
import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class CinemaApplication extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            ModelFactory modelFactory = new ModelFactory();
            ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
            // Create the ViewHandler and start the client application
            ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        }

        public static void main(String[] args) {
            launch(args);
        }
}

