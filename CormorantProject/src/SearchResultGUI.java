import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

public class SearchResultGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Search Results");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Button btnEdit = new Button("Edit");
		btnEdit.setTextFill(Color.BLACK);
		btnEdit.setTextFill(Color.WHITE);
		btnEdit.setStyle("-fx-base: #FF0000");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.CENTER_RIGHT);
		hbBtn.getChildren().add(btnEdit);

		Button btnDelete = new Button("Delete");
		btnDelete.setTextFill(Color.WHITE);
		btnDelete.setStyle("-fx-base: #FF0000");
		btnDelete.setTextFill(Color.WHITE);
		hbBtn.getChildren().add(btnDelete);

		Button btnBack = new Button("Back");
		btnBack.setTextFill(Color.WHITE);
		btnBack.setStyle("-fx-base: #FF0000");
		hbBtn.getChildren().add(btnBack);
		grid.add(hbBtn, 1, 4);

		Scene scene = new Scene(grid, 1000, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}