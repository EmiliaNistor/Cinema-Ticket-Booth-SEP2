package Client.Core;


import Client.ViewModel.*;


public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private final MovieListViewModel movieListViewModel;
    private final PurchaseTicketPopUpViewModel purchaseTicketPopUpViewModel;
    private final TicketInformationViewModel ticketInformationViewModel;
    private final CancelTicketViewModel cancelTicketViewModel;
    private final ViewTicketPopupViewModel viewTicketPopupViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        movieListViewModel = new MovieListViewModel(modelFactory.getMovieListModel());
        purchaseTicketPopUpViewModel = new PurchaseTicketPopUpViewModel();
        ticketInformationViewModel = new TicketInformationViewModel();
        cancelTicketViewModel = new CancelTicketViewModel(modelFactory.getTicketModel());
        viewTicketPopupViewModel = new ViewTicketPopupViewModel(modelFactory.getTicketModel());
    }

    public MovieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

    public PurchaseTicketPopUpViewModel getPurchaseTicketViewModel(){return purchaseTicketPopUpViewModel;}

    public TicketInformationViewModel getTicketInformationViewModel(){return ticketInformationViewModel;}

    public ViewTicketPopupViewModel getViewTicketPopupViewModel() {
        return viewTicketPopupViewModel;
    }
}