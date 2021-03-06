
package edu.augustana.csc285.cormorant.ordertracker.gui;

import edu.augustana.csc285.cormorant.ordertracker.datamodel.ControlledVocab;
import edu.augustana.csc285.cormorant.ordertracker.datamodel.DataCollections;
import edu.augustana.csc285.cormorant.ordertracker.datamodel.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditPersonGUI extends Application {
	private ObservableList<String> oListCulture;
	private ObservableList<String> oListOccupation;

	@Override
	public void start(Stage primaryStage) {
		System.setProperty("glass.accessible.force", "false"); // Fixes bug of
																// combobox
																// crashing when
																// running on
																// certain
																// computers
		// Removes person from list
		DataCollections.getPersonCollection().remove(SearchResultGUI.getSelectedPerson());

		// GUI Variables
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid);
		Label nameLabel = new Label("Name:");
		Label nicknameLabel = new Label("Nickname:");
		Label genderLabel = new Label("Gender:");
		Label cultureLabel = new Label("Culture Identity:");
		Label occupationLabel = new Label("Occupation:");
		Label notesLabel = new Label("Notes:");
		TextField nameTextField = new TextField();
		TextField nicknameTextField = new TextField();
		ComboBox<String> cultureDropDown = new ComboBox<String>();
		TextArea notesTextArea = new TextArea();
		ComboBox<String> occupationDropDown = new ComboBox<String>();
		ComboBox<String> genderDropDown = new ComboBox<String>();
		Button addPersonButton = new Button("Done");
		Button backButton = new Button("Back");
		HBox notesLabelBox = new HBox();
		HBox buttonBox = new HBox();
		oListCulture = FXCollections.observableArrayList(ControlledVocab.getCultureVocab());
		oListOccupation = FXCollections.observableArrayList(ControlledVocab.getOccupationVocab());
		nameTextField.setText(SearchResultGUI.getSelectedPerson().getName());
		nicknameTextField.setText(SearchResultGUI.getSelectedPerson().getNickname());
		genderDropDown.setValue(SearchResultGUI.getSelectedPerson().getGender());
		cultureDropDown.setValue(SearchResultGUI.getSelectedPerson().getCulture());
		occupationDropDown.setValue(SearchResultGUI.getSelectedPerson().getOccupation());
		notesTextArea.setText(SearchResultGUI.getSelectedPerson().getNotes());

		// Grid Methods
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(nameLabel, 0, 0);
		grid.add(nameTextField, 1, 0);
		grid.add(nicknameLabel, 0, 1);
		grid.add(nicknameTextField, 1, 1);
		grid.add(genderLabel, 0, 2);
		grid.add(genderDropDown, 1, 2);
		grid.add(cultureLabel, 0, 3);
		grid.add(cultureDropDown, 1, 3);
		grid.add(occupationLabel, 0, 4);
		grid.add(occupationDropDown, 1, 4);
		grid.add(notesLabelBox, 0, 5);
		grid.add(notesTextArea, 1, 5);
		grid.add(backButton, 0, 6);
		grid.add(buttonBox, 1, 6);

		// Gender Methods
		genderDropDown.getItems().addAll("Male", "Female", "Unknown");
		genderDropDown.setMinSize(300, 10);

		// Culture Methods
		cultureDropDown.getItems().addAll(oListCulture);
		cultureDropDown.setMinSize(300, 10);

		// Occupation Methods
		occupationDropDown.getItems().addAll(oListOccupation);
		occupationDropDown.setMinSize(300, 10);

		// Notes Label Box
		notesLabelBox.getChildren().add(notesLabel);
		notesLabelBox.setAlignment(Pos.TOP_LEFT);
		notesTextArea.setMaxSize(300, 100);
		notesTextArea.setPromptText("Brief description of person...");

		// Name Text Field
		nameTextField.setPromptText("ex. John Doe");

		// Nick Name Text Field
		nicknameTextField.setPromptText("ex. Johny");

		// Add Person Buttons
		addPersonButton.setTextFill(Color.BLACK);
		addPersonButton.setStyle("-fx-base: #FFFFFF");
		addPersonButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id = SearchResultGUI.getSelectedPerson().getID();
				String name = nameTextField.getText();
				String nickname = (nicknameTextField.getText().matches(".*[a-zA-Z]+.*")) ? nicknameTextField.getText()
						: "No Nickname";
				String gender = (genderDropDown.getValue() != null) ? genderDropDown.getValue() : "Unknown";
				String culture = (cultureDropDown.getValue() != null) ? cultureDropDown.getValue() : "Unknown";
				String occupation = (occupationDropDown.getValue() != null) ? occupationDropDown.getValue() : "Unknown";
				String notes = (notesTextArea.getText() != null) ? notesTextArea.getText() : "none";
				if (name.matches(".*[a-zA-Z]+.*")) {
					Person person = new Person(id, name, nickname, gender, culture, occupation, notes);
					if (person.checkForUnallowedInput(person.getName(), person.getNickname()) < 0) {
						DialogGUI.showError("Invalid Characters Entered",
								"Make sure no numbers or special characters are entered in the Name, Culture or Occupation fields");
					} else if (DataCollections.checkForPersonDuplicates(person) > 0) {
						DialogGUI.showError("That person has already been entered.",
								"Person already exists. (ID number:" + DataCollections.checkForPersonDuplicates(person)
										+ ")");
					} else {
						DataCollections.addPerson(person);
						for (int i = 0; i < DataCollections.getInteractionCollection().size(); i++) {
							for (int j = 0; j < DataCollections.getInteractionCollection().get(i).getPeople1()
									.size(); j++) {
								if (DataCollections.getInteractionCollection().get(i).getPeople1().get(j)
										.equals(SearchResultGUI.getSelectedPerson())) {
									DataCollections.getInteractionCollection().get(i).getPeople1().get(j)
											.replacePerson(person);
								}
							}
							for (int j = 0; j < DataCollections.getInteractionCollection().get(i).getPeople2()
									.size(); j++) {
								if (DataCollections.getInteractionCollection().get(i).getPeople2().get(j)
										.equals(SearchResultGUI.getSelectedPerson())) {
									DataCollections.getInteractionCollection().get(i).getPeople2().get(j)
											.replacePerson(person);
								}
							}
						}
						SearchResultGUI searchGUI = new SearchResultGUI();
						searchGUI.start(primaryStage);
					}
				} else {
					DialogGUI.showError("No Letters in Name Field", "Name must contain a letter.");
				}
			}
		});

		// Back Button Methods
		backButton.setTextFill(Color.BLACK);
		backButton.setStyle("-fx-base: #FFFFFF");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				DataCollections.addPerson(SearchResultGUI.getSelectedPerson());
				SearchResultGUI searchGUI = new SearchResultGUI();
				searchGUI.start(primaryStage);
			}
		});

		// Button Box
		buttonBox.getChildren().add(addPersonButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);

		// Primary Stage Methods
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				String saveDialog = DialogGUI.saveConfirmation();
				if (saveDialog == "Save") {
					HomeGUI.savePerson();
					HomeGUI.saveInteractions();
					HomeGUI.saveCultureVocab();
					HomeGUI.saveOccupationVocab();
					HomeGUI.saveInteractionsType();
				} else if (saveDialog == "Cancel") {
					we.consume();
				}
			}
		});

		primaryStage.setTitle("Edit Person");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
