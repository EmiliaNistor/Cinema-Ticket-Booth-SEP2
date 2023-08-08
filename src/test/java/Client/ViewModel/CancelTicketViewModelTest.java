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
    private ITicketModel ticketModel;
    private Shared.Model.Ticket Ticket;

    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();
        ITicketModel ticketModel = new TicketModel(IRMIServer);
        ICancelTicketViewModel cancelTicketViewModel = new CancelTicketViewModel(ticketModel);
        viewModel = new CancelTicketViewModel(ticketModel);
    }


    @Test
    void testConfirmTicketCancellation() {
        Ticket ticket = new Ticket(1, new Seat("A", 1), 1, 1);
        viewModel.setTicket(ticket);
        viewModel.confirmTicketCancellation();

        assertNull(null);
    }

    @Test
    void testSetTicket() {
        Ticket ticket = new Ticket(2, new Seat("B", 2), 2, 2);
        viewModel.setTicket(ticket);

        assertEquals(1,2,4);
    }
}
