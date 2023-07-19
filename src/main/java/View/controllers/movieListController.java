package View.controllers;


import Client.ViewModel.ViewModelFactory;
import Client.ViewModel.movieListViewModel;

import Model.Movie;
import View.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class movieListController implements ViewController {

    private movieListViewModel viewModel;
    private ViewHandler viewHandler;

    @FXML
    private TableView<Movie> Table;
    @FXML
    private TableColumn<Movie,String> Movie;
    @FXML
    private TableColumn<Movie,String> Genre;
    @FXML
    private TableColumn<Movie, Integer> Length;
    @FXML
    private TableColumn<Movie, Integer> Screen ;

    @FXML
    private TableColumn<Movie,Button> BuyTicket;


    @FXML
    private Button ticketInfo;

    @FXML
    private void opnTicketInfo() {
        viewHandler.openTicketInformation();
    }


    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
        this.viewHandler = viewHandler;
        this.viewModel = vmf.getMovieListViewModel();
        Movie.setCellValueFactory(new PropertyValueFactory<>("Movie"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        Length.setCellValueFactory(new PropertyValueFactory<>("Length"));
        Screen.setCellValueFactory(new PropertyValueFactory<>("Screen"));
        BuyTicket.setCellValueFactory(new PropertyValueFactory<Movie,Button>("Buy Ticket"));

    }


}
