package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pet;
import model.VIAPetsModelManager;

import java.io.IOException;

/**
 * Controller class for managing the Pet List view.
 * Provides functionality to add, edit, remove, and view details of pets,
 * and displays pet data in a table.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class PetListController {

  @FXML private TableColumn<Pet, String> petNameColumn;
  @FXML private TableColumn<Pet, Integer> petAgeColumn;
  @FXML private TableColumn<Pet, String> petGenderColumn;
  @FXML private TableColumn<Pet, String> petColorColumn;
  @FXML private TableColumn<Pet, Double> petPriceColumn;
  @FXML private TableColumn<Pet, Boolean> petTypeColumn;
  @FXML private TableColumn<Pet, String> petClass;
  @FXML private TableView<Pet> petsTableView;

  private ViewHandler viewHandler;
  private Scene scene;

  /**
   * Initializes the controller with the necessary dependencies and sets up the pet table.
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
   * Retrieves the scene managed by this controller.
   *
   * @return the scene
   */
  public Scene getScene() {
    return scene;
  }

  /**
   * Resets the view by refreshing the pet table.
   */
  public void reset() {
    refreshTable();
  }

  /**
   * Configures the table columns to display pet properties and populates the table with data.
   */
  private void setupTable() {
    petClass.setCellValueFactory(new PropertyValueFactory<>("petClassName"));
    petNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    petAgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    petGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    petColorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    petPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    petTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    refreshTable();
  }

  /**
   * Refreshes the pet table with the latest data from the model.
   */
  private void refreshTable() {
    petsTableView.setItems(FXCollections.observableArrayList(
            VIAPetsModelManager.getViaPets().getPetList().getAllPets()
    ));
  }

  /**
   * Handles the action of adding a new pet.
   * Opens the Pet Add/Edit view in add mode.
   *
   * @param event
   */
  @FXML
  private void handleAddPet(ActionEvent event) {
    PetAddEditController addController = viewHandler.getPetAddEditController();
    addController.setEdit(false);
    addController.reset(); // Clear any previous data
    viewHandler.openView("PetAddEdit");
  }

  /**
   * Handles the action of editing a selected pet.
   * Opens the Pet Add/Edit view in edit mode with the selected pet's details.
   *
   * @param event
   */
  @FXML
  private void handleEditPet(ActionEvent event) {
    Pet selectedPet = petsTableView.getSelectionModel().getSelectedItem();
    if (selectedPet != null) {
      PetAddEditController editController = viewHandler.getPetAddEditController();
      editController.setEdit(true);
      editController.setEditingPet(selectedPet);
      viewHandler.openView("PetAddEdit");
    } else {
      showAlert("No Selection", "Please select a pet to edit.");
    }
  }

  /**
   * Handles the action of removing a selected pet from the list.
   * Updates the pet data file after removal.
   *
   * @param event
   * @throws IOException
   */
  @FXML
  private void handleRemovePet(ActionEvent event) throws IOException {
    Pet selectedPet = petsTableView.getSelectionModel().getSelectedItem();
    if (selectedPet != null) {
      VIAPetsModelManager.getViaPets().getPetList().removePet(selectedPet);
      VIAPetsModelManager.updatePetsFile();
      refreshTable();
    } else {
      showAlert("No Selection", "Please select a pet to remove.");
    }
  }

  /**
   * Handles the action of viewing details of a selected pet.
   * Opens the Pet View to display pet details.
   *
   * @param event
   */
  @FXML
  private void handleViewPet(ActionEvent event) {
    Pet selectedPet = petsTableView.getSelectionModel().getSelectedItem();
    if (selectedPet != null) {
      PetViewController viewController = viewHandler.getPetViewController();
      viewController.setViewedPet(selectedPet);
      viewHandler.openView("PetView");
    } else {
      showAlert("No Selection", "Please select a pet to view.");
    }
  }

  /**
   * Displays a warning alert with the specified title and content.
   *
   * @param title   the title of the alert
   * @param content the content of the alert
   */
  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
