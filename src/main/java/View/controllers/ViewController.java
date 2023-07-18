package View.controllers;

import Client.ViewModel.ViewModelFactory;
import View.ViewHandler;

public interface ViewController {
    void init(ViewHandler vh, ViewModelFactory vmf);
}
