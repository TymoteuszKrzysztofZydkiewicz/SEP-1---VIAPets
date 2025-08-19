package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Sale;
import model.VIAPetsModelManager;

import java.io.IOException;

/**
 * Controller class for managing the Sale List view.
 * Provides functionality to add, edit, remove, and view details of sales,
 * and displays sales data in a table.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class SaleListController {

    @FXML private TableView<Sale> salesTable;
    @FXML private TableColumn<Sale, String> petName;
    @FXML private TableColumn<Sale, String> customerName;
    @FXML private TableColumn<Sale, String> saleDate;
    @FXML private TableColumn<Sale, String> salePrice;

    private ViewHandler viewHandler;
    private Scene scene;

    /**
     * Initializes the controller with the necessary dependencies and sets up the sales table.
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
     * Resets the view by refreshing the sales table.
     */
    public void reset() {
        refreshTable();
    }

    /**
     * Configures the table columns to display sale properties and populates the table with data.
     */
    private void setupTable() {
        petName.setCellValueFactory(new PropertyValueFactory<>("petName"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        saleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        salePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        refreshTable();
    }

    /**
     * Refreshes the sales table with the latest data from the model.
     */
    private void refreshTable() {
        salesTable.setItems(FXCollections.observableArrayList(
                VIAPetsModelManager.getViaPets().getSaleList().getSales()
        ));
    }

    /**
     * Handles the action of adding a new sale.
     * Opens the Sale Add/Edit view in add mode.
     *
     * @param event
     */
    @FXML
    private void handleAddButton(ActionEvent event) {
        SaleAddEditController addController = viewHandler.getSaleAddEditController();
        addController.setEdit(false);
        addController.reset(); // Clear form for adding a new sale
        viewHandler.openView("SaleAddEdit");
    }

    /**
     * Handles the action of removing a selected sale from the list.
     * Updates the sales data file after removal.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleRemoveButton(ActionEvent event) throws IOException {
        Sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            VIAPetsModelManager.getViaPets().getSaleList().removeSale(selectedSale);
            VIAPetsModelManager.updateSalesFile();
            refreshTable();
        } else {
            showAlert("No Selection", "Please select a sale to remove.");
        }
    }

    /**
     * Handles the action of editing a selected sale.
     * Opens the Sale Add/Edit view in edit mode with the selected sale's details.
     *
     * @param event
     */
    @FXML
    private void handleEditButton(ActionEvent event) {
        Sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            SaleAddEditController editController = viewHandler.getSaleAddEditController();
            editController.setEdit(true);
            editController.setEditingSale(selectedSale);
            viewHandler.openView("SaleAddEdit");
        } else {
            showAlert("No Selection", "Please select a sale to edit.");
        }
    }

    /**
     * Handles the action of viewing details of a selected sale.
     * Opens the Sale View to display sale details.
     *
     * @param event
     */
    @FXML
    private void handleSeeDetailsButton(ActionEvent event) {
        Sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            SaleViewController viewController = viewHandler.getSaleViewController();
            viewController.setViewedSale(selectedSale);
            viewHandler.openView("SaleView");
        } else {
            showAlert("No Selection", "Please select a sale to view.");
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
