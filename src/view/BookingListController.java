package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Date;
import model.KennelBooking;
import model.VIAPetsModelManager;

import java.io.IOException;

/**
 * Controller class for managing the Booking List view.
 * Provides functionality to add, edit, remove, and view bookings, as well as display them in a table.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class BookingListController {

    @FXML private TableColumn<KennelBooking, String> petNameBooking;
    @FXML private TableColumn<KennelBooking, String> customerNameBooking;
    @FXML private TableColumn<KennelBooking, String> petBooking;
    @FXML private TableColumn<KennelBooking, Date> startDate;
    @FXML private TableColumn<KennelBooking, Date> endDate;
    @FXML private TableColumn<KennelBooking, Double> bookingPrice;
    @FXML private TableView<KennelBooking> bookingTableView;

    private ViewHandler viewHandler;
    private Scene scene;

    /**
     * Initializes the controller with the necessary view handler and scene.
     * Sets up the table view to display booking data.
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
     * Configures the table columns and populates the table with booking data.
     */
    private void setupTable() {
        petBooking.setCellValueFactory(new PropertyValueFactory<>("petClass"));
        petNameBooking.setCellValueFactory(new PropertyValueFactory<>("petName"));
        customerNameBooking.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        bookingPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        refreshTable();
    }

    /**
     * Refreshes the table with the latest booking data.
     */
    private void refreshTable() {
        bookingTableView.setItems(FXCollections.observableArrayList(
                VIAPetsModelManager.getViaPets().getBookingList().getBookings()
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
     * Resets the view by refreshing the table data.
     */
    public void reset() {
        refreshTable();
    }

    /**
     * Handles the action for adding a new booking.
     * Opens the Booking Add/Edit view in add mode.
     *
     * @param event
     */
    @FXML
    private void handleAddBooking(ActionEvent event) {
        BookingAddEditController addController = viewHandler.getBookingAddEditController();
        addController.setEdit(false);
        addController.reset(); // Ensure a clean form
        viewHandler.openView("BookingAddEdit");
    }

    /**
     * Handles the action for editing an existing booking.
     * Opens the Booking Add/Edit view in edit mode.
     *
     * @param event
     */
    @FXML
    private void handleEditBooking(ActionEvent event) {
        KennelBooking selectedBooking = bookingTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            BookingAddEditController editController = viewHandler.getBookingAddEditController();
            editController.setEdit(true);
            editController.setEditingBooking(selectedBooking);
            viewHandler.openView("BookingAddEdit");
        } else {
            showAlert("No Selection", "Please select a booking to edit.");
        }
    }

    /**
     * Handles the action for removing an existing booking.
     * Removes the selected booking from the list and updates the data file.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleRemoveBooking(ActionEvent event) throws IOException {
        KennelBooking selectedBooking = bookingTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            VIAPetsModelManager.getViaPets().getBookingList().removeBooking(selectedBooking);
            VIAPetsModelManager.updateBookingsFile();
            refreshTable();
        } else {
            showAlert("No Selection", "Please select a booking to remove.");
        }
    }

    /**
     * Handles the action for viewing details of an existing booking.
     * Opens the Booking View to display booking details.
     *
     * @param event
     */
    @FXML
    private void handleViewBooking(ActionEvent event) {
        KennelBooking selectedBooking = bookingTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            BookingViewController viewController = viewHandler.getBookingViewController();
            viewController.setViewedBooking(selectedBooking);
            viewHandler.openView("BookingView");
        } else {
            showAlert("No Selection", "Please select a booking to view.");
        }
    }

    /**
     * Displays an alert dialog with the specified title and content.
     *
     * @param title
     * @param content
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
