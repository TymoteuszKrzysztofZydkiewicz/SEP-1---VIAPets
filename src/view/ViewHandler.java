package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Manages the navigation and initialization of all views in the VIAPets application.
 * Provides methods to load, switch, and reset views dynamically.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class ViewHandler {

    private Stage stage;

    // Controllers for all views
    private BookingAddEditController bookingAddEditController;
    private BookingListController bookingListController;
    private BookingViewController bookingViewController;
    private CustomerAddEditController customerAddEditController;
    private CustomerListController customerListController;
    private PetAddEditController petAddEditController;
    private PetListController petListController;
    private PetViewController petViewController;
    private SaleAddEditController saleAddEditController;
    private SaleListController saleListController;
    private SaleViewController saleViewController;
    private TabPaneController tabPaneController;

    /**
     * Constructor to initialize the ViewHandler with the primary stage.
     *
     * @param stage the primary stage for the application
     */
    public ViewHandler(Stage stage) {
        this.stage = stage;
    }

    /**
     * Starts the application by loading all views and opening the default view.
     */
    public void start() {
        loadAllViews();
        openView("TabPane"); // Default starting view
    }

    /**
     * Opens the specified view by its ID and updates the stage.
     *
     * @param viewId the ID of the view to open (e.g., "BookingList", "CustomerList")
     */
    public void openView(String viewId) {
        switch (viewId) {
            case "BookingAddEdit" -> stage.setScene(bookingAddEditController.getScene());
            case "BookingList" -> {
                stage.setScene(bookingListController.getScene());
                bookingListController.reset();
            }
            case "BookingView" -> stage.setScene(bookingViewController.getScene());
            case "CustomerAddEdit" -> stage.setScene(customerAddEditController.getScene());
            case "CustomerList" -> {
                stage.setScene(customerListController.getScene());
                customerListController.reset();
            }
            case "PetAddEdit" -> stage.setScene(petAddEditController.getScene());
            case "PetList" -> {
                stage.setScene(petListController.getScene());
                petListController.reset();
            }
            case "PetView" -> stage.setScene(petViewController.getScene());
            case "SaleAddEdit" -> stage.setScene(saleAddEditController.getScene());
            case "SaleList" -> {
                stage.setScene(saleListController.getScene());
                saleListController.reset();
            }
            case "SaleView" -> stage.setScene(saleViewController.getScene());
            case "TabPane" -> {
                stage.setScene(tabPaneController.getScene());
                tabPaneController.reset();
            }
        }
        stage.setTitle("VIAPets");
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Loads all views and initializes their controllers.
     */
    private void loadAllViews() {
        loadView("BookingAddEdit.fxml", "BookingAddEditController");
        loadView("BookingList.fxml", "BookingListController");
        loadView("BookingView.fxml", "BookingViewController");
        loadView("CustomerAddEdit.fxml", "CustomerAddEditController");
        loadView("CustomerList.fxml", "CustomerListController");
        loadView("PetAddEdit.fxml", "PetAddEditController");
        loadView("PetList.fxml", "PetListController");
        loadView("PetView.fxml", "PetViewController");
        loadView("SaleAddEdit.fxml", "SaleAddEditController");
        loadView("SaleList.fxml", "SaleListController");
        loadView("SaleView.fxml", "SaleViewController");
        loadView("TabPane.fxml", "TabPaneController");
    }

    /**
     * Generic method to load a view and initialize its controller.
     *
     * @param fxmlFile the FXML file name for the view
     * @param controllerName the name of the associated controller
     */
    private void loadView(String fxmlFile, String controllerName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();

            switch (controllerName) {
                case "BookingAddEditController" -> {
                    bookingAddEditController = (BookingAddEditController) controller;
                    bookingAddEditController.init(this, scene);
                }
                case "BookingListController" -> {
                    bookingListController = (BookingListController) controller;
                    bookingListController.init(this, scene);
                }
                case "BookingViewController" -> {
                    bookingViewController = (BookingViewController) controller;
                    bookingViewController.init(this, scene);
                }
                case "CustomerAddEditController" -> {
                    customerAddEditController = (CustomerAddEditController) controller;
                    customerAddEditController.init(this, scene);
                }
                case "CustomerListController" -> {
                    customerListController = (CustomerListController) controller;
                    customerListController.init(this, scene);
                }
                case "PetAddEditController" -> {
                    petAddEditController = (PetAddEditController) controller;
                    petAddEditController.init(this, scene);
                }
                case "PetListController" -> {
                    petListController = (PetListController) controller;
                    petListController.init(this, scene);
                }
                case "PetViewController" -> {
                    petViewController = (PetViewController) controller;
                    petViewController.init(this, scene);
                }
                case "SaleAddEditController" -> {
                    saleAddEditController = (SaleAddEditController) controller;
                    saleAddEditController.init(this, scene);
                }
                case "SaleListController" -> {
                    saleListController = (SaleListController) controller;
                    saleListController.init(this, scene);
                }
                case "SaleViewController" -> {
                    saleViewController = (SaleViewController) controller;
                    saleViewController.init(this, scene);
                }
                case "TabPaneController" -> {
                    tabPaneController = (TabPaneController) controller;
                    tabPaneController.init(this, scene);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for controllers
    public BookingAddEditController getBookingAddEditController() { return bookingAddEditController; }
    public BookingListController getBookingListController() { return bookingListController; }
    public BookingViewController getBookingViewController() { return bookingViewController; }
    public CustomerAddEditController getCustomerAddEditController() { return customerAddEditController; }
    public CustomerListController getCustomerListController() { return customerListController; }
    public PetAddEditController getPetAddEditController() { return petAddEditController; }
    public PetListController getPetListController() { return petListController; }
    public PetViewController getPetViewController() { return petViewController; }
    public SaleAddEditController getSaleAddEditController() { return saleAddEditController; }
    public SaleListController getSaleListController() { return saleListController; }
    public SaleViewController getSaleViewController() { return saleViewController; }
    public TabPaneController getTabPaneController() { return tabPaneController; }
}
