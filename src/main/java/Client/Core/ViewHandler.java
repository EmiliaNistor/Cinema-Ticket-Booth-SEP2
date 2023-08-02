package Client.Core;

import Client.View.Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    /**
     * Used to access view models necessary for controller initialization
     */
    private final ViewModelFactory viewModelFactory;

    // Scene related stuff
    private final Stage mainWindow;
    private final Parent movieList;
    //private final Stage ticketInfo;
    private Scene purchaseTicketScene;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;

        // Creating default scenes
        this.movieList = createMovieList();
        //this.ticketInfo = createViewTicketPopup();

        this.mainWindow = createMainWindow();
        mainWindow.show();
    }

    /**
     * Creates a window used to display the main contents of the application
     * @return Main window of the application
     */
    private Stage createMainWindow() {
        try {
            // Create a new stage for the window
            Stage stage = new Stage();
            stage.setTitle("Cinema Ticket Booth");
            stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            MainSceneController controller = fxmlLoader.getController();
            controller.init(this, viewModelFactory.getMainSceneViewModel());

            stage.setScene(scene);
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the movie list scene
     * @return Movie list scene
     */
    public Parent getMovieList() {
        return movieList;
    }

    /**
     * Creates a new movie list scene
     * @return Movie list scene
     */
    private Parent createMovieList() {
        try {
            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/movieList.fxml"));
            Parent root = fxmlLoader.load();
            //Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            MovieListController controller = fxmlLoader.getController();
            controller.init(this, viewModelFactory.getMovieListViewModel());

            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    OLD METHOD
    public Stage createViewTicketPopup() {
        try {
            // Create a new stage for the window
            Stage stage = new Stage();
            stage.setTitle("View Ticket Information");
            stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ticketInformationPopUp.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            TicketInformationPopupController controller = fxmlLoader.getController();
            controller.init(viewModelFactory.getViewTicketPopupViewModel(), stage);

            stage.setScene(scene);
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    */

    /**
     * Creates a new ticket information
     * @return Ticket Information scene
     */
    public Stage createViewTicketPopup() {
        try {
            // Create a new stage for the window
            Stage stage = new Stage();
            stage.setTitle("View Ticket Information");
            stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ticketInformationPopUp.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            TicketInformationPopupController controller = fxmlLoader.getController();
            controller.init(viewModelFactory.getViewTicketPopupViewModel(), stage, this);

            stage.setScene(scene);

            // Show the popup
            stage.show();

            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Opens the purchase ticket popup window
     */
    public void openPurchaseTicketPopup() {
        try {
            // Create a new stage for the window
            Stage stage = new Stage();
            stage.setTitle("View Ticket Information");
            stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/purchaseTicketPopUp.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            PurchaseTicketPopUpController controller = fxmlLoader.getController();
            controller.init(this, viewModelFactory.getPurchaseTicketPopUpViewModel());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSignUpWindow() {

        // Create a new stage for the SignUp window
        Stage stage = new Stage();
        stage.setTitle("Sign Up");
        stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal


        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/signUp.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Create the SignupController
            SignupController controller = fxmlLoader.getController();

            // Initialize the signupViewModel field in the controller
            controller.signupViewModel = viewModelFactory.getSignupViewModel();

            // Call the init method after setting the signupViewModel
            controller.init( viewModelFactory.getSignupViewModel());

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLogIn() {
        try {
            // Create a new stage for the window
            Stage stage = new Stage();
            stage.setTitle("Account Log In");
            stage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal

            // Create the scene from the window's fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/logIn.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Initializing values for the scene's controller
            LogInController controller = fxmlLoader.getController();
            controller.init(viewModelFactory.getLogInViewModel());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private Scene createMovieList() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the window
            Stage popupStage = new Stage();
            popupStage.setTitle("Cinema Ticket Booth");
            popupStage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal
            Scene scene = new Scene(root);

            //fxmlLoader.getController();
            popupStage.setScene(scene);
            popupStage.show();
        } catch (IOException e) {

        }
    }*/

    /*public void start() {
        Platform.runLater(() -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
                Parent root = fxmlLoader.load();

                // Create a new stage for the popup
                Stage popupStage = new Stage();
                popupStage.setTitle("Cinema Ticket Booth");
                popupStage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal
                Scene scene = new Scene(root);

                //fxmlLoader.getController();
                popupStage.setScene(scene);
                popupStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }*/

    /*public void openTicketInformation() {
        try {
            Parent root = loadFXML("/ticketInformation.fxml");

            stage.setTitle("Ticket Information");
            ticketInfoScene = new Scene(root);
            stage.setScene(ticketInfoScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }*/

}
