import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditPersonGUI extends Application {
	
	public void start(Stage primaryStage, DataCollections personList, Person editPerson) {
		System.setProperty("glass.accessible.force", "false"); // Fixes bug of combobox crashing when running on certain computers
		//Removes person from list
		personList.getPersonCollection().remove(editPerson);
		// GUI Variables
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 500, 400);
		Label nameLabel = new Label("Name:");
		Label genderLabel = new Label("Gender:");
		Label cultureLabel = new Label("Culture Identity:");
		Label occupationLabel = new Label("Occupation:");
		Label notesLabel = new Label("Notes:");
		TextField nameTextField = new TextField();
		TextField cultureTextField = new TextField();
		TextArea notesTextArea = new TextArea();
		TextField occupationTextField = new TextField();
		ComboBox<String> genderDropDown = new ComboBox<String>();
		Button addPersonButton = new Button("Done");
		Button backButton = new Button("Back");
		HBox notesLabelBox = new HBox();
		HBox buttonBox = new HBox();
		
		nameTextField.setText(editPerson.getName());
		genderDropDown.setValue(editPerson.getGender());
		cultureTextField.setText(editPerson.getCulture());
		occupationTextField.setText(editPerson.getOccupation());
		notesTextArea.setText(editPerson.getNotes());
		 
		// Grid Methods
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(nameLabel, 0, 0);
		grid.add(nameTextField, 1, 0);
		grid.add(genderLabel, 0, 1);
		grid.add(genderDropDown, 1, 1);
		grid.add(cultureLabel, 0, 2);
		grid.add(cultureTextField, 1, 2);
		grid.add(occupationLabel, 0, 3);
		grid.add(occupationTextField, 1, 3);
		grid.add(notesLabelBox, 0, 4);
		grid.add(notesTextArea, 1, 4);
		grid.add(addPersonButton, 0, 6);
		grid.add(buttonBox, 1, 6);

		//Gender Methods
		genderDropDown.getItems().addAll("Male", "Female", "Unknown");
		genderDropDown.setMinSize(300, 10);

		//Notes Label Box
		notesLabelBox.getChildren().add(notesLabel);
		notesLabelBox.setAlignment(Pos.TOP_LEFT);
		notesTextArea.setMaxSize(300, 100);
		
		//Add Person Buttons
		addPersonButton.setTextFill(Color.BLACK);
		addPersonButton.setTextFill(Color.WHITE);
		addPersonButton.setStyle("-fx-base: #FF0000");
		addPersonButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id = editPerson.getID();
				String name=nameTextField.getText();
				String gender=genderDropDown.getValue();
				String culture=cultureTextField.getText();
				String occupation=occupationTextField.getText();
				String notes=notesTextArea.getText();
				if (name.isEmpty() && gender == null && culture.isEmpty() && occupation.isEmpty()&& notes.isEmpty()) {
					
				} else {
					
					Person person = new Person(id, name, gender, culture, occupation, notes);
					if (person.checkForUnallowedInput(person.getName(), person.getCulture(), person.getCulture()) < 0) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Error");
						alert.setHeaderText("Invalid characters entered.");
						alert.setContentText(
								"Make sure no numbers or special characters are entered in the Name, Culture or Occupation fields");
						alert.showAndWait();
					} else if (personList.checkForPersonDuplicates(person) > 0) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Error");
						alert.setHeaderText("That person has already been entered.");
						alert.setContentText("Person already exists. (ID number:"
								+ personList.checkForPersonDuplicates(person) + ")");
						alert.showAndWait();
					} else {
						personList.addPerson(person);
						SearchResultGUI searchGUI=new SearchResultGUI();
						searchGUI.start(primaryStage, "Person", personList);
					}
				}
			}
		});

		//Back Button Methods
		backButton.setTextFill(Color.WHITE);
		backButton.setStyle("-fx-base: #FF0000");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				personList.addPerson(editPerson);
				SearchResultGUI searchGUI=new SearchResultGUI();
				searchGUI.start(primaryStage, "Person", personList);
			}
		});
		
		//Button Box
		buttonBox.getChildren().add(backButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);


		// Primary Stage Methods
		primaryStage.setTitle("Edit Person");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}