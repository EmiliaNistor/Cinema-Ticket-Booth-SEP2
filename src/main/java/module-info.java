module via.dk.cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;

    exports Client.View;
    opens Client.View to javafx.fxml;
    exports Client;
    opens Client to javafx.fxml;
    exports Client.Model;
    opens Client.Model to javafx.fxml;

    exports Shared;

    exports Server.RMI;
    exports Server.Utils;
}