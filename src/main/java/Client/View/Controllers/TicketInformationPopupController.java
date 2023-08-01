package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IViewTicketPopupViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketInformationPopupController
{
    private IViewTicketPopupViewModel viewModel;
    private Stage window;
    private ViewHandler viewHandler; // Add the ViewHandler variable here

    @FXML
    private TextField inputField;

    @FXML
    private Button continueButton;

    public void init(IViewTicketPopupViewModel viewModel, Stage stage,ViewHandler viewHandler)
    {
        this.viewModel = viewModel;
        this.window = stage;
        this.viewHandler= viewHandler;
    }



    private void validateInput()
    {
        String input = inputField.getCharacters().toString();

        try
        {
            int ticketID = Integer.parseInt(input);
            continueButton.setDisable(ticketID < 1); // Update button disable state
        } catch (NumberFormatException e)
        {
            continueButton.setDisable(true);
        }
    }

    // Add this setter method to set the ViewHandler instance
    public void setViewHandler(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }

    // Rest of the class code...


    @FXML
    private void cancel(ActionEvent actionEvent)
    {
        // Close ticket info popup controller window
        window.close();
    }

    @FXML
    private void continueAction(ActionEvent actionEvent)
    {
        validateInput();
        if (continueButton.isDisabled())
        {
            return;
        }

        // Close ticket info popup controller window
        window.close();

        // Open ticket info window
        String input = inputField.getCharacters().toString();
        int ticketID = Integer.parseInt(input);
        viewModel.getTicket(ticketID);

        // Use the ViewHandler to open the ticket information stage
        // Assuming you have a method createViewTicketInfoStage(int ticketID) in the ViewHandler
        // that opens the ticket information stage based on the ticketID
        // Replace 'viewHandler' with the actual instance of your ViewHandler class
        viewHandler.openTicketInformation(viewModel.getTicket(ticketID));
    }

    @FXML
    private void inputChanged(ActionEvent actionEvent)
    {
        validateInput();
    }
}

