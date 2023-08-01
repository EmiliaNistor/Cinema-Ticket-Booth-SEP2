package Client.Core;


import Client.ViewModel.*;
import Shared.Model.Movie;


public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private final MovieListViewModel movieListViewModel;
    private final PurchaseTicketPopUpViewModel purchaseTicketPopUpViewModel;
    private final TicketInformationViewModel ticketInformationViewModel;
    private final CancelTicketViewModel cancelTicketViewModel;
    private final ViewTicketPopupViewModel viewTicketPopupViewModel;

    private final SignupViewModel signupViewModel; // Add the SignupViewModel

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        movieListViewModel = new MovieListViewModel(this, modelFactory.getMovieListModel());
        purchaseTicketPopUpViewModel = new PurchaseTicketPopUpViewModel(
                modelFactory.getMovieListModel(), modelFactory.getScreenModel(), modelFactory.getTicketModel()
        );
        ticketInformationViewModel = new TicketInformationViewModel();
        cancelTicketViewModel = new CancelTicketViewModel(modelFactory.getTicketModel());
        viewTicketPopupViewModel = new ViewTicketPopupViewModel(modelFactory.getTicketModel());
        signupViewModel = new SignupViewModel(modelFactory.getSignupModel()); // Create an instance of SignupViewModel

    }

    public MovieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

    public PurchaseTicketPopUpViewModel getPurchaseTicketPopUpViewModel(){return purchaseTicketPopUpViewModel;}

    public TicketInformationViewModel getTicketInformationViewModel(){return ticketInformationViewModel;}

    public ViewTicketPopupViewModel getViewTicketPopupViewModel() {
        return viewTicketPopupViewModel;
    }

    public SignupViewModel getSignupViewModel() {
        return signupViewModel; // Return the SignupViewModel instance
    }



    /**
     * Updates the purchase ticket popup's movie information
     * @param movie The movie to use
     */
    public void updatePurchaseTicketMovie(Movie movie) {
        purchaseTicketPopUpViewModel.setMovie(movie);
    }
}