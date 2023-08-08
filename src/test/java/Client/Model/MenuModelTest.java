package Client.Model;

import Mocks.RMIServerMock;
import Shared.Model.Menu;
import Shared.Network.IRMIServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuModelTest {
    private IRMIServer rmiServer;
    private IMenuModel menuModel;

    @BeforeEach
    void setUp() {
        rmiServer = new RMIServerMock();
        menuModel = new MenuModel(rmiServer);
    }

    @AfterEach
    void tearDown() {
        rmiServer = null;
        menuModel = null;
    }

    @Test
    void getMenuByIdFailTest() {
        Menu returnedMenu = menuModel.getMenuById(-1);
        assertNull(returnedMenu);
    }

    @Test
    void getMenuByIdZeroTest() {
        Menu returnedMenu = menuModel.getMenuById(0);
        assertEquals(0, returnedMenu.getMenuId());
    }

    @Test
    void getMenuByIdOneTest() {
        Menu returnedMenu = menuModel.getMenuById(1);
        assertEquals(1, returnedMenu.getMenuId());
    }
}