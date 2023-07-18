package View;

import Client.ViewModel.ViewModelFactory;
import View.controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Scene movieList;

    private Stage stage;

    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() {
        stage = new Stage();
        openMovieList();
    }

    public void openMovieList() {
        try {
            Parent root = loadFXML("Client/View/movieList/movieList.fxml");

            stage.setTitle("Movie List");
            movieList = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(movieList);
        stage.show();
    }

    public Parent loadFXML(String path) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
