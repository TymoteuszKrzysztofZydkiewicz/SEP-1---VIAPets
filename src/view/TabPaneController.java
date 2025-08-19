package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Controller class for managing the TabPane in the VIAPets application.
 * Handles the dynamic content and behavior of tabs for Booking, Customer, Sale, and Pet lists.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class TabPaneController {

    @FXML private TabPane tabPane;

    @FXML private Tab tab1; // Booking List
    @FXML private Tab tab2; // Customer List
    @FXML private Tab tab3; // Sale List
    @FXML private Tab tab4; // Pet List

    private ViewHandler viewHandler;

    /**
     * Initializes the controller with the necessary dependencies and sets up the tabs.
     *
     * @param viewHandler the handler for managing views
     * @param scene the root scene containing the TabPane
     */
    public void init(ViewHandler viewHandler, Scene scene) {
        this.viewHandler = viewHandler;
        setUpTabs();
    }

    /**
     * Dynamically sets the content for each tab based on the associated controllers.
     */
    private void setUpTabs() {
        tab1.setContent(viewHandler.getBookingListController().getScene().getRoot());
        tab2.setContent(viewHandler.getCustomerListController().getScene().getRoot());
        tab3.setContent(viewHandler.getSaleListController().getScene().getRoot());
        tab4.setContent(viewHandler.getPetListController().getScene().getRoot());
    }

    /**
     * Retrieves the root scene of the TabPane.
     *
     * @return the scene containing the TabPane
     */
    public Scene getScene() {
        return tabPane.getScene();
    }

    /**
     * Resets the contents of all tabs by refreshing the data in their respective controllers.
     */
    public void reset() {
        viewHandler.getBookingListController().reset();
        viewHandler.getCustomerListController().reset();
        viewHandler.getSaleListController().reset();
        viewHandler.getPetListController().reset();
    }

    /**
     * Selects a specific tab by its name.
     *
     * @param tabName the name of the tab to select (e.g., "BookingList", "CustomerList", "SaleList", "PetList")
     */
    public void selectTab(String tabName) {
        switch (tabName) {
            case "BookingList" -> tabPane.getSelectionModel().select(tab1);
            case "CustomerList" -> tabPane.getSelectionModel().select(tab2);
            case "SaleList" -> tabPane.getSelectionModel().select(tab3);
            case "PetList" -> tabPane.getSelectionModel().select(tab4);
        }
    }
}
