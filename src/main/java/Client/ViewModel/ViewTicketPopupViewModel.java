package Client.ViewModel;

import Client.Model.ITicketModel;
import Shared.Model.Ticket;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewTicketPopupViewModel implements IViewTicketPopupViewModel {
    private final ITicketModel ticketModel;

    public ViewTicketPopupViewModel(ITicketModel tm) {
        ticketModel = tm;
    }

    /**
     * Open the view containing information about the ticket
     * @param ticketID Ticket ID whose information to show
     */
    @Override
    public void openTicketInfoView(int ticketID) {
        Ticket ticket = ticketModel.getTicket(ticketID);
        if (ticket == null) {
            return; // no ticket found
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ticketInformationPopUp.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Ticket Information");
            popupStage.initModality(Modality.APPLICATION_MODAL); // This makes the popup window modal
            Scene scene = new Scene(root);

            //fxmlLoader.getController();
            popupStage.setScene(scene);
            popupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
