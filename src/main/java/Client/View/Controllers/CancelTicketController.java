package Client.View.Controllers;

import Client.ViewModel.ICancelTicketViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CancelTicketController {
    @FXML
    private Label titleText;
    @FXML
    private Label infoText;
    private ICancelTicketViewModel viewModel;

    @FXML
    private void cancel(ActionEvent actionEvent) {
        // Close cancel ticket controller window
        Stage stage = (Stage) titleText.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void confirm(ActionEvent actionEvent) {
        viewModel.confirmTicketCancellation();
        Stage stage = (Stage) titleText.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the base information about the controller
     * @param viewModel The controller's view model
     */
    public void init(ICancelTicketViewModel viewModel) {
        this.viewModel = viewModel;
        titleText.textProperty().bind(viewModel.title());
        infoText.textProperty().bind(viewModel.info());
    }
}
