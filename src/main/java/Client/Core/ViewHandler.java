package Client.Core;

import Client.View.Controllers.MovieListController;
import Client.View.Controllers.TicketInformationPopupController;
import Client.View.Controllers.ViewController;
import Client.ViewModel.ViewTicketPopupViewModel;
import javafx.application.Platform;
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
        Platform.runLater(() -> {
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource("/movieList.fxml"));

            try {
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 800, 600);
                primaryStage.setTitle("Hello!");

                // set up controller's internal values
                MovieListController controller = fxmlLoader.getController();
                controller.init(this);

                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public ViewTicketPopupViewModel getViewTicketPopupViewModel() {
        return vmf.getViewTicketPopupViewModel();
    }

    public void openMovieList() {
        try {
            Parent root = loadFXML("/movieList.fxml");

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
            Parent root = loadFXML("/purchaseTicket.fxml");

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
            Parent root = loadFXML("/ticketInformation.fxml");

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
