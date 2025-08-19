package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for managing the Add/Edit Pet view.
 * Handles user input for creating or editing pet details, including validation and saving data.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class PetAddEditController {

    @FXML private ComboBox<String> petComboBox;
    @FXML private TextField petNameField;
    @FXML private TextField petBreedField;
    @FXML private TextField petAgeField;
    @FXML private TextField petColorField;
    @FXML private TextField petGenderField;
    @FXML private TextField breederNameField;
    @FXML private TextField petPriceField;
    @FXML private TextField speciesField;
    @FXML private TextField preferredFoodField;
    @FXML private TextField habitatField;
    @FXML private TextField commentsField;

    @FXML private RadioButton kennelRadio;
    @FXML private RadioButton saleRadio;
    @FXML private RadioButton availabilityYes;
    @FXML private RadioButton availabilityNo;
    @FXML private RadioButton biteYes;
    @FXML private RadioButton biteNo;
    @FXML private RadioButton predatorYes;
    @FXML private RadioButton predatorNo;

    private Alert alert;
    private ViewHandler viewHandler;
    private Scene scene;
    private boolean edit;
    private Pet editingPet;

    /**
     * Initializes the controller with the necessary dependencies and sets up the pet type combo box.
     *
     * @param viewHandler the handler for switching views
     * @param scene the scene associated with this controller
     */
    public void init(ViewHandler viewHandler, Scene scene) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);

        setupPetComboBox();
    }

    /**
     * Retrieves the scene managed by this controller.
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Resets the form fields and state to their default values.
     */
    public void reset() {
        petComboBox.setValue("Dog");
        petNameField.clear();
        petBreedField.clear();
        petAgeField.clear();
        petColorField.clear();
        petGenderField.clear();
        breederNameField.clear();
        petPriceField.clear();
        speciesField.clear();
        preferredFoodField.clear();
        habitatField.clear();
        commentsField.clear();

        kennelRadio.setSelected(false);
        saleRadio.setSelected(false);
        availabilityYes.setSelected(false);
        availabilityNo.setSelected(false);
        biteYes.setSelected(false);
        biteNo.setSelected(false);
        predatorYes.setSelected(false);
        predatorNo.setSelected(false);

        edit = false;
        editingPet = null;
    }

    /**
     * Sets whether the controller is in edit mode.
     *
     * @param edit true for edit mode,  false for add mode
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    /**
     * Sets the pet to be edited and populates the form fields with its details.
     *
     * @param pet the pet to edit
     */
    public void setEditingPet(Pet pet) {
        this.reset();
        this.editingPet = pet;
        this.setEdit(true);
        if (edit) {
            setFields();
        }
    }

    /**
     * Configures the pet type combo box with available pet types.
     */
    private void setupPetComboBox() {
        petComboBox.setItems(FXCollections.observableArrayList("Dog", "Cat", "Rodent", "Bird", "Fish"));
        petComboBox.setValue("Dog");
    }

    /**
     * Populates the form fields with the details of the pet being edited.
     */
    private void setFields() {
        petColorField.setText(editingPet.getColor());
        petAgeField.setText(String.valueOf(editingPet.getAge()));
        petGenderField.setText(editingPet.getGender());
        petNameField.setText(editingPet.getName());
        commentsField.setText(editingPet.getComment());
        petComboBox.setValue(editingPet.getPetClassName());
        petPriceField.setText(String.valueOf(editingPet.getPrice()));

        kennelRadio.setSelected(editingPet.getTypeBoolean());
        saleRadio.setSelected(!editingPet.getTypeBoolean());
        availabilityYes.setSelected(editingPet.getAvailableBoolean());
        availabilityNo.setSelected(!editingPet.getAvailableBoolean());

        switch (editingPet.getPetClassName()) {
            case "Dog" -> {
                Dog dog = (Dog) editingPet;
                breederNameField.setText(dog.getBreederName());
                petBreedField.setText(dog.getBreed());
            }
            case "Cat" -> {
                Cat cat = (Cat) editingPet;
                breederNameField.setText(cat.getBreederName());
                petBreedField.setText(cat.getBreed());
            }
            case "Rodent" -> {
                Rodent rodent = (Rodent) editingPet;
                speciesField.setText(rodent.getSpecies());
                biteYes.setSelected(rodent.getDoTheyBiteBoolean());
                biteNo.setSelected(!rodent.getDoTheyBiteBoolean());
            }
            case "Fish" -> {
                Fish fish = (Fish) editingPet;
                speciesField.setText(fish.getSpecies());
                habitatField.setText(fish.getHabitat());
                predatorYes.setSelected(fish.getIsPredatorBoolean());
                predatorNo.setSelected(!fish.getIsPredatorBoolean());
            }
            case "Bird" -> {
                Bird bird = (Bird) editingPet;
                speciesField.setText(bird.getSpecies());
                preferredFoodField.setText(bird.getPreferredFood());
            }
        }
    }

    /**
     * Handles the cancel action, returning to the "Pet List" tab in the TabPane.
     *
     * @param event
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        TabPaneController tabPaneController = viewHandler.getTabPaneController();
        tabPaneController.selectTab("PetList");
        viewHandler.openView("TabPane");
    }

    /**
     * Handles the save action, validating and saving the pet data.
     * Adds a new pet or updates an existing pet based on the mode.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void savePetHandler(ActionEvent event) throws IOException {
        if (!petNameField.getText().isEmpty() && !petColorField.getText().isEmpty()) {
            double price = -1;
            boolean priceIsDouble = true;
            try {
                price = Double.parseDouble(petPriceField.getText());
            } catch (NumberFormatException e) {
                priceIsDouble = false;
            }

            int age = -1;
            boolean ageIsInteger = true;
            try {
                age = Integer.parseInt(petAgeField.getText());
            } catch (NumberFormatException e) {
                ageIsInteger = false;
            }

            if (age >= 0 && price >= 0 && ageIsInteger && priceIsDouble) {
                if ((edit && Objects.equals(editingPet.getPetClassName(), petComboBox.getValue()) &&
                        Objects.equals(editingPet.getName(), petNameField.getText())) ||
                        PetList.getPetByName(VIAPetsModelManager.getViaPets().getPetList().getPetsByClassName(petComboBox.getValue()), petNameField.getText()) == null) {

                    boolean available = availabilityYes.isSelected();
                    boolean type = kennelRadio.isSelected();
                    boolean doTheyBite = biteYes.isSelected();
                    boolean predator = predatorYes.isSelected();
                    Pet pet = null;

                    switch (petComboBox.getValue()) {
                        case "Dog" -> pet = new Dog(petColorField.getText(), age, petGenderField.getText(), petNameField.getText(), commentsField.getText(), type, price, available, breederNameField.getText(), petBreedField.getText());
                        case "Cat" -> pet = new Cat(petColorField.getText(), age, petGenderField.getText(), petNameField.getText(), commentsField.getText(), type, price, available, breederNameField.getText(), petBreedField.getText());
                        case "Rodent" -> pet = new Rodent(petColorField.getText(), age, petGenderField.getText(), petNameField.getText(), commentsField.getText(), type, price, available, speciesField.getText(), doTheyBite);
                        case "Fish" -> pet = new Fish(petColorField.getText(), age, petGenderField.getText(), petNameField.getText(), commentsField.getText(), type, price, available, predator, habitatField.getText(), speciesField.getText());
                        case "Bird" -> pet = new Bird(petColorField.getText(), age, petGenderField.getText(), petNameField.getText(), commentsField.getText(), type, price, available, preferredFoodField.getText(), speciesField.getText());
                    }

                    if (pet != null) {
                        if (!edit) {
                            VIAPetsModelManager.getViaPets().getPetList().addPet(pet);
                        } else {
                            VIAPetsModelManager.getViaPets().getPetList().editPet(editingPet, pet);
                        }
                        VIAPetsModelManager.updatePetsFile();
                    }

                    // Return to TabPane and select the "Pet List" tab
                    TabPaneController tabPaneController = viewHandler.getTabPaneController();
                    tabPaneController.selectTab("PetList");
                    viewHandler.openView("TabPane");
                } else {
                    alert.setContentText("Warning, there is already a " + petComboBox.getValue() + " named " + petNameField.getText());
                    alert.show();
                }
            } else {
                alert.setContentText("Warning, the entered data is incorrect");
                alert.show();
            }
        } else {
            alert.setContentText("Warning, the entered data is incorrect");
            alert.show();
        }
    }
}
