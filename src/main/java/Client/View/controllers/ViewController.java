package Client.View.controllers;

import Client.core.ViewModelFactory;
import Client.core.ViewHandler;

public interface ViewController {
    void init(ViewHandler vh, ViewModelFactory vmf);
}
