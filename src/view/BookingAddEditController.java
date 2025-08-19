package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Controller class for the Booking Add/Edit view in the application.
 * Handles the creation and editing of kennel bookings by interacting
 * with the model and updating the view.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 *
 */
public class BookingAddEditController {

    @FXML private ComboBox<String> petComboBox;
    @FXML private ComboBox<String> petNameComboBox;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField priceField;
    @FXML private ComboBox<String> customerComboBox;

    private ViewHandler viewHandler;
    private Scene scene;
    private boolean edit;
    private KennelBooking editingBooking;
    private ArrayList<Pet> filteredPets;
    private boolean initialValues = true;
    private Alert alert;

    /**
     * Initializes the controller with the necessary view handler and scene.
     * Sets up UI elements such as combo boxes and alert dialogs.
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
        alert.setContentText("Warning, the entered data is incorrect");

        setUpComboBoxes();
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
     * Sets whether the controller is in edit mode or add mode.
     *
     * @param edit  true for edit mode, false for add mode
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    /**
     * Sets the booking being edited and populates the fields if in edit mode.
     *
     * @param booking the booking to edit
     */
    public void setEditingBooking(KennelBooking booking) {
        this.editingBooking = booking;
        if (edit) {
            setFields();
        }
    }

    /**
     * Populates the form fields with the details of the booking being edited.
     */
    private void setFields() {
        petComboBox.setValue(editingBooking.getPetClass());
        customerComboBox.setValue(editingBooking.getCustomerName());
        petUpdated(null);

        priceField.setText(String.valueOf(editingBooking.getPrice()));
        Date startDateEdited = editingBooking.getStartDate();
        startDate.setValue(LocalDate.of(startDateEdited.getYear(), startDateEdited.getMonth(), startDateEdited.getDay()));
        Date endDateEdited = editingBooking.getEndDate();
        endDate.setValue(LocalDate.of(endDateEdited.getYear(), endDateEdited.getMonth(), endDateEdited.getDay()));
    }

    /**
     * Sets up the combo boxes for pet types and customer names.
     * Initializes their values based on available data.
     */
    private void setUpComboBoxes() {
        petComboBox.setItems(FXCollections.observableArrayList("Dog", "Cat", "Rodent", "Bird", "Fish"));
        petComboBox.setValue("Dog");

        ArrayList<String> customerNames = new ArrayList<>();
        for (Customer customer : VIAPetsModelManager.getViaPets().getCustomerList().getCustomers()) {
            customerNames.add(customer.getName());
        }
        if (customerNames.isEmpty()) {
            customerComboBox.setItems(FXCollections.observableArrayList("No customers found"));
            customerComboBox.setValue("No customers found");
        } else {
            customerComboBox.setItems(FXCollections.observableArrayList(customerNames));
            customerComboBox.setValue(customerNames.get(0));
        }

        petUpdated(null);
    }

    /**
     * Handles the save action, validating and saving the booking data.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleSaveAction(ActionEvent event) throws IOException {
        if (startDate.getValue() != null && endDate.getValue() != null && !petNameComboBox.getValue().equals("No pets found")) {
            double price = -1;
            boolean priceIsDouble = true;
            try {
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                priceIsDouble = false;
            }

            Date startDateConverted = new Date(startDate.getValue().getDayOfMonth(), startDate.getValue().getMonthValue(), startDate.getValue().getYear());
            Date endDateConverted = new Date(endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear());

            if (startDateConverted.isBefore(endDateConverted) && priceIsDouble && price >= 0 && !customerComboBox.getValue().equals("No customers found")) {
                KennelBooking booking = new KennelBooking(
                        VIAPetsModelManager.getViaPets().getCustomerList().getCustomerByName(customerComboBox.getValue()),
                        PetList.getPetByName(filteredPets, petNameComboBox.getValue()),
                        startDateConverted, endDateConverted, price
                );

                if (!edit) {
                    if (VIAPetsModelManager.getViaPets().getBookingList().addBooking(booking)) {
                        VIAPetsModelManager.updateBookingsFile();
                        viewHandler.openView("TabPane");
                    } else {
                        alert.show();
                    }
                } else {
                    if (VIAPetsModelManager.getViaPets().getBookingList().editBooking(editingBooking, booking)) {
                        VIAPetsModelManager.updateBookingsFile();
                        viewHandler.openView("TabPane");
                    } else {
                        alert.show();
                    }
                }
            } else {
                alert.show();
            }
        } else {
            alert.show();
        }
    }

    /**
     * Handles the cancel action, returning to the previous view.
     *
     * @param event
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        viewHandler.openView("TabPane");
    }

    /**
     * Updates the pet combo box based on the selected pet type.
     * Filters pets available for kennel booking and updates the pet name combo box.
     *
     * @param event
     */
    @FXML
    private void petUpdated(ActionEvent event) {
        filteredPets = PetList.filterPets(VIAPetsModelManager.getViaPets().getPetList().getPetsByClassName(petComboBox.getValue()), "Kennel");
        if (!filteredPets.isEmpty()) {
            ArrayList<String> petNames = new ArrayList<>();
            for (Pet pet : filteredPets) {
                petNames.add(pet.getName());
            }

            petNameComboBox.setItems(FXCollections.observableArrayList(petNames));
            if (edit && initialValues) {
                petNameComboBox.setValue(editingBooking.getPetName());
                initialValues = false;
            } else {
                petNameComboBox.setValue(petNames.get(0));
            }
        } else {
            petNameComboBox.setItems(FXCollections.observableArrayList("No pets found"));
            petNameComboBox.setValue("No pets found");
        }
    }

    /**
     * Resets the form to its initial state.
     * Clears fields and reinitializes combo boxes for adding a new booking.
     */
    public void reset() {
        setUpComboBoxes();
        if (edit) {
            setFields();
        } else {
            priceField.clear();
            startDate.setValue(null);
            endDate.setValue(null);
        }
    }
}
