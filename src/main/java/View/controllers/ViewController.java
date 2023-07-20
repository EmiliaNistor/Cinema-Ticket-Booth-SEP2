package View.controllers;

import Util.ViewModelFactory;
import View.ViewHandler;

public interface ViewController {
    void init(ViewHandler vh, ViewModelFactory vmf);
}
