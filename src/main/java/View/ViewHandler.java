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
    private Scene purchaseTicketScene;
    private Scene ticketInfoScene;

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
            stage.setScene(movieList);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openPurchaseWindow() {
        try {
            Parent root = loadFXML("Client/View/movieList/purchaseTicket.fxml");

            stage.setTitle("Purchase Ticket");
            purchaseTicketScene = new Scene(root);
            stage.setScene(purchaseTicketScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openTicketInformation() {
        try {
            Parent root = loadFXML("Client/View/movieList/ticketInformation.fxml");

            stage.setTitle("Ticket Information");
            ticketInfoScene = new Scene(root);
            stage.setScene(ticketInfoScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
