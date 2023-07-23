package Client.View.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TicketInformationController {
    //will be used for later
    //controller.init(this, viewModelFactory.makeMainViewModel());

    @FXML
    private void opnBackInfo(ActionEvent actionEvent) {
        // Get the reference to the stage that contains the button
        Stage stage = (Stage) ((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow();

        // Close the stage (ticket information window)
        stage.close();
    }
}
