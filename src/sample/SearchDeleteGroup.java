package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SearchDeleteGroup {

    private Group group = new Group();

    public SearchDeleteGroup(Controller controller, MainWindowTableGroup tableGroup, String searchOrDelete) {
        Label searchByLabel = new Label(searchOrDelete + " by");
        searchByLabel.setLayoutY(20);
        searchByLabel.setLayoutX(130);

        TextField firstSearchParameterInput = new TextField();
        firstSearchParameterInput.setLayoutX(150);
        firstSearchParameterInput.setLayoutY(70);

        TextField secondSearchParameterInput = new TextField();
        secondSearchParameterInput.setLayoutX(150);
        secondSearchParameterInput.setLayoutY(100);

        TextField thirdSearchParameterInput = new TextField();
        thirdSearchParameterInput.setLayoutX(150);
        thirdSearchParameterInput.setLayoutY(130);

        ObservableList<String> chooseSearchParametersValues = FXCollections.observableArrayList(
                "Tournament name or tournament date", "Sport name or winner name", "Tournament prize size or winner earnings"
        );

        ComboBox<String> chooseSearchParameters = new ComboBox<>(chooseSearchParametersValues);
        chooseSearchParameters.setLayoutY(40);
        chooseSearchParameters.setLayoutX(30);

        group.getChildren().addAll(searchByLabel, chooseSearchParameters);

        chooseSearchParameters.setOnAction(actionEvent -> {
            switch (chooseSearchParameters.getValue()) {
                case "Tournament name or tournament date":
                    Button tournamentNameOrTournamentDate = getButton(searchOrDelete);
                    DatePicker tournamentDate = getDatePickerForSearch();

                    firstSearchParameterInput.clear();
                    secondSearchParameterInput.clear();

                    group.getChildren().remove(2, group.getChildren().size());

                    group.getChildren().addAll(tournamentDate, secondSearchParameterInput,
                                               getFirstParameterLabel("Tournament Date"),
                                               getSecondParameterLabel("Tournament Name"), tournamentNameOrTournamentDate);

                    tournamentNameOrTournamentDate.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.tournamentNameOrTournamentDate(firstSearchParameterInput.getText(), tournamentDate.getValue());
                        }
                        else {
                            int deleteNumber = controller.tournamentNameOrTournamentDateDelete(firstSearchParameterInput.getText(), tournamentDate.getValue());

                            Stage stage = (Stage) tournamentNameOrTournamentDate.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }
                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });

                    break;
                case "Sport name or winner name":
                    firstSearchParameterInput.clear();
                    secondSearchParameterInput.clear();

                    Button sportNameOrWinnerName = getButton(searchOrDelete);

                    group.getChildren().remove(2, group.getChildren().size());
                    group.getChildren().addAll(firstSearchParameterInput, secondSearchParameterInput,
                            getFirstParameterLabel("Sport name"),
                            getSecondParameterLabel("Winner name"), sportNameOrWinnerName);

                    sportNameOrWinnerName.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.sportNameOrWinnerName(firstSearchParameterInput.getText(), secondSearchParameterInput.getText());
                        }
                        else {
                            int deleteNumber = controller.sportNameOrWinnerNameDelete(firstSearchParameterInput.getText(), secondSearchParameterInput.getText());

                            Stage stage = (Stage) sportNameOrWinnerName.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }

                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });
                    break;

                case "Tournament prize size or winner earnings":
                    firstSearchParameterInput.clear();
                    secondSearchParameterInput.clear();
                    Button tournamentPrizeSizeOrWinnerEarnings = getButton(searchOrDelete);

                    group.getChildren().remove(2, group.getChildren().size());
                    group.getChildren().addAll(getFirstParameterLabel("Prize size"), firstSearchParameterInput);
                    group.getChildren().addAll(getSecondParameterLabel("Winner earnings from"), secondSearchParameterInput);
                    group.getChildren().addAll(thirdSearchParameterInput, getThirdParameterLabel("to"), tournamentPrizeSizeOrWinnerEarnings);

                    tournamentPrizeSizeOrWinnerEarnings.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.tournamentPrizeSizeOrWinnerEarnings(Integer.parseInt(firstSearchParameterInput.getText()),
                                    Integer.parseInt(secondSearchParameterInput.getText()), Integer.parseInt(thirdSearchParameterInput.getText()));
                            tableGroup.updateTable();
                        }
                        else {
                            int deleteNumber = controller.tournamentPrizeSizeOrWinnerEarningsDelete(Integer.parseInt(firstSearchParameterInput.getText()),
                                    Integer.parseInt(secondSearchParameterInput.getText()), Integer.parseInt(thirdSearchParameterInput.getText()));

                            Stage stage = (Stage) tournamentPrizeSizeOrWinnerEarnings.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }

                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });

                    break;
            }
        });
    }

    private Label getFirstParameterLabel(String parameterName) {
        Label label = new Label(parameterName);
        label.setLayoutY(70);
        label.setLayoutX(30);
        return label;
    }

    private Label getSecondParameterLabel(String parameterName) {
        Label label = new Label(parameterName);
        label.setLayoutY(100);
        label.setLayoutX(30);
        return label;
    }

    private Label getThirdParameterLabel(String parameterName) {
        Label label = new Label(parameterName);
        label.setLayoutY(130);
        label.setLayoutX(31);
        return label;
    }

    private DatePicker getDatePickerForSearch() {

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(2020, 5, 6));
        datePicker.setLayoutX(130);
        datePicker.setLayoutY(70);

        return datePicker;
    }

    private Button getButton(String buttonText) {

        Button button = new Button(buttonText);
        button.setLayoutY(170);
        button.setLayoutX(130);

        return button;
    }

    public Group getGroup() {
        return group;
    }

    private void showDeleteInformation(int numOfDeleted) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Information");

        if (numOfDeleted != 0) {
            alert.setContentText(Integer.toString(numOfDeleted) + " record(s) have been deleted");
        }
        else {
            alert.setContentText("No records found!");
        }

        alert.showAndWait();
    }
}
