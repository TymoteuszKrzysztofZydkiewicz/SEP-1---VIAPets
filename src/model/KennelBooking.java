package model;

import java.io.Serializable;

/**
 * A class representing a kennel booking.
 * This class includes attributes for the customer, pet, start and end dates, and the booking price.
 * It provides methods to get and set these attributes, as well as utility methods for
 * comparing booking objects and representing them as strings.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class KennelBooking implements Serializable {
    private Customer customer; // The customer making the booking
    private Pet pet; // The pet being booked into the kennel
    private Date startDate; // The start date of the booking
    private Date endDate; // The end date of the booking
    private double price; // The price of the booking

    /**
     * 10 argument Constructor for creating pet objects.
     * Parameters:
     *
     * @param customer  the customer making the booking
     * @param pet       the pet being booked
     * @param startDate the start date of the booking
     * @param endDate   the end date of the booking
     * @param price     the price of the booking
     */
    public KennelBooking(Customer customer, Pet pet, Date startDate, Date endDate, double price) {
        this.setCustomer(customer);
        this.setPet(pet);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setPrice(price);
    }

    // GETTERS

    /**
     * Gets the customer making the booking.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the name of the customer making the booking.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customer.getName();
    }

    /**
     * Gets the class name of the pet being booked.
     *
     * @return the pet class name
     */
    public String getPetClass()
    {
        return pet.getPetClassName();
    }

    /**
     * Gets the name of the pet being booked.
     *
     * @return the pet name
     */
    public String getPetName() {
        return pet.getName();
    }
    /**
     * Gets the pet being booked.
     *
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Gets the price of the booking.
     *
     * @return the booking price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the end date of the booking.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the start date of the booking.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    // SETTERS

    /**
     * Sets the customer making the booking.
     *
     * @param customer the new customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Sets the pet being booked.
     *
     * @param pet the new pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Sets the price of the booking.
     *
     * @param price the new booking price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the end date of the booking.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the start date of the booking.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Override toString method returns a string representation of the KennelBooking object.
     * This method provides a formatted string containing the booking dates,
     * pet's name, customer's name, and the booking price.
     *
     * @return a formatted string representation of the booking
     */
    @Override
    public String toString() {
        return "Dates: " + getStartDate() + " - " + getEndDate() +
                " Pet: " + getPet().getName() +
                " Customer: " + getCustomer().getName() +
                " Price: " + getPrice();
    }

    /**
     * Override equals method which compares this KennelBooking object with another object for equality.
     * This method checks if all attributes (customer, pet, price, start date, and end date)
     * of both bookings are equal.
     *
     * @param obj the object to compare with
     * @return true if the bookings are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
            // Check if the object is null or of a different class
        }
        // makes a copy
        KennelBooking kennelBookingCompare = (KennelBooking) obj;
        // compare instance variables for equality
        return kennelBookingCompare.getCustomer().equals(getCustomer()) &&
                kennelBookingCompare.getPet().equals(getPet()) &&
                kennelBookingCompare.getPrice() == getPrice() &&
                kennelBookingCompare.getEndDate().equals(getEndDate()) &&
                kennelBookingCompare.getStartDate().equals(getStartDate());
    }
}
