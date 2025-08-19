package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.VIAPetsModelManager;

import java.io.IOException;

/**
 * Controller class for managing the Customer List view.
 * Provides functionality to add, edit, or remove customers, and displays customer data in a table.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class CustomerListController {

    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> streetColumn;
    @FXML private TableColumn<Customer, String> houseNumberColumn;
    @FXML private TableColumn<Customer, String> cityColumn;
    @FXML private TableColumn<Customer, String> zipCodeColumn;
    @FXML private TableView<Customer> customerTable;

    private ViewHandler viewHandler;
    private Scene scene;

    /**
     * Initializes the controller with the necessary dependencies and sets up the customer table.
     *
     * @param viewHandler the handler for switching views
     * @param scene the scene associated with this controller
     */
    public void init(ViewHandler viewHandler, Scene scene) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        setupTable();
    }

    /**
     * Configures the table columns to display customer properties and populates the table with data.
     */
    private void setupTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("houseName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        refreshTable();
    }

    /**
     * Refreshes the customer table with the latest data.
     */
    private void refreshTable() {
        customerTable.setItems(FXCollections.observableArrayList(
                VIAPetsModelManager.getViaPets().getCustomerList().getCustomers()
        ));
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
     * Resets the view by refreshing the customer table.
     */
    public void reset() {
        refreshTable();
    }

    /**
     * Handles the action of removing a selected customer from the list.
     * Updates the customer file after removal.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) throws IOException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            VIAPetsModelManager.getViaPets().getCustomerList().removeCustomer(selectedCustomer);
            VIAPetsModelManager.updateCustomersFile();
            refreshTable();
        } else {
            showAlert("No Selection", "Please select a customer to remove.");
        }
    }

    /**
     * Handles the action of adding a new customer.
     * Opens the Customer Add/Edit view in add mode.
     *
     * @param event
     */
    @FXML
    private void handleAddCustomer(ActionEvent event) {
        CustomerAddEditController addController = viewHandler.getCustomerAddEditController();
        addController.setEdit(false);
        addController.reset(); // Clear any previous input
        viewHandler.openView("CustomerAddEdit");
    }

    /**
     * Handles the action of editing a selected customer.
     * Opens the Customer Add/Edit view in edit mode with the selected customer's details.
     *
     * @param event
     */
    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            CustomerAddEditController editController = viewHandler.getCustomerAddEditController();
            editController.setEdit(true);
            editController.setEditingCustomer(selectedCustomer);
            viewHandler.openView("CustomerAddEdit");
        } else {
            showAlert("No Selection", "Please select a customer to edit.");
        }
    }

    /**
     * Displays a warning alert with the specified title and content.
     *
     * @param title   the title of the alert
     * @param content the content of the alert
     */
    private void showAlert(String title, String content) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
