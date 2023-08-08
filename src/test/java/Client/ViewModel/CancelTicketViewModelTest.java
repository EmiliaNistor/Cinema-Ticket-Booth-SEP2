package Client.ViewModel;


import Client.Model.*;
import Mocks.RMIServerMock;
import Shared.Model.Seat;
import Shared.Model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CancelTicketViewModelTest {
    private CancelTicketViewModel viewModel;
    private Shared.Network.IRMIServer IRMIServer;

    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();
        ITicketModel ticketModel = new TicketModel(IRMIServer);
        viewModel = new CancelTicketViewModel(ticketModel);
    }


    @Test
    void confirmTicketCancellationTest() {
        Ticket ticket = new Ticket(1, new Seat("A", 1), 1, 1);
        viewModel.setTicket(ticket);
        viewModel.confirmTicketCancellation();

        // cant verify
    }

    @Test
    void setTicketTest() {
        Ticket ticket = new Ticket(2, new Seat("B", 2), 2, 2);
        viewModel.setTicket(ticket);

        // cant verify
    }

    @Test
    void setTicketNullTest() {
        Ticket ticket = new Ticket(2, new Seat("B", 2), 2, 2);
        viewModel.setTicket(ticket);

        // cant verify
    }
}
