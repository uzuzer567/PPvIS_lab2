package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SAXparser extends DefaultHandler {

    private List<Tournament> tournaments = new ArrayList<>();
    private String thisElement = "";
    private String tournamentName = "";
    private LocalDate tournamentDate;
    private String sportName = "";
    private String  fullWinnerName = "";
    private int tournamentPrizeSize = 0;
    private double winnerEarnings = 0;
    private int readCounter = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (thisElement) {
            case "tournamentName":
                tournamentName = new String(ch, start, length);
                break;
            case "tournamentDate":
                String tournamentDateString = new String(ch, start, length);
                tournamentDate = LocalDate.parse(tournamentDateString);
                break;
            case "sportName":
                sportName = new String(ch, start, length);
                break;
            case "fullWinnerName":
                fullWinnerName = new String(ch, start, length);
                break;
            case "tournamentPrizeSize":
                String tournamentPrizeSizeString = new String(ch, start, length);
                tournamentPrizeSize = Integer.parseInt(tournamentPrizeSizeString);
                break;
            case "winnerEarnings":
                String winnerEarningsString = new String(ch, start, length);
                winnerEarnings = Double.parseDouble(winnerEarningsString);
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (!thisElement.equals("tableDate")) {
            readCounter++;
        }
        if (readCounter == 7) {
            tournaments.add(new Tournament(tournamentName, tournamentDate, sportName, fullWinnerName, tournamentPrizeSize, winnerEarnings));
            readCounter = 0;
        }
        thisElement = "";
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }
}
