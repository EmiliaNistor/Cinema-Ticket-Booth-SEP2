module via.dk.cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;





    exports Shared.Model;

    exports Client.ViewModel;
    opens Client.ViewModel to javafx.fxml;
    opens Shared.Model to javafx.fxml;
    exports Client.View.Controllers;
    opens Client.View.Controllers to javafx.fxml;
    exports Client.View;
    opens Client.View to javafx.fxml;
    exports Shared.Network;



    exports Client.Core;
    opens Client.Core to javafx.fxml;
    exports Client.Network;
    opens Client.Network to javafx.fxml;


}