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
    void purchaseTicketNullSeatTest() {
        assertNull(ticketModel.purchaseTicket(
                new Ticket(-1, null, 1, -1)
        ));
    }

    @Test
    void purchaseTicketNullTest() {
        assertNull(ticketModel.purchaseTicket(null));
    }

    @Test
    void purchaseTicketTest() {
        assertNotNull(ticketModel.purchaseTicket(
                new Ticket(-1, new Seat("", 1), 1, -1)
        ));
    }

    @Test
    void getTicketFailTest() {
        assertNull(ticketModel.getTicket(-1));
    }

    @Test
    void getTicketOneTest() {
        assertNotNull(ticketModel.getTicket(1));
    }
}