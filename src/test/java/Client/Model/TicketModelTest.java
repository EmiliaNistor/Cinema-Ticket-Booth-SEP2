package Client.Model;

import Mocks.RMIServerMock;
import Shared.Model.Seat;
import Shared.Model.Ticket;
import Shared.Network.IRMIServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketModelTest {

    private IRMIServer rmiServer;
    private ITicketModel ticketModel;

    @BeforeEach
    void setUp() {
        rmiServer = new RMIServerMock();
        ticketModel = new TicketModel(rmiServer);
    }

    @AfterEach
    void tearDown() {
        rmiServer = null;
        ticketModel = null;
    }

    @Test
    void purchaseTicketFail() {
        assertNull(ticketModel.purchaseTicket(
                new Ticket(-1, null, 1, -1)
        ));
    }

    @Test
    void purchaseTicketSuccess() {
        assertNotNull(ticketModel.purchaseTicket(
                new Ticket(-1, new Seat("", 1), 1, -1)
        ));
    }

    @Test
    void getTicketFail() {
        assertNull(ticketModel.getTicket(-1));
    }

    @Test
    void getTicketSuccess() {
        assertNotNull(ticketModel.getTicket(1));
    }
}