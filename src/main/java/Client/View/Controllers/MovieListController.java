package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import javafx.event.ActionEvent;

//import junit testing libraries for assertions and annotations for test methods (e.g. @Test)

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieListController {
    private ViewHandler vh;

    @FXML
    private Button ticketInfoButton;

    // This method will be invoked when the ticketInfoButton is clicked
    @FXML
    private void opnTicketInfo(ActionEvent actionEvent) {
        try {
            // Load the ticketInformationPopUp.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ticketInformationPopUp.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Ticket Information");
            popupStage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // set up controller's internal values
            TicketInformationPopupController controller = fxmlLoader.getController();
            controller.init(vh.getViewTicketPopupViewModel(), popupStage);

            Scene scene = new Scene(root);
            popupStage.setScene(scene);
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(ViewHandler vh) {
        this.vh = vh;
        System.out.println("MovieListController init");
    }
}
