package Client.Model;

import Mocks.RMIServerMock;
import Shared.Network.IRMIServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountModelTest {
    private IRMIServer rmiServer;
    private IAccountModel accountModel;

    @BeforeEach
    void setUp() {
        rmiServer = new RMIServerMock();
        accountModel = new AccountModel(rmiServer);
    }

    @AfterEach
    void tearDown() {
        rmiServer = null;
        accountModel = null;
    }

    @Test
    void signupNullTest() {
        assertFalse(accountModel.signup(null, null));
    }

    @Test
    void signupZeroTest() {
        assertFalse(accountModel.signup("", ""));
    }

    @Test
    void signupTest() {
        assertTrue(accountModel.signup("123", "123"));
    }

    @Test
    void logInNullTest() {
        assertFalse(accountModel.logIn(null, null));
    }

    @Test
    void logInZeroTest() {
        assertFalse(accountModel.logIn("", ""));
    }

    @Test
    void logInTest() {
        assertTrue(accountModel.logIn("123", "123"));
    }
}