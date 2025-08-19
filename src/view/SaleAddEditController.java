package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller class for managing the Add/Edit Sale view.
 * Handles user input for creating or editing sale records, including validation and data persistence.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class SaleAddEditController {

    @FXML private ComboBox<String> petComboBox;
    @FXML private ComboBox<String> petNameComboBox;
    @FXML private DatePicker date;
    @FXML private TextField priceField;
    @FXML private ComboBox<String> customerComboBox;

    private ViewHandler viewHandler;
    private Scene scene;
    private boolean edit;
    private Sale editingSale;
    private ArrayList<Pet> filteredPets;
    private boolean initialValues = true;
    private Alert alert;

    /**
     * Initializes the controller with the necessary dependencies and sets up the combo boxes.
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
        setupComboBoxes();
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
        priceField.clear();
        date.setValue(null);
        petComboBox.setValue("Dog");
        setupComboBoxes();
        petUpdated(null);
        edit = false; // Default to add mode
        editingSale = null;
        initialValues = true;
    }

    /**
     * Sets whether the controller is in edit mode.
     *
     * @param edit true for edit mode, false for add mode
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    /**
     * Sets the sale to be edited and populates the form fields with its details.
     *
     * @param sale the sale to edit
     */
    public void setEditingSale(Sale sale) {
        this.editingSale = sale;
        if (edit) {
            setFields();
        }
    }

    /**
     * Configures the pet type and customer combo boxes with available options.
     */
    private void setupComboBoxes() {
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
     * Populates the form fields with details of the sale being edited.
     */
    private void setFields() {
        petComboBox.setValue(editingSale.getPet().getPetClassName());
        customerComboBox.setValue(editingSale.getCustomerName());
        petUpdated(null);
        priceField.setText(String.valueOf(editingSale.getPrice()));
        Date dateEdited = editingSale.getDate();
        date.setValue(LocalDate.of(dateEdited.getYear(), dateEdited.getMonth(), dateEdited.getDay()));
    }

    /**
     * Handles the save action, validating and saving the sale data.
     * Adds a new sale or updates an existing sale based on the mode.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleSaveAction(ActionEvent event) throws IOException {
        if (date.getValue() != null && !petNameComboBox.getValue().equals("No pets found")) {
            double price = -1;
            boolean priceIsDouble = true;
            try {
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                priceIsDouble = false;
            }

            if (priceIsDouble && price >= 0) {
                Date dateConverted = new Date(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear());
                Pet pet = PetList.getPetByName(filteredPets, petNameComboBox.getValue());
                pet.setAvailable(false);
                Sale sale = new Sale(VIAPetsModelManager.getViaPets().getCustomerList().getCustomerByName(customerComboBox.getValue()), pet, price, dateConverted);

                if (!edit) {
                    VIAPetsModelManager.getViaPets().getSaleList().addSale(sale);
                } else {
                    VIAPetsModelManager.getViaPets().getSaleList().editSale(editingSale, sale);
                }

                VIAPetsModelManager.updateSalesFile();
                VIAPetsModelManager.updatePetsFile();

                // Return to TabPane and select the Sales tab
                TabPaneController tabPaneController = viewHandler.getTabPaneController();
                tabPaneController.selectTab("SaleList");
                viewHandler.openView("TabPane");
            } else {
                alert.show();
            }
        } else {
            alert.show();
        }
    }

    /**
     * Handles the cancel action, returning to the "Sales" tab in the TabPane.
     *
     * @param event
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        TabPaneController tabPaneController = viewHandler.getTabPaneController();
        tabPaneController.selectTab("SaleList");
        viewHandler.openView("TabPane");
    }

    /**
     * Updates the pet name combo box based on the selected pet type.
     * Filters pets available for sale and updates the combo box with their names.
     *
     * @param event
     */
    @FXML
    private void petUpdated(ActionEvent event) {
        filteredPets = PetList.filterPets(
                VIAPetsModelManager.getViaPets().getPetList().getPetsByClassName(petComboBox.getValue()), "For sale");
        filteredPets = PetList.filterAvailablePets(filteredPets);

        if (edit && petComboBox.getValue().equals(editingSale.getPet().getPetClassName()) && !filteredPets.contains(editingSale.getPet())) {
            filteredPets.add(editingSale.getPet());
        }

        ArrayList<String> petNames = new ArrayList<>();
        for (Pet pet : filteredPets) {
            petNames.add(pet.getName());
        }

        if (!petNames.isEmpty()) {
            petNameComboBox.setItems(FXCollections.observableArrayList(petNames));
            if (edit && initialValues) {
                petNameComboBox.setValue(editingSale.getPetName());
                initialValues = false;
            } else {
                petNameComboBox.setValue(petNames.get(0));
            }
        } else {
            petNameComboBox.setItems(FXCollections.observableArrayList("No pets found"));
            petNameComboBox.setValue("No pets found");
        }
    }
}
