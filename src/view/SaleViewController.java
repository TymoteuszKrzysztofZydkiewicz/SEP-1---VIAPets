package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.*;

/**
 * Controller class for managing the Sale View.
 * Displays detailed information about a specific sale, including pet and customer details,
 * and provides actions to edit or close the view.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class SaleViewController {

    @FXML private Label pet;
    @FXML private Label petNameFieldInfo;
    @FXML private Label petBreedFieldInfo;
    @FXML private Label petAgeFieldInfo;
    @FXML private Label petColorFieldInfo;
    @FXML private Label petGenderFieldInfo;
    @FXML private Label breederNameFieldInfo;
    @FXML private Label speciesFieldInfo;
    @FXML private Label preferredFoodFieldInfo;
    @FXML private Label habitatFieldInfo;
    @FXML private Label doTheyBiteInfo;
    @FXML private Label isPredatorInfo;
    @FXML private Label dateInfo;
    @FXML private Label nameField;
    @FXML private Label phoneField;
    @FXML private Label streetField;
    @FXML private Label houseField;
    @FXML private Label cityField;
    @FXML private Label postCodeField;
    @FXML private Label priceField;

    private ViewHandler viewHandler;
    private Scene scene;
    private Sale viewedSale;

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
     * Sets the sale to be displayed and populates the view with its details.
     *
     * @param viewedSale the sale to view
     */
    public void setViewedSale(Sale viewedSale) {
        this.viewedSale = viewedSale;
        fillInInformation();
    }

    /**
     * Populates the labels with details of the sale being viewed.
     */
    private void fillInInformation() {
        pet.setText(viewedSale.getPet().getPetClassName());
        petNameFieldInfo.setText(viewedSale.getPetName());
        petAgeFieldInfo.setText(String.valueOf(viewedSale.getPet().getAge()));
        petColorFieldInfo.setText(viewedSale.getPet().getColor());
        petGenderFieldInfo.setText(viewedSale.getPet().getGender());
        breederNameFieldInfo.setText("");
        petBreedFieldInfo.setText("");
        speciesFieldInfo.setText("");
        doTheyBiteInfo.setText("");
        habitatFieldInfo.setText("");
        preferredFoodFieldInfo.setText("");
        isPredatorInfo.setText("");
        dateInfo.setText(viewedSale.getDate().toString());
        nameField.setText(viewedSale.getCustomerName());
        phoneField.setText(viewedSale.getCustomer().getPhoneNumber());
        streetField.setText(viewedSale.getCustomer().getAddress().getStreet());
        houseField.setText(viewedSale.getCustomer().getAddress().getHouseNumber());
        cityField.setText(viewedSale.getCustomer().getAddress().getCity());
        postCodeField.setText(viewedSale.getCustomer().getAddress().getPostalCode());
        priceField.setText(String.valueOf(viewedSale.getPrice()));

        switch (viewedSale.getPet().getPetClassName()) {
            case "Dog" -> {
                Dog dog = (Dog) viewedSale.getPet();
                breederNameFieldInfo.setText(dog.getBreederName());
                petBreedFieldInfo.setText(dog.getBreed());
            }
            case "Cat" -> {
                Cat cat = (Cat) viewedSale.getPet();
                breederNameFieldInfo.setText(cat.getBreederName());
                petBreedFieldInfo.setText(cat.getBreed());
            }
            case "Rodent" -> {
                Rodent rodent = (Rodent) viewedSale.getPet();
                speciesFieldInfo.setText(rodent.getSpecies());
                doTheyBiteInfo.setText(rodent.getDoTheyBite());
            }
            case "Fish" -> {
                Fish fish = (Fish) viewedSale.getPet();
                speciesFieldInfo.setText(fish.getSpecies());
                habitatFieldInfo.setText(fish.getHabitat());
                isPredatorInfo.setText(fish.getIsPredator());
            }
            case "Bird" -> {
                Bird bird = (Bird) viewedSale.getPet();
                speciesFieldInfo.setText(bird.getSpecies());
                preferredFoodFieldInfo.setText(bird.getPreferredFood());
            }
        }
    }

    /**
     * Handles the close action, returning to the "Sale List" tab in the TabPane.
     *
     * @param event
     */
    @FXML
    private void handleCloseAction(ActionEvent event) {
        TabPaneController tabPaneController = viewHandler.getTabPaneController();
        tabPaneController.selectTab("SaleList");
        viewHandler.openView("TabPane");
    }

    /**
     * Handles the edit action, opening the Sale Add/Edit view in edit mode with the sale's details.
     *
     * @param event
     */
    @FXML
    private void handleEditAction(ActionEvent event) {
        SaleAddEditController editController = viewHandler.getSaleAddEditController();
        editController.setEdit(true);
        editController.setEditingSale(viewedSale);
        viewHandler.openView("SaleAddEdit");
    }

    /**
     * Resets the view to reflect the current sale details.
     * Ensures the displayed information is up-to-date.
     */
    public void reset() {
        if (viewedSale != null) {
            fillInInformation();
        }
    }
}
