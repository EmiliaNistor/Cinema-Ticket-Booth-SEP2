package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMovieListViewModel;

//import junit testing libraries for assertions and annotations for test methods (e.g. @Test)

import Shared.Model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MovieListController
{
    private ViewHandler viewHandler;
    private IMovieListViewModel viewModel;

    // Controller stuff
    @FXML
    private Button addMovieButton;
    @FXML
    private Button purchaseMovieButton;
    @FXML
    private Button modifyMovieButton;
    @FXML
    private TableView<Movie> movies;
    @FXML
    private TableColumn<Movie,String> title;
    @FXML
    private TableColumn<Movie,String> genre;
    @FXML
    private TableColumn<Movie,Integer> length;
    @FXML
    private TableColumn<Movie, LocalDate> date;



    /**
     * Sets the base information about the controller
     *
     * @param viewHandler View Handler to provide additional functionality
     * @param viewModel   The controller's view model
     */
    public void init(ViewHandler viewHandler, IMovieListViewModel viewModel)
    {
        System.out.println("Movie list controller initializing!");
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        movies.setItems(viewModel.getMovieList());
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // regular user buttons
        purchaseMovieButton.visibleProperty().bind(viewModel.loggedInProperty());
        // administrator buttons
        addMovieButton.visibleProperty().bind(viewModel.administratorProperty());
        modifyMovieButton.visibleProperty().bind(viewModel.administratorProperty());
    }

    @FXML
    private void addMovie(ActionEvent actionEvent)
    {

    }

    @FXML
    private void modifyMovie(ActionEvent actionEvent)
    {

    }

    @FXML
    private void purchaseTicket(ActionEvent actionEvent)
    {
        Movie selectedMovie = movies.getSelectionModel().getSelectedItem();
        viewModel.setPopUpMovie(selectedMovie);
        viewHandler.openPurchaseTicketPopup();
    }
}
