
package edu.augustana.csc285.cormorant.ordertracker.gui;

import edu.augustana.csc285.cormorant.ordertracker.datamodel.CSVUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HomeGUI extends Application {
	private ComboBox<String> searchType;
	private static String typeOfSearch;
	private static String searchKey;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// GUI Variables
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid);
		searchType = new ComboBox<String>();
		TextField searchTextField = new TextField();
		Image imageSearch = new Image("search_icon.png");
		Image imagePerson = new Image("person_icon.png");
		Image imageInteraction = new Image("interaction_icon.png");
		Image imageEdit = new Image("edit_icon.png");
		ImageView imageSearchView = new ImageView(imageSearch);
		ImageView imagePersonView = new ImageView(imagePerson);
		ImageView imageInteractionView = new ImageView(imageInteraction);
		ImageView imageEditView = new ImageView(imageEdit);
		Button searchButton = new Button("Search", imageSearchView);
		imageSearchView.setFitHeight(15);
		imageSearchView.setFitWidth(15);
		Button insertPersonButton = new Button("Insert Person", imagePersonView);
		imagePersonView.setFitHeight(20);
		imagePersonView.setFitWidth(20);
		Button insertInteractionButton = new Button("Insert Interaction", imageInteractionView);
		imageInteractionView.setFitHeight(20);
		imageInteractionView.setFitWidth(20);
		Button editVocabButton = new Button("Edit Controlled Vocabulary", imageEditView);
		imageEditView.setFitHeight(20);
		imageEditView.setFitWidth(20);
		HBox topRowBox = new HBox();
		HBox bottomButtonRowBox = new HBox(10);

		// Grid Methods
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// DropDown list for choosing search type
		searchType.getItems().addAll("Person", "Interaction");
		searchType.setPromptText("Search Category");
		System.setProperty("glass.accessible.force", "false");// Fixes bug of
																// crashing
																// combobox

		// Search Text Field Methods
		searchTextField.setMinSize(325, 10);
		searchTextField.setMaxSize(325, 50);
		searchTextField.setPromptText("Search Query");

		// Search Button Methods
		searchButton.setTextFill(Color.WHITE);
		searchButton.setStyle("-fx-base: #FF0000");
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (searchType.getValue() != null) {
					SearchResultGUI searchGUI = new SearchResultGUI();
					typeOfSearch = searchType.getValue();
					searchKey = searchTextField.getText();
					searchGUI.start(primaryStage);
				}
			}
		});

		// Top Row Box Methods
		topRowBox.setAlignment(Pos.CENTER_RIGHT);
		topRowBox.getChildren().add(searchType);
		topRowBox.getChildren().add(searchTextField);
		topRowBox.getChildren().add(searchButton);

		// Insert Person Button Methods
		insertPersonButton.setTextFill(Color.BLACK);
		insertPersonButton.setAlignment(Pos.CENTER_LEFT);
		insertPersonButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddPersonGUI gui = new AddPersonGUI();
				try {
					gui.start(primaryStage);
				} catch (Exception error) {
					DialogGUI.showError("Error Changing to Add Person View", error.toString());
				}
			}
		});

		// Insert Interaction Button Methods
		insertInteractionButton.setAlignment(Pos.CENTER);
		insertInteractionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddInteractionGUI gui = new AddInteractionGUI();
				try {
					gui.start(primaryStage);
				} catch (Exception error) {
					DialogGUI.showError("Error Changing to Add Interaction View", error.toString());
				}
			}
		});

		// Edit Vocab Button Methods
		editVocabButton.setTextFill(Color.BLACK);
		editVocabButton.setAlignment(Pos.CENTER_RIGHT);
		editVocabButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				EditVocabGUI gui = new EditVocabGUI();
				gui.start(primaryStage);
			}
		});

		// Bottom Button Row Box
		bottomButtonRowBox.getChildren().add(insertPersonButton);
		bottomButtonRowBox.getChildren().add(insertInteractionButton);
		bottomButtonRowBox.getChildren().add(editVocabButton);

		// Adds Boxes to grid for display
		grid.add(topRowBox, 1, 0);
		grid.add(bottomButtonRowBox, 1, 1);

		// Primary Stage Methods
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				CSVUtil.savePerson();
				CSVUtil.saveInteractions();
			}
		});
		primaryStage.setTitle("Home Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static String getType() {
		return typeOfSearch;
	}

	public static String getSearchKey() {
		return searchKey;
	}

}
