package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteWindow {

    public DeleteWindow(Stage primaryStage, Controller controller, MainWindowTableGroup tableGroup) {

        SearchDeleteGroup deleteGroup = new SearchDeleteGroup(controller, tableGroup, "Delete");

        BorderPane layout = new BorderPane();

        layout.setCenter(deleteGroup.getGroup());

        Scene scene = new Scene(layout, 400, 200);

        Stage stage = new Stage();
        stage.setTitle("Delete tournaments");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        stage.show();
    }

}
