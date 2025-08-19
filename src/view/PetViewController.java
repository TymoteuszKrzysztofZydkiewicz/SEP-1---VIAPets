package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.*;

/**
 * Controller class for managing the Pet View.
 * Displays detailed information about a specific pet and provides actions
 * to edit or return to the pet list view.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class PetViewController {

    @FXML private Label pet;
    @FXML private Label petNameFieldInfo;
    @FXML private Label petBreedFieldInfo;
    @FXML private Label petAgeFieldInfo;
    @FXML private Label petColorFieldInfo;
    @FXML private Label petGenderFieldInfo;
    @FXML private Label commentFieldInfo;
    @FXML private Label petPriceFieldInfo;
    @FXML private Label breederNameFieldInfo;
    @FXML private Label speciesFieldInfo;
    @FXML private Label preferredFoodFieldInfo;
    @FXML private Label habitatFieldInfo;
    @FXML private Label doTheyBiteInfo;
    @FXML private Label isPredatorInfo;
    @FXML private Label availabilityInfo;
    @FXML private Label petTypeInfo;

    private ViewHandler viewHandler;
    private Scene scene;
    private Pet viewedPet;

    /**
     * Initializes the controller with the necessary dependencies.
     *
     * @param viewHandler the handler for switching views
     * @param scene the scene associated with this controller
     */
    public void init(ViewHandler viewHandler, Scene scene) {
        this.viewHandler = viewHandler;
        this.scene = scene;
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
     * Sets the pet to be displayed and populates the view with its details.
     *
     * @param viewedPet the pet to view
     */
    public void setViewedPet(Pet viewedPet) {
        this.viewedPet = viewedPet;
        fillInInformation();
    }

    /**
     * Populates the labels with details of the pet being viewed.
     */
    private void fillInInformation() {
        pet.setText(viewedPet.getPetClassName());
        petNameFieldInfo.setText(viewedPet.getName());
        petAgeFieldInfo.setText(String.valueOf(viewedPet.getAge()));
        petColorFieldInfo.setText(viewedPet.getColor());
        petGenderFieldInfo.setText(viewedPet.getGender());
        petPriceFieldInfo.setText(String.valueOf(viewedPet.getPrice()));
        commentFieldInfo.setText(viewedPet.getComment());
        availabilityInfo.setText(viewedPet.getAvailable());
        petTypeInfo.setText(viewedPet.getType());
        breederNameFieldInfo.setText("");
        petBreedFieldInfo.setText("");
        speciesFieldInfo.setText("");
        doTheyBiteInfo.setText("");
        habitatFieldInfo.setText("");
        preferredFoodFieldInfo.setText("");
        isPredatorInfo.setText("");

        switch (viewedPet.getPetClassName()) {
            case "Dog" -> {
                Dog dog = (Dog) viewedPet;
                breederNameFieldInfo.setText(dog.getBreederName());
                petBreedFieldInfo.setText(dog.getBreed());
            }
            case "Cat" -> {
                Cat cat = (Cat) viewedPet;
                breederNameFieldInfo.setText(cat.getBreederName());
                petBreedFieldInfo.setText(cat.getBreed());
            }
            case "Rodent" -> {
                Rodent rodent = (Rodent) viewedPet;
                speciesFieldInfo.setText(rodent.getSpecies());
                doTheyBiteInfo.setText(rodent.getDoTheyBite());
            }
            case "Fish" -> {
                Fish fish = (Fish) viewedPet;
                speciesFieldInfo.setText(fish.getSpecies());
                habitatFieldInfo.setText(fish.getHabitat());
                isPredatorInfo.setText(fish.getIsPredator());
            }
            case "Bird" -> {
                Bird bird = (Bird) viewedPet;
                speciesFieldInfo.setText(bird.getSpecies());
                preferredFoodFieldInfo.setText(bird.getPreferredFood());
            }
        }
    }

    /**
     * Handles the close action, returning to the "Pet List" tab in the TabPane.
     *
     * @param event
     */
    @FXML
    private void handleCloseAction(ActionEvent event) {
        TabPaneController tabPaneController = viewHandler.getTabPaneController();
        tabPaneController.selectTab("PetList");
        viewHandler.openView("TabPane");
    }

    /**
     * Handles the edit action, opening the Pet Add/Edit view in edit mode with the pet's details.
     *
     * @param event
     */
    @FXML
    private void handleEditAction(ActionEvent event) {
        PetAddEditController editController = viewHandler.getPetAddEditController();
        editController.setEdit(true);
        editController.setEditingPet(viewedPet);
        viewHandler.openView("PetAddEdit");
    }

    /**
     * Resets the view to reflect the current pet details.
     * Ensures the displayed information is up-to-date.
     */
    public void reset() {
        if (viewedPet != null) {
            fillInInformation();
        }
    }
}
