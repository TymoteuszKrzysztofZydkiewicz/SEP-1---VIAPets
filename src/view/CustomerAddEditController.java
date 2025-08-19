package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import model.*;

import java.io.IOException;

/**
 * Controller class for managing the Add/Edit Customer view.
 * Provides functionality to add or edit a customer, with form validation.
 * Handles navigation back to the customer list tab in the TabPane.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class CustomerAddEditController {

  @FXML private TextField nameField;
  @FXML private TextField streetField;
  @FXML private TextField phoneField;
  @FXML private TextField zipCodeField;
  @FXML private TextField cityField;
  @FXML private TextField houseNumberField;

  private ViewHandler viewHandler;
  private Scene scene;
  private Alert alert;
  private boolean edit;
  private Customer editingCustomer;

  /**
   * Initializes the controller with the necessary dependencies.
   * Sets up the warning alert.
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
   * Resets the form fields to empty values.
   */
  public void reset() {
    nameField.clear();
    streetField.clear();
    phoneField.clear();
    zipCodeField.clear();
    cityField.clear();
    houseNumberField.clear();
  }

  /**
   * Sets the edit mode for the controller.
   *
   * @param edit  true for edit mode,  false for add mode
   */
  public void setEdit(boolean edit) {
    this.edit = edit;
  }

  /**
   * Sets the customer to be edited and populates the fields with its details.
   *
   * @param customer the customer to edit
   */
  public void setEditingCustomer(Customer customer) {
    this.editingCustomer = customer;
    if (edit) {
      setFields();
    }
  }

  /**
   * Populates the form fields with the details of the customer being edited.
   */
  private void setFields() {
    nameField.setText(editingCustomer.getName());
    phoneField.setText(editingCustomer.getPhoneNumber());
    streetField.setText(editingCustomer.getStreet());
    zipCodeField.setText(editingCustomer.getPostalCode());
    cityField.setText(editingCustomer.getCity());
    houseNumberField.setText(editingCustomer.getHouseName());
  }

  /**
   * Handles the cancel action by returning to the TabPane view and selecting the "Customer List" tab.
   *
   * @param event
   */
  @FXML
  private void handleCancel(ActionEvent event) {
    TabPaneController tabPaneController = viewHandler.getTabPaneController();
    tabPaneController.selectTab("CustomerList");
    viewHandler.openView("TabPane");
  }

  /**
   * Handles the save action, validating and saving the customer data.
   * Adds a new customer or updates an existing customer in the customer list.
   *
   * @param event
   * @throws IOException
   */
  @FXML
  private void handleSaveCustomer(ActionEvent event) throws IOException {
    if (!nameField.getText().isEmpty() && !cityField.getText().isEmpty() &&
            !streetField.getText().isEmpty() && !phoneField.getText().isEmpty() &&
            !zipCodeField.getText().isEmpty() && !houseNumberField.getText().isEmpty()) {

      Customer customer = new Customer(
              nameField.getText(),
              new Address(streetField.getText(), cityField.getText(), zipCodeField.getText(), houseNumberField.getText()),
              phoneField.getText()
      );

      if (!edit) {
        if (VIAPetsModelManager.getViaPets().getCustomerList().addCustomer(customer)) {
          VIAPetsModelManager.updateCustomersFile();
          viewHandler.openView("TabPane");
        } else {
          alert.setContentText("Warning, a customer with such name already exists");
          alert.show();
        }
      } else {
        if (VIAPetsModelManager.getViaPets().getCustomerList().editCustomer(editingCustomer, customer)) {
          VIAPetsModelManager.updateCustomersFile();
          viewHandler.openView("TabPane");
        } else {
          alert.setContentText("Warning, a customer with such name already exists");
          alert.show();
        }
      }
    } else {
      alert.setContentText("Warning, the entered data is incorrect");
      alert.show();
    }
  }
}
