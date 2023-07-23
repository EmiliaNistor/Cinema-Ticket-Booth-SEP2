package Client.Core;


import Client.ViewModel.MovieListViewModel;
import Client.ViewModel.PurchaseTicketViewModel;
import Client.ViewModel.TicketInformationViewModel;


public class ViewModelFactory {

    private ModelFactory mf;
    private MovieListViewModel movieListViewModel;
    private PurchaseTicketViewModel purchaseTicketViewModel;

    private TicketInformationViewModel ticketInformationViewModel;
    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
        movieListViewModel = new MovieListViewModel();
        purchaseTicketViewModel = new PurchaseTicketViewModel();
        ticketInformationViewModel = new TicketInformationViewModel();
    }

    public MovieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

    public PurchaseTicketViewModel getPurchaseTicketViewModel(){return purchaseTicketViewModel;}

    public TicketInformationViewModel getTicketInformationViewModel(){return ticketInformationViewModel;}

}