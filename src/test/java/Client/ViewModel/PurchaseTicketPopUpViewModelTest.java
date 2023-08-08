package Client.ViewModel;


import Client.Core.ModelFactory;
import Client.Core.ViewModelFactory;
import Client.Model.*;
import Mocks.RMIServerMock;
import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Network.IRMIServer;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTicketPopUpViewModelTest {
    private PurchaseTicketPopUpViewModel viewModel;
    private IRMIServer IRMIServer;

    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();

        IMovieListModel MovieListModel = new MovieListModel(IRMIServer);
        IScreenModel ScreenModel = new ScreenModel(IRMIServer);
        ITicketModel TicketModel = new TicketModel(IRMIServer);
        IMenuModel MenuModel = new MenuModel(IRMIServer);

        viewModel = new PurchaseTicketPopUpViewModel(
                null, MovieListModel, ScreenModel, TicketModel, MenuModel
        );
    }

    @AfterEach
    void tearDown() {
        IRMIServer = null;
        viewModel = null;
    }



    @Test
    void updateMovieStartTest() {
        Movie movie = new Movie(1,"Movie Name",
                LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), "Comedy",100, 1);
        viewModel.setMovie(movie);

        viewModel.updateMovieStart(LocalTime.now());

        ObservableList<Seat> seatOptions = viewModel.getTicketSeatOptions();
        assertEquals(0, seatOptions.size());

    }

    @Test
    void updateMenuTest() {
        Menu menu = new Menu(1, "Happy Meal", 120.00);
        viewModel.updateMenu(menu);
        assertEquals("220.00 DKK", viewModel.getTicketPriceProperty().get());
    }

    @Test
    void updateMenuNullTest() {
        viewModel.updateMenu(null);
        assertEquals("100.00 DKK", viewModel.getTicketPriceProperty().get());
    }
}
