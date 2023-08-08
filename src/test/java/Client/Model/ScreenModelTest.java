package Client.Model;

import Mocks.RMIServerMock;
import Shared.Network.IRMIServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreenModelTest {
    private IRMIServer rmiServer;
    private IScreenModel screenModel;

    @BeforeEach
    void setUp() {
        rmiServer = new RMIServerMock();
        screenModel = new ScreenModel(rmiServer);
    }

    @AfterEach
    void tearDown() {
        rmiServer = null;
        screenModel = null;
    }

    @Test
    void getScreenByIdFailTest() {
        assertNull(screenModel.getScreenById(-1));
    }

    @Test
    void getScreenByIdOneTest() {
        assertEquals(1, screenModel.getScreenById(1).getScreenId());
    }
    @Test
    void getScreenByIdZeroTest() {
        assertNull(screenModel.getScreenById(0));
    }
}