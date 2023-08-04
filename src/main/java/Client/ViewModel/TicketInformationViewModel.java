package Client.ViewModel;

import Client.Core.ViewModelFactory;
import Client.Model.IMenuModel;
import Client.Model.IMovieListModel;
import Client.Model.IScreenModel;
import Client.Model.ITicketModel;
import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Ticket;
import javafx.beans.property.*;

public class TicketInformationViewModel implements ITicketInformationViewModel {
    private Ticket currentTicket;
    private final ITicketModel ticketModel;
    private final IMovieListModel movieListModel;
    private final IScreenModel screenModel;
    private final IMenuModel menuModel;
    private final ViewModelFactory vmf;
    private final StringProperty title;
    private final StringProperty movieName;
    private final StringProperty date;
    private final StringProperty startTime;
    private final StringProperty endTime;
    private final StringProperty length;
    private final StringProperty seatInfo;
    private final StringProperty chosenMenu;

    public TicketInformationViewModel(
            ViewModelFactory vmf, ITicketModel ticketModel, IMovieListModel movieListModel, IScreenModel screenModel, IMenuModel menuModel
    ) {
        this.vmf = vmf;
        this.ticketModel = ticketModel;
        this.movieListModel = movieListModel;
        this.screenModel = screenModel;
        this.menuModel = menuModel;

        title = new SimpleStringProperty("Ticket [Missing info]");
        movieName = new SimpleStringProperty("Missing info");
        date = new SimpleStringProperty("Missing info");
        startTime = new SimpleStringProperty("Missing info");
        endTime = new SimpleStringProperty("Missing info");
        length = new SimpleStringProperty("Missing info");
        seatInfo = new SimpleStringProperty("Missing info");
        chosenMenu = new SimpleStringProperty("Missing info");
    }

    public void setCurrentTicket(Ticket ticket) {
        this.currentTicket = ticket;

        if (currentTicket == null) {
            title.setValue("Ticket [Missing info]");
            movieName.setValue("Missing info");
            date.setValue("Missing info");
            startTime.setValue("Missing info");
            endTime.setValue("Missing info");
            length.setValue("Missing info");
            seatInfo.setValue("Missing info");
            chosenMenu.setValue("Missing info");
            return;
        }

        title.setValue(String.format("Ticket [%d]", ticket.getId()));
        seatInfo.setValue(String.format("%s - %d", ticket.getSeat().getRow(), ticket.getSeat().getNumber()));
        // movie data
        Movie movie = movieListModel.getMovieById(ticket.getMovieId());
        movieName.setValue(movie.getName());
        date.setValue(movie.getDate().toString());
        startTime.setValue(movie.getStartTime().toString());
        endTime.setValue(movie.getEndTime().toString());
        length.setValue(movie.getLength()+"");
        // menu data
        if (ticket.getMenuId() < 1) {
            chosenMenu.setValue("None");
            return;
        }

        Menu menu = menuModel.getMenuById(ticket.getMenuId());
        chosenMenu.setValue(menu.getFood());
    }

    @Override
    public void openCancelTicket() {
        if (currentTicket != null) {
            ticketModel.cancelTicket(currentTicket);
            setCurrentTicket(null);
        }
    }

    @Override
    public StringProperty title() {
        return title;
    }

    @Override
    public StringProperty movieName() {
        return movieName;
    }

    @Override
    public StringProperty date() {
        return date;
    }

    @Override
    public StringProperty startTime() {
        return startTime;
    }

    @Override
    public StringProperty endTime() {
        return endTime;
    }

    @Override
    public StringProperty length() {
        return length;
    }

    @Override
    public StringProperty seatInfo() {
        return seatInfo;
    }

    @Override
    public StringProperty chosenMenu() {
        return chosenMenu;
    }
}
