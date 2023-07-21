package Client.View.controllers;

import Client.core.ViewModelFactory;
import Client.ViewModel.purchaseTicketViewModel;
import Client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class purchaseTicketController {


    @FXML
    private Button purchaseTicket;

    @FXML
    private Label movieName;

    @FXML
    private Label movieScreen;

    @FXML
    private Button movieHour1;

    @FXML
    private Button movieHour2;

    @FXML
    private Label moviePrice;

    private purchaseTicketViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {

        this.viewHandler = viewHandler;
        this.viewModel = vmf.getPurchaseTicketViewModel();
        movieName.textProperty().bind(viewModel.movieNameProperty());
        movieScreen.textProperty().bind(viewModel.movieScreenProperty());
        movieHour1.textProperty().bind(viewModel.movieHour1Property());
        movieHour2.textProperty().bind(viewModel.movieHour2Property());
        moviePrice.textProperty().bind(viewModel.moviePriceProperty());
        
    }

    @FXML
    private void purchaseTicket() {

        String selectedMovieName = viewModel.getMovieName();
        String selectedScreen = viewModel.getMovieScreen();
        String selectedHour = "";
        String ticketPrice = viewModel.getMoviePrice();

        viewHandler.openTicketInformation();
    }


}
