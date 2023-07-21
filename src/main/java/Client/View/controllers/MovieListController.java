package Client.View.controllers;

import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import javafx.event.ActionEvent;

//import junit testing libraries for assertions and annotations for test methods (e.g. @Test)

import Shared.Model.Ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieListController implements ViewController {
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
            Scene scene = new Scene(root);

            popupStage.setScene(scene);
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        System.out.println("MovieListController init");
    }
}
