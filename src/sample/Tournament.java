package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Tournament {

    private StringProperty tournamentName;
    private SimpleObjectProperty<LocalDate> tournamentDate;
    private StringProperty sportName;
    private StringProperty fullWinnerName;
    private int tournamentPrizeSize;
    private double winnerEarnings;

    public Tournament(String tournamentName, LocalDate tournamentDate, String sportName, String fullWinnerName,
                      int tournamentPrizeSize, double winnerEarnings) {

        this.tournamentName = new SimpleStringProperty(tournamentName);
        this.tournamentDate = new SimpleObjectProperty<>(tournamentDate);
        this.sportName = new SimpleStringProperty(sportName);
        this.fullWinnerName = new SimpleStringProperty(fullWinnerName);
        this.tournamentPrizeSize = tournamentPrizeSize;
        this.winnerEarnings = winnerEarnings;
    }

    public String getTournamentName() {
        return tournamentName.get();
    }

    public LocalDate getTournamentDate() {
        return tournamentDate.get();
    }

    public String getSportName() {
        return sportName.get();
    }

    public int getTournamentPrizeSize() { return tournamentPrizeSize; }

    public String getFullWinnerName() {
        return fullWinnerName.get();
    }

    public double getWinnerEarnings() {
        return winnerEarnings;
    }
}
