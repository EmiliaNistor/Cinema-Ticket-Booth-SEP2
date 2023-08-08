package Client.ViewModel;

import Client.Core.ModelFactory;
import Client.Core.ViewModelFactory;
import Client.Model.*;
import Shared.Model.Seat;
import Shared.Model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketInformationViewModelTest {
    private TicketInformationViewModel viewModel;
    private Shared.Network.IRMIServer IRMIServer;

    private ModelFactory modelFactory;

    @BeforeEach
    void setUp() {
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ITicketModel ticketModel = new TicketModel(IRMIServer);
        IMovieListModel movieListModel = new MovieListModel(IRMIServer);
        IScreenModel screenModel = new ScreenModel(IRMIServer);
        IMenuModel menuModel = new MenuModel(IRMIServer);

        viewModel = new TicketInformationViewModel(viewModelFactory, ticketModel, movieListModel, screenModel, menuModel);
    }

    @Test
    void testSetCurrentTicket() {
        Ticket ticket = new Ticket(1, new Seat("A", 1), 1, 1);
        viewModel.setCurrentTicket(ticket);

        assertEquals("Ticket [1]", viewModel.title().get());
        assertEquals("A - 1", viewModel.seatInfo().get());
        assertEquals("Oppenheimer", viewModel.movieName().get());
        assertEquals("2023-08-08", viewModel.date().get());
        assertEquals("10:00 AM", viewModel.startTime().get());
        assertEquals("13:30 AM", viewModel.endTime().get());
        assertEquals("180", viewModel.length().get());
        assertEquals("Happy Meal", viewModel.chosenMenu().get());
    }


    @Test
    void testOpenCancelTicket() {
        Ticket ticket = new Ticket(2, new Seat("B", 2), 2, 0);
        viewModel.setCurrentTicket(ticket);

        viewModel.openCancelTicket();

        assertEquals("Ticket [Missing info]", viewModel.title().get());
        assertEquals("Missing info", viewModel.seatInfo().get());
        assertEquals("Missing info", viewModel.movieName().get());
        assertEquals("Missing info", viewModel.date().get());
        assertEquals("Missing info", viewModel.startTime().get());
        assertEquals("Missing info", viewModel.endTime().get());
        assertEquals("Missing info", viewModel.length().get());
        assertEquals("Missing info", viewModel.chosenMenu().get());
    }
}
