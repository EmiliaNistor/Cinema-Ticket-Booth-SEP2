package View.controllers;

import Client.Model.MovieList;
import Client.ViewModel.ViewModelFactory;
import Client.ViewModel.movieListViewModel;
import Server.Utils.DatabaseImpl;
import View.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class movieListController implements ViewController {

    private movieListViewModel viewModel;
    private ViewHandler viewHandler;

    @FXML
    private TableView<MovieList> Table;
    @FXML
    private TableColumn<MovieList,String> Movie;
    @FXML
    private TableColumn<MovieList,String> Genre;
    @FXML
    private TableColumn<MovieList, Integer> Length;
    @FXML
    private TableColumn<MovieList, Integer> Screen ;

    private ObservableList<MovieList> list = FXCollections.observableArrayList();

    private DatabaseImpl dbImpl;

    {
        try {
            dbImpl = new DatabaseImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
        this.viewHandler = viewHandler;
        this.viewModel = vmf.Ope
    }
}
