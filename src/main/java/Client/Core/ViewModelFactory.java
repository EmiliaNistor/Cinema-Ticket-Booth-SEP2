package Client.Core;


import Client.ViewModel.*;


public class ViewModelFactory {
    private final ModelFactory mf;
    private final MovieListViewModel movieListViewModel;
    private final PurchaseTicketViewModel purchaseTicketViewModel;
    private final TicketInformationViewModel ticketInformationViewModel;
    private final CancelTicketViewModel cancelTicketViewModel;
    private final ViewTicketPopupViewModel viewTicketPopupViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
        movieListViewModel = new MovieListViewModel();
        purchaseTicketViewModel = new PurchaseTicketViewModel();
        ticketInformationViewModel = new TicketInformationViewModel();
        cancelTicketViewModel = new CancelTicketViewModel(mf.getTicketModel());
        viewTicketPopupViewModel = new ViewTicketPopupViewModel(mf.getTicketModel());
    }

    public MovieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

    public PurchaseTicketViewModel getPurchaseTicketViewModel(){return purchaseTicketViewModel;}

    public TicketInformationViewModel getTicketInformationViewModel(){return ticketInformationViewModel;}

    public ViewTicketPopupViewModel getViewTicketPopupViewModel() {
        return viewTicketPopupViewModel;
    }
}