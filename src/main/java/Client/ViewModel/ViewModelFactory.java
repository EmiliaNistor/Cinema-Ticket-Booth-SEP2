package Client.ViewModel;


import Util.ModelFactory;

public class ViewModelFactory {

    private ModelFactory mf;
    private movieListViewModel movieListViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
        movieListViewModel = new movieListViewModel();
    }

    public movieListViewModel getMovieListViewModel() {
        return movieListViewModel;
    }

}