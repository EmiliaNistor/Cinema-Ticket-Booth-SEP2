package Client.View.Controllers;

import Client.Core.ViewModelFactory;
import Client.Core.ViewHandler;

public interface ViewController {
    void init(ViewHandler vh, ViewModelFactory vmf);
}
