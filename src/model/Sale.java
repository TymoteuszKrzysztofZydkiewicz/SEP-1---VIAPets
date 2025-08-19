package model;

import java.io.Serializable;

/**
 * A class representing a sale transaction.
 * This class includes attributes for the customer, pet, sale price, and the date of the sale.
 * It provides methods for setting and retrieving these attributes, comparing sale objects and generating
 * a string representation.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */

public class Sale implements Serializable {
    private Customer customer;
    private Pet pet;
    private double price;
    private Date date;
    /**
     * 4 argument Constructor for creating sale objects.
     * Parameters:
     * @param customer the customer involved in the sale
     * @param pet      the pet being sold
     * @param price    the price of the sale
     * @param date     the date of the sale
     */

    public Sale(Customer customer, Pet pet, double price, Date date)
    {
        this.customer = customer;
        this.pet = pet;
        this.price = price;
        this.date = date;
    }
    /**
     * Gets the customer involved in the sale.
     *
     * @return customer
     */

    public String getPetName()
    {
        return pet.getName();
    }

    public String getCustomerName()
    {
        return customer.getName();
    }

    public Customer getCustomer() {
        return customer;
    }
    /**
     * Gets the pet being sold in the sale.
     *
     * @return pet
     */

    public Pet getPet() {
        return pet;
    }
    /**
     * Gets the date of the sale.
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }
    /**
     * Gets the price of the sale.
     *
     * @return the sale price
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the customer involved in the sale.
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    /**
     * Sets the pet being sold in the sale.
     *
     * @param pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    /**
     * Sets the price of the sale.
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Sets the date of the sale.
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * toString method returns a string representation of the address object.
     * This method provides a formatted string containing Date, Pet name,
     * Customer, and Price.
     *
     * @return a formatted string representation of the sale
     */
    public String toString()
    {
        return "Date: " + getDate() + " Pet name: " + getPet().getName() + " Customer: " + getCustomer().getName() + " Price: " + getPrice();
    }
    /**
     * Equals method to compare sale for equality.
     *
     * @param obj Object to compare.
     * @return True if the sales are equal, false if they're not.
     */

    public boolean equals(Object obj)
    {
        if(obj == null || obj.getClass() != this.getClass()) // Check if the object is null or of a different class
        {
            return false;
        }

        Sale kennelBookingCompare = (Sale) obj; // makes a copy

        return this.getCustomer().equals(kennelBookingCompare.getCustomer()) && this.getPet().equals(kennelBookingCompare.getPet()) && this.getPrice() == kennelBookingCompare.getPrice() && this.getDate().equals(kennelBookingCompare.getDate()); // compare instance variables for equality
    }
}
