package Client.ViewModel;

import Client.Core.ViewModelFactory;
import Client.Model.ITicketModel;
import Shared.Model.Ticket;

public class ViewTicketPopupViewModel implements IViewTicketPopupViewModel
{
    private final ITicketModel ticketModel;
    private final ViewModelFactory vmf;

    public ViewTicketPopupViewModel(ViewModelFactory vmf, ITicketModel tm)
    {
        this.vmf = vmf;
        ticketModel = tm;
    }

    @Override
    public boolean openTicketInfo(int ticketID)
    {
        Ticket ticket = ticketModel.getTicket(ticketID);
        if (ticket == null) {
            return false;
        }

        vmf.getTicketInformationViewModel().setCurrentTicket(ticket);
        vmf.getMainSceneViewModel().changeToTicketInfo();
        return true;
    }
}
