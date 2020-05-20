package sample;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller {

    private List<Tournament> mainTableData = new ArrayList<>();
    private List<Tournament> searchTableData = new ArrayList<>();
    private DOMparser parser;

    public void addTournamentToArray(String tournamentName, LocalDate tournamentDate, String sportName, String fullWinnerName,
                                  int tournamentPrizeSize, double winnerEarnings) {

        Tournament tournament = new Tournament(tournamentName, tournamentDate, sportName, fullWinnerName, tournamentPrizeSize, winnerEarnings);
        mainTableData.add(tournament);
    }

    public Page<Tournament> updateMainWindowTableView(int pageNumber, int recordsOnPageCount) {
        return createPage(pageNumber, recordsOnPageCount, mainTableData);
    }

    public Page<Tournament> createPage(int pageNumber, int recordsOnPageCount, List<Tournament> tableData) {
        int pageCount = 1;
        if (tableData.size() > recordsOnPageCount) {
            if (tableData.size() % recordsOnPageCount != 0) {
                pageCount = (tableData.size() - tableData.size() % recordsOnPageCount) / recordsOnPageCount + 1;
            }
            else {
                pageCount = (tableData.size() - tableData.size() % recordsOnPageCount) / recordsOnPageCount;
            }
        }

        List<Tournament> pageData;
        if (pageCount != pageNumber && pageCount != 0) {
            pageData = tableData.subList((pageNumber - 1) * recordsOnPageCount,
                                        (pageNumber - 1) * recordsOnPageCount + recordsOnPageCount);
        }
        else {
            pageData = tableData.subList((pageNumber - 1) * recordsOnPageCount,
                                            tableData.size());
        }

        Page<Tournament> page = new Page<>(pageNumber, pageData, pageCount, tableData.size());
        pageNumber = page.getPageNumber();
        return page;
    }

    public void saveTableData(File file, DOMparser parser) throws TransformerException, ParserConfigurationException {
        parser.parse(mainTableData, file);
    }

    public void getTableDataFromFile(File file, SAXParser parser) throws ParserConfigurationException, SAXException,
                                                                                                       IOException {
        SAXparser saxParser = new SAXparser();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        parser = factory.newSAXParser();
        parser.parse(file, saxParser);

        mainTableData = saxParser.getTournaments();
    }

    public void tournamentNameOrTournamentDate(String tournamentName, LocalDate tournamentData) {
        List<Tournament> searchResult = new ArrayList<>();

        for (Tournament tournament : mainTableData) {
            if (tournamentName.isEmpty()) {
                if (tournament.getTournamentDate().toString().equals(tournamentData.toString())) {
                    searchResult.add(tournament);
                }
            }
            else if (tournament.getFullWinnerName().equals(tournamentName)) {
                searchResult.add(tournament);
            }
        }
        searchTableData = searchResult;
    }

    public int tournamentNameOrTournamentDateDelete(String tournamentName, LocalDate tournamentData) {

        int deleteNumber = 0;
        for (Iterator<Tournament> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Tournament tournament = iterator.next();
            if (tournamentName.isEmpty()) {
                if (tournament.getTournamentDate().toString().equals(tournamentData.toString())) {
                    iterator.remove();
                    deleteNumber++;
                }
            }
            else if (tournament.getTournamentName().equals(tournamentName)) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public void sportNameOrWinnerName(String sportName, String winnerName) {
        List<Tournament> searchResult = new ArrayList<>();

        for (Tournament tournament : mainTableData) {
            if (winnerName.isEmpty()) {
                if (tournament.getSportName().contains(sportName)) {
                    searchResult.add(tournament);
                }
            }
            else if (tournament.getFullWinnerName().contains(winnerName)) {
                searchResult.add(tournament);
            }
        }
        searchTableData = searchResult;
    }

    public int sportNameOrWinnerNameDelete(String sportName, String winnerName) {

        int deleteNumber = 0;
        for (Iterator<Tournament> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Tournament tournament = iterator.next();
            if (tournament.getSportName().contains(sportName) && tournament.getFullWinnerName().contains(winnerName)) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public void tournamentPrizeSizeOrWinnerEarnings(int tournamentPrizeSize, int winnerEarningsFrom, int winnerEarningsTo) {
        List<Tournament> searchResult = new ArrayList<>();

        for (Tournament mainTableDatum : mainTableData) {
            if (mainTableDatum.getTournamentPrizeSize() == tournamentPrizeSize &&
                    mainTableDatum.getWinnerEarnings() >= winnerEarningsFrom && mainTableDatum.getWinnerEarnings() <= winnerEarningsTo) {
                searchResult.add(mainTableDatum);
            }
        }
        searchTableData = searchResult;
    }

    public int tournamentPrizeSizeOrWinnerEarningsDelete(int tournamentPrizeSize, int winnerEarningsFrom, int winnerEarningsTo) {
        int deleteNumber = 0;
        for (Iterator<Tournament> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Tournament tournament = iterator.next();
            if (tournament.getTournamentPrizeSize() == tournamentPrizeSize &&
                    tournament.getWinnerEarnings() >= winnerEarningsFrom && tournament.getWinnerEarnings() <= winnerEarningsTo) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public Page<Tournament> updateSearchWindowTable(int pageNumber, int recordsOnPageCount) {
        return createPage(pageNumber, recordsOnPageCount, searchTableData);
    }

    public void exit() {
        System.exit(0);
    }
}
