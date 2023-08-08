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
    private ModelFactory ModelFactory;

    @BeforeEach
    void setUp() {
        IRMIServer = new RMIServerMock();
        ViewModelFactory viewModelFactory = new ViewModelFactory(ModelFactory);
        IAccountModel accountModel = new AccountModel(IRMIServer);

        viewModel = new MainSceneViewModel(viewModelFactory, accountModel);
    }

    @AfterEach
    void tearDown() {
        IRMIServer = null;
        ModelFactory = null;
        viewModel = null;
    }

    @Test
    void testInitialState() {
        assertFalse(viewModel.loggedInProperty().get());
        assertFalse(viewModel.administratorProperty().get());
        assertEquals("Log In", viewModel.logInTextStringProperty().get());
        assertTrue(viewModel.movieListVisible().get());
        assertFalse(viewModel.ticketInfoVisible().get());
    }

    //Combined test for Login and Logout
    @Test
    void testLogInAndOut() {
        assertFalse(viewModel.isLoggedIn());

        viewModel.loggedInProperty();
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
    void testChangeToMovieListAndTicketInfo() {
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

