package Client.ViewModel;

import Client.Core.ModelFactory;
import Client.Core.ViewModelFactory;
import Client.Model.AccountModel;
import Client.Model.IAccountModel;
import Mocks.RMIServerMock;
import Shared.Network.IRMIServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainSceneViewModelTest {

    private MainSceneViewModel viewModel;
    private IRMIServer IRMIServer;
    private IAccountModel accountModel;

    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();
        IAccountModel accountModel = new AccountModel(IRMIServer);
        this.accountModel = accountModel;

        viewModel = new MainSceneViewModel(null, accountModel);
    }

    @AfterEach
    void tearDown() {
        IRMIServer = null;
        viewModel = null;
        accountModel = null;
    }

    @Test
    void initialStateTest() {
        assertFalse(viewModel.loggedInProperty().get());
        assertFalse(viewModel.administratorProperty().get());
        assertEquals("Log In", viewModel.logInTextStringProperty().get());
        assertTrue(viewModel.movieListVisible().get());
        assertFalse(viewModel.ticketInfoVisible().get());
    }

    //Combined test for Login and Logout
    @Test
    void logInAndOutTest() {
        assertFalse(viewModel.isLoggedIn());

        accountModel.logIn("yes", "yes"); // mock always returns true
        assertTrue(viewModel.loggedInProperty().get());
        assertFalse(viewModel.administratorProperty().get());
        assertEquals("Log Out", viewModel.logInTextStringProperty().get());

        viewModel.logOut();
        assertFalse(viewModel.loggedInProperty().get());
        assertFalse(viewModel.administratorProperty().get());
        assertEquals("Log In", viewModel.logInTextStringProperty().get());
    }

    //Combined Test for changing scenes
    @Test
    void changeToMovieListAndTicketInfoTest() {
        assertTrue(viewModel.movieListVisible().get());
        assertFalse(viewModel.ticketInfoVisible().get());

        viewModel.changeToTicketInfo();
        assertFalse(viewModel.movieListVisible().get());
        assertTrue(viewModel.ticketInfoVisible().get());

        viewModel.changeToMovieList();
        assertTrue(viewModel.movieListVisible().get());
        assertFalse(viewModel.ticketInfoVisible().get());
    }
    
}

