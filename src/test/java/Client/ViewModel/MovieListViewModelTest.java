package Client.ViewModel;


import Client.Model.*;
import Mocks.RMIServerMock;
import Shared.Model.Movie;
import Shared.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListViewModelTest {

    private MovieListViewModel viewModel;
    private Shared.Network.IRMIServer IRMIServer;


    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();

        IMovieListModel movieListModel = new MovieListModel(IRMIServer);
        IAccountModel accountModel = new AccountModel(IRMIServer);

        viewModel = new MovieListViewModel(null, movieListModel, accountModel);
    }

    @Test
    void initialStateTest() {
        assertFalse(viewModel.loggedInProperty().get());
        assertFalse(viewModel.administratorProperty().get());
        assertTrue(viewModel.getMovieList().isEmpty());
    }




}

