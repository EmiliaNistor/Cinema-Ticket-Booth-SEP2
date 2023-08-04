package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.ITicketInformationViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketInformationController {
    private ITicketInformationViewModel viewModel;
    private ViewHandler viewHandler;
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
    private Label seatInfo;
    @FXML
    private Label chosenMenu;

    public void init(ViewHandler viewHandler, ITicketInformationViewModel viewModel)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;

        // Binding values
        title.textProperty().bind(viewModel.title());
        movieName.textProperty().bind(viewModel.movieName());
        movieDate.textProperty().bind(viewModel.date());
        movieStartTime.textProperty().bind(viewModel.startTime());
        movieEndTime.textProperty().bind(viewModel.endTime());
        movieLength.textProperty().bind(viewModel.length());
        chosenMenu.textProperty().bind(viewModel.chosenMenu());
        seatInfo.textProperty().bind(viewModel.seatInfo());
    }

    @FXML
    private void cancelTicket(ActionEvent action) {
        viewModel.openCancelTicket();
    }
}
