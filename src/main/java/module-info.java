module via.dk.cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;




    exports Util;
    exports Model;
    exports Model.server;
    exports ViewModel;
    opens ViewModel to javafx.fxml;
    opens Util to javafx.fxml;
    opens Model to javafx.fxml;
    exports View.controllers;
    opens View.controllers to javafx.fxml;
    exports View;
    opens View to javafx.fxml;
    exports Model.client;
    opens Model.client to javafx.fxml;


}