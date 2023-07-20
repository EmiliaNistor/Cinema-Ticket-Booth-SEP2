package Util;


import Util.ModelFactory;
import ViewModel.movieListViewModel;
import ViewModel.purchaseTicketViewModel;
import ViewModel.ticketInformationViewModel;

public class ViewModelFactory {

    private ModelFactory mf;
    private ViewModel.movieListViewModel movieListViewModel;
    private ViewModel.purchaseTicketViewModel purchaseTicketViewModel;

    private ViewModel.ticketInformationViewModel ticketInformationViewModel;
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