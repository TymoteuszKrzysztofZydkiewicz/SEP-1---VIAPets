package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.*;

/**
 * Controller class for managing the Booking View.
 * Displays details of a specific booking, including pet and customer information,
 * and provides actions to edit or close the view.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class BookingViewController {

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
    @FXML private Label startDateInfo;
    @FXML private Label endDateInfo;
    @FXML private Label nameField;
    @FXML private Label phoneField;
    @FXML private Label streetField;
    @FXML private Label houseField;
    @FXML private Label cityField;
    @FXML private Label postCodeField;
    @FXML private Label priceField;

    private ViewHandler viewHandler;
    private Scene scene;
    private KennelBooking viewedBooking;

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
     * Sets the booking to be displayed and populates the view with booking details.
     *
     * @param viewedBooking the booking to view
     */
    public void setViewedBooking(KennelBooking viewedBooking) {
        this.viewedBooking = viewedBooking;
        fillInInformation();
    }

    /**
     * Populates the labels with booking, pet, and customer details.
     */
    private void fillInInformation() {
        pet.setText(viewedBooking.getPet().getPetClassName());
        petNameFieldInfo.setText(viewedBooking.getPetName());
        petAgeFieldInfo.setText(String.valueOf(viewedBooking.getPet().getAge()));
        petColorFieldInfo.setText(viewedBooking.getPet().getColor());
        petGenderFieldInfo.setText(viewedBooking.getPet().getGender());
        breederNameFieldInfo.setText("");
        petBreedFieldInfo.setText("");
        speciesFieldInfo.setText("");
        doTheyBiteInfo.setText("");
        habitatFieldInfo.setText("");
        preferredFoodFieldInfo.setText("");
        isPredatorInfo.setText("");
        startDateInfo.setText(viewedBooking.getStartDate().toString());
        endDateInfo.setText(viewedBooking.getEndDate().toString());
        nameField.setText(viewedBooking.getCustomerName());
        phoneField.setText(viewedBooking.getCustomer().getPhoneNumber());
        streetField.setText(viewedBooking.getCustomer().getAddress().getStreet());
        houseField.setText(viewedBooking.getCustomer().getAddress().getHouseNumber());
        cityField.setText(viewedBooking.getCustomer().getAddress().getCity());
        postCodeField.setText(viewedBooking.getCustomer().getAddress().getPostalCode());
        priceField.setText(String.valueOf(viewedBooking.getPrice()));

        // Populate additional pet details
        switch (viewedBooking.getPet().getPetClassName()) {
            case "Dog":
                Dog dog = (Dog) viewedBooking.getPet();
                breederNameFieldInfo.setText(dog.getBreederName());
                petBreedFieldInfo.setText(dog.getBreed());
                break;
            case "Cat":
                Cat cat = (Cat) viewedBooking.getPet();
                breederNameFieldInfo.setText(cat.getBreederName());
                petBreedFieldInfo.setText(cat.getBreed());
                break;
            case "Rodent":
                Rodent rodent = (Rodent) viewedBooking.getPet();
                speciesFieldInfo.setText(rodent.getSpecies());
                doTheyBiteInfo.setText(rodent.getDoTheyBite());
                break;
            case "Fish":
                Fish fish = (Fish) viewedBooking.getPet();
                speciesFieldInfo.setText(fish.getSpecies());
                habitatFieldInfo.setText(fish.getHabitat());
                isPredatorInfo.setText(fish.getIsPredator());
                break;
            case "Bird":
                Bird bird = (Bird) viewedBooking.getPet();
                speciesFieldInfo.setText(bird.getSpecies());
                preferredFoodFieldInfo.setText(bird.getPreferredFood());
                break;
        }
    }

    /**
     * Handles the close action, returning to the main TabPane view.
     *
     * @param event
     */
    @FXML
    private void handleCloseAction(ActionEvent event) {
        viewHandler.openView("TabPane");
    }

    /**
     * Handles the edit action, opening the Booking Add/Edit view in edit mode.
     *
     * @param event
     */
    @FXML
    private void handleEditAction(ActionEvent event) {
        BookingAddEditController editController = viewHandler.getBookingAddEditController();
        editController.setEdit(true);
        editController.setEditingBooking(viewedBooking);
        editController.reset(); // Reset form fields if needed
        viewHandler.openView("BookingAddEdit");
    }

    /**
     * Optional reset method for re-initializing view data.
     * Ensures the displayed information is updated with the current booking details.
     */
    public void reset() {
        if (viewedBooking != null) {
            fillInInformation();
        }
    }
}
