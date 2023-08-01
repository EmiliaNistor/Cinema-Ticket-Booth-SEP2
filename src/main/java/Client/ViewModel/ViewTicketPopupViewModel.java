package Client.ViewModel;

import Client.Model.ITicketModel;
import Shared.Model.Ticket;

public class ViewTicketPopupViewModel implements IViewTicketPopupViewModel
{
    private final ITicketModel ticketModel;

    public ViewTicketPopupViewModel(ITicketModel tm)
    {
        ticketModel = tm;
    }

    /**
     * Open the view containing information about the ticket
     *
     * @param ticketID Ticket ID whose information to show
     */
    @Override
    public Ticket getTicket(int ticketID)
    {
        Ticket ticket = ticketModel.getTicket(ticketID);
        System.out.println(ticketID+"vm");
        return ticket;
    }
}
