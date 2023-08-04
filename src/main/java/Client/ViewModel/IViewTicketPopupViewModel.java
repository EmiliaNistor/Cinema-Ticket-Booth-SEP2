package Client.ViewModel;


public interface IViewTicketPopupViewModel {
    /**
     * Open the view containing information about the ticket
     * @param ticketID Ticket ID whose information to show
     * @return True if successful
     */
    boolean openTicketInfo(int ticketID);
}
