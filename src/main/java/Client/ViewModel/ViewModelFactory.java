package Client.ViewModel;


import Model.Movie;
import Util.ModelFactory;

public class ViewModelFactory {

    private ModelFactory mf;
    private movieListViewModel movieListViewModel;
    private purchaseTicketViewModel purchaseTicketViewModel;

    private ticketInformationViewModel ticketInformationViewModel;
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