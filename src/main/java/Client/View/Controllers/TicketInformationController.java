package Client.View.Controllers;

import Shared.Model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TicketInformationController {

    @FXML
    private Label ticketIdLabel;

    @FXML
    private Label seatLabel;

    @FXML
    private Label movieLabel;

    @FXML
    private Label screenLabel;

    private Ticket ticket;

    public void init(Ticket ticket)
    {
        setTicket(ticket);
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        updateLabels();
    }

    private void updateLabels() {
        if (ticket != null) {
            ticketIdLabel.setText("Ticket ID: " + ticket.getId());
            seatLabel.setText("Seat: " + ticket.getSeat());
            movieLabel.setText("Movie: " + ticket.getMovie().getName());
        } else {
            ticketIdLabel.setText("Ticket ID: N/A");
            seatLabel.setText("Seat: N/A");
            movieLabel.setText("Movie: N/A");
            screenLabel.setText("Screen: N/A");
        }
    }
}
