package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.layout.FlowPane;

import java.time.LocalDate;

public class AddTournamentWindow {

    private Controller controller;
    private MainWindowTableGroup tableGroup;

    public AddTournamentWindow(MainWindowTableGroup tableGroup, Stage primaryStage, Controller controller) {
        this.controller = controller;
        this.tableGroup = tableGroup;

        HBox layout = getWindowLayout();

        Scene secondScene = new Scene(layout);
        Stage newWindow = new Stage();
        newWindow.setTitle("Information");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(primaryStage);

        newWindow.show();
    }

    public HBox getWindowLayout() {

        VBox addingStage = new VBox();
        addingStage.setPadding(new Insets(10, 20, 20, 20));
        addingStage.setSpacing(10);

        VBox dateBox = new VBox();
        dateBox.setPadding(new Insets(10, 20, 20, 20));
        dateBox.setSpacing(10);

        Label textTournamentName = new Label("Tournament name");
        TextField tournamentName = new TextField();
        tournamentName.setMinWidth(300);
        addingStage.getChildren().addAll(textTournamentName, tournamentName);

        Label textTournamentDate = new Label("Date of tournament");
        DatePicker tournamentDate = new DatePicker(LocalDate.of(2020, 5, 6));
        dateBox.getChildren().addAll(textTournamentDate, tournamentDate);

        Label textSportName = new Label("Sport name");
        addingStage.getChildren().add(textSportName);
        ObservableList<String> sport = FXCollections.observableArrayList("Бег", "Прыжки", "Прыжки в высоту", "Прыжки с шестом",
                "Стрельба из лука", "Метание копья", "Метание молота");
        ComboBox<String> comboBox = new ComboBox<String>(sport);
        comboBox.setValue("Бег");
        FlowPane cBox = new FlowPane(10, 10, comboBox);
        addingStage.getChildren().addAll(cBox);

        Label textFullWinnerName = new Label("Winner name");
        TextField fullWinnerName = new TextField();
        fullWinnerName.setMinWidth(300);
        addingStage.getChildren().addAll(textFullWinnerName, fullWinnerName);

        Label textTournamentPrizeSize = new Label("Tournament Prize Size");
        TextField tournamentPrizeSize = new TextField();
        tournamentPrizeSize.setMinWidth(300);
        addingStage.getChildren().addAll(textTournamentPrizeSize, tournamentPrizeSize);

        Button addInfo = new Button("Enter");
        addingStage.getChildren().add(addInfo);

        HBox adding = new HBox(addingStage, dateBox);

        addInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tournamentName.getText().isEmpty() || tournamentPrizeSize.getText().isEmpty() ||
                        fullWinnerName.getText().isEmpty() || tournamentPrizeSize.getText().isEmpty()) {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Warning");
                    warning.setContentText("Some fields are empty.");
                    warning.showAndWait();
                    return;
                }
                controller.addTournamentToArray(tournamentName.getText(), tournamentDate.getValue(), comboBox.getValue(), fullWinnerName.getText(),
                        Integer.parseInt(tournamentPrizeSize.getText()), 0.6 * Integer.parseInt(tournamentPrizeSize.getText()));

                tableGroup.updateTable();
                Stage stage = (Stage) addInfo.getScene().getWindow();
                stage.close();
            }
        });

        return adding;
    }
}
