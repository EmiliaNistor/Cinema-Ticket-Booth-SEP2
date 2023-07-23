package Client.Core;


import Client.ViewModel.movieListViewModel;
import Client.ViewModel.purchaseTicketViewModel;
import Client.ViewModel.ticketInformationViewModel;


public class ViewModelFactory {

    private ModelFactory mf;
    private Client.ViewModel.movieListViewModel movieListViewModel;
    private Client.ViewModel.purchaseTicketViewModel purchaseTicketViewModel;

    private Client.ViewModel.ticketInformationViewModel ticketInformationViewModel;
    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
        movieListViewModel = new movieListViewModel();
        purchaseTicketViewModel = new purchaseTicketViewModel();
        ticketInformationViewModel = new ticketInformationViewModel();
    }

    public movieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

    public purchaseTicketViewModel getPurchaseTicketViewModel(){return purchaseTicketViewModel;}

    public ticketInformationViewModel getTicketInformationViewModel(){return ticketInformationViewModel;}

}