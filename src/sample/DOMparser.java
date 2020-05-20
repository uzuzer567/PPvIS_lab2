package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMparser {

    public void parse(List<Tournament> tableData, File file) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();
        Element docRootElement = document.createElement("tableData");

        for (int index = 0; index < tableData.size(); index++) {
            docRootElement.appendChild(addPlayerToDocument(index, tableData.get(index), document));
        }

        document.appendChild(docRootElement);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        saveDataInFile(source, result);
    }

    private Element addPlayerToDocument(int index, Tournament tournament, Document document) {

        Element tournamentItem = document.createElement("tournament");
        tournamentItem.setAttribute("id", Integer.toString(index));
        document.appendChild(tournamentItem);

        Element tournamentName = document.createElement("tournamentName");
        tournamentName.appendChild(document.createTextNode(tournament.getTournamentName()));
        tournamentItem.appendChild(tournamentName);

        Element tournamentDate = document.createElement("tournamentDate");
        tournamentDate.appendChild(document.createTextNode(tournament.getTournamentDate().toString()));
        tournamentItem.appendChild(tournamentDate);

        Element sportName = document.createElement("sportName");
        sportName.appendChild(document.createTextNode(tournament.getSportName()));
        tournamentItem.appendChild(sportName);

        Element receiptDate = document.createElement("fullWinnerName");
        receiptDate.appendChild(document.createTextNode(tournament.getFullWinnerName()));
        tournamentItem.appendChild(receiptDate);

        Element tournamentPrizeSize = document.createElement("tournamentPrizeSize");
        tournamentPrizeSize.appendChild(document.createTextNode(Integer.toString(tournament.getTournamentPrizeSize())));
        tournamentItem.appendChild(tournamentPrizeSize);

        Element winnerEarnings = document.createElement("winnerEarnings");
        winnerEarnings.appendChild(document.createTextNode(Double.toString(tournament.getWinnerEarnings())));
        tournamentItem.appendChild(winnerEarnings);

        return tournamentItem;
    }

    private void saveDataInFile(DOMSource source, StreamResult result) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }
}
