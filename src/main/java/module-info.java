module via.dk.cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;





    exports Shared.Model;

    exports Client.ViewModel;
    opens Client.ViewModel to javafx.fxml;
    opens Shared.Model to javafx.fxml;
    exports Client.View.controllers;
    opens Client.View.controllers to javafx.fxml;
    exports Client.View;
    opens Client.View to javafx.fxml;
    exports Shared.network;



    exports Client.core;
    opens Client.core to javafx.fxml;
    exports Client.network;
    opens Client.network to javafx.fxml;


}