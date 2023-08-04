package Client.View.Controllers;

import Client.ViewModel.ITicketInformationViewModel;
import Shared.Model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketInformationController {
    private ITicketInformationViewModel viewModel;
    @FXML
    private Label title;
    @FXML
    private Label movieName;
    @FXML
    private Label movieDate;
    @FXML
    private Label movieStartTime;
    @FXML
    private Label movieEndTime;
    @FXML
    private Label movieLength;
    @FXML
    private Label movieSeatInfo;
    @FXML
    private Label chosenMenu;

    public void init(ITicketInformationViewModel viewModel)
    {
        this.viewModel = viewModel;

        // Binding values
        title.textProperty().bind(viewModel.title());
        movieName.textProperty().bind(viewModel.movieName());
        movieDate.textProperty().bind(viewModel.date());
        movieStartTime.textProperty().bind(viewModel.startTime());
        movieEndTime.textProperty().bind(viewModel.endTime());
        movieLength.textProperty().bind(viewModel.length());
        chosenMenu.textProperty().bind(viewModel.chosenMenu());
    }

    @FXML
    public void cancelTicket(ActionEvent action) {
        viewModel.openCancelTicket();
    }
}
