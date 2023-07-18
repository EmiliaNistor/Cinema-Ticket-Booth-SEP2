package Client.ViewModel;

import Client.Model.ModelFactory;

public class ViewModelFactory {

    private ModelFactory mf;
    private movieListViewModel movieListViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public movieListViewModel getMovieListViewModel() {
        return(movieListViewModel = movieListViewModel == null ?
                new movieListViewModel(mf.get);
    }
}
