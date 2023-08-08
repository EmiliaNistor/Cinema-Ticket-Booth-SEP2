package Client.ViewModel;


import Client.Core.ModelFactory;
import Client.Core.ViewModelFactory;
import Client.Model.*;
import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Network.IRMIServer;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTicketPopUpViewModelTest {
    private PurchaseTicketPopUpViewModel viewModel;
    private IRMIServer IRMIServer;
    private ModelFactory ModelFactory;

    @BeforeEach
    void setUp() {
        ViewModelFactory viewModelFactory = new ViewModelFactory(ModelFactory);
        IMovieListModel MovieListModel = new MovieListModel(IRMIServer);
        IScreenModel ScreenModel = new ScreenModel(IRMIServer);
        ITicketModel TicketModel = new TicketModel(IRMIServer);
        IMenuModel MenuModel = new MenuModel(IRMIServer);

        viewModel = new PurchaseTicketPopUpViewModel(
                viewModelFactory, MovieListModel, ScreenModel, TicketModel, MenuModel
        );
    }

    @Test
    void testSetMovie() {
        Movie movie = new Movie(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), 120);
        viewModel.setMovie(movie);

        assertEquals("Movie Name", viewModel.getMovieNameProperty().get());
        assertEquals("120", viewModel.getMovieLengthProperty().get());
        assertEquals(LocalDate.now().toString(), viewModel.getMovieDateProperty().get());
        assertEquals(LocalTime.now().plusHours(2).toString(), viewModel.getMovieEndTimeProperty().get());

        ObservableList<LocalTime> startTimes = viewModel.getMovieStartTimes();
        assertEquals(1, startTimes.size());
        assertTrue(startTimes.contains(LocalTime.now()));

        assertTrue(viewModel.getTicketMenuOptions().isEmpty());
    }

    @Test
    void testUpdateMovieStart() {
        Movie movie = new Movie(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), 120);
        viewModel.setMovie(movie);

        viewModel.updateMovieStart(LocalTime.now());

        ObservableList<Seat> seatOptions = viewModel.getTicketSeatOptions();
        assertEquals(100, seatOptions.size());

        assertTrue(viewModel.getTicketMenuOptions().isEmpty());
    }

    @Test
    void testUpdateMenu() {
        viewModel.updateMenu(null);
        assertEquals("100.00 DKK", viewModel.getTicketPriceProperty().get());

        Menu menu = new Menu(1, "Happy Meal", 120.00);
        viewModel.updateMenu(menu);
        assertEquals("120.00 DKK", viewModel.getTicketPriceProperty().get());
    }

}
