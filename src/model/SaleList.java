package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of sales.
 * This class uses an ArrayList to store and manage Sale objects.
 * It provides methods for adding, removing, retrieving, and editing sales in the list.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class SaleList implements Serializable {
    private ArrayList<Sale> sales; //ArrayList to store all sales

    /**
     * Constructs an empty SaleList.
     */
    public SaleList() {
        this.sales = new ArrayList<Sale>();
    }

    /**
     * Constructs a saleList with an existing list of sales.
     *
     * @param sales the initial list of sales
     */
    public SaleList(ArrayList<Sale> sales) {
        this.sales = new ArrayList<Sale>(sales);
    }

    /**
     * Gets the list of sales.
     *
     * @return an ArrayList of  Sale objects
     */
    public ArrayList<Sale> getSales() {
        return sales;
    }

    /**
     * Adds a sale to the list.
     *
     * @param sale the sale to be added
     */
    public void addSale(Sale sale) {
        sales.add(sale);
    }

    /**
     * Removes a sale from the list.
     *
     * @param sale the sale to be removed
     */
    public void removeSale(Sale sale) {
        sales.remove(sale);
    }

    /**
     * Gets the index of a specific sale in the list.
     *
     * @param sale the sale whose index is to be retrieved
     * @return the index of the sale in the list, or "-1" if not found
     */
    public int getIndex(Sale sale) {
        return sales.indexOf(sale);
    }

    /**
     * Edits an existing sale by replacing it with a new sale.
     *
     * @param saleToEdit the sale to be edited
     * @param newSale    the new sale to replace the old one
     */
    public void editSale(Sale saleToEdit, Sale newSale) {
        sales.set(this.getIndex(saleToEdit), newSale);
    }
}
