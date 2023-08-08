package Client.ViewModel;

import Client.Model.ITicketModel;
import Shared.Model.Ticket;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CancelTicketViewModel implements ICancelTicketViewModel {
    /**
     * The ticket model which handles ticket cancellations
     */
    private final ITicketModel ticketModel;
    private final StringProperty title;
    private final StringProperty info;
    public Ticket selectedTicket;

    public CancelTicketViewModel(ITicketModel ticketModel) {
        this.ticketModel = ticketModel;
        this.title = new SimpleStringProperty();
        this.info = new SimpleStringProperty();
    }

    @Override
    public void confirmTicketCancellation() {
        ticketModel.cancelTicket(selectedTicket);
        selectedTicket = null;
    }

    public void setTicket(Ticket ticket) {
        selectedTicket = ticket;
    }

    @Override
    public StringProperty title() {return title;}
    @Override
    public StringProperty info() {return info;}
}
