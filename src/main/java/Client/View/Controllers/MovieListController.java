package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMovieListViewModel;

//import junit testing libraries for assertions and annotations for test methods (e.g. @Test)

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MovieListController {
    private ViewHandler viewHandler;
    private IMovieListViewModel viewModel;

    /**
     * Sets the base information about the controller
     * @param viewHandler View Handler to provide additional functionality
     * @param viewModel The controller's view model
     */
    public void init(ViewHandler viewHandler, IMovieListViewModel viewModel) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
    }
}
