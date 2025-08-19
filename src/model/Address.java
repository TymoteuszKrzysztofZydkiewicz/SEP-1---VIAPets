package model;

import java.io.Serializable;

/**
 * A class representing an address of a customer.
 * This class includes attributes for the street name, city, postal code,
 * and house number. It provides methods for setting and retrieving these
 * attributes, as well as for comparing address objects and generating a
 * string representation.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class Address implements Serializable {
    private String street; // Name of the street
    private String city; // Name of the city
    private String postalCode; // Postal code of the customer
    private String houseNumber; // Number of the customer's house

    /**
     * 4 argument Constructor for creating address objects.
     * Parameters:
     *
     * @param street      the name of the street
     * @param city        the name of the city
     * @param postalCode  the postal code of the address
     * @param houseNumber the house number of the address
     */
    public Address(String street, String city, String postalCode, String houseNumber) {
        this.setStreet(street);
        this.setCity(city);
        this.setPostalCode(postalCode);
        this.setHouseNumber(houseNumber);
    }

    /**
     * Gets the street name of the address.
     *
     * @return the name of the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets the city name of the address.
     *
     * @return the name of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the house number of the address.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Gets the postal code of the address.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the city name of the address.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the house number of the address.
     *
     * @param houseNumber
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Sets the postal code of the address.
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the street name of the address.
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Override toString method returns a string representation of the address object.
     * This method provides a formatted string containing the city, postal code,
     * street name, and house number.
     *
     * @return a formatted string representation of the address
     */
    @Override
    public String toString() {
        return "City: " + getCity() + " Postalcode: " + getPostalCode() +
                " Street: " + getStreet() + " HouseNumber: " + getHouseNumber();
    }

    /**
     * Override equals method to compare addresses for equality.
     *
     * @param obj Object to compare.
     * @return True if the sales are equal, false if they're not.
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is null or of a different class
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        // makes a copy
        Address addressCompare = (Address) obj;
        // compare instance variables for equality
        return this.getCity().equals(addressCompare.getCity()) &&
                this.getPostalCode().equals(addressCompare.getPostalCode()) &&
                this.getHouseNumber().equals(addressCompare.getHouseNumber()) &&
                this.getStreet().equals(addressCompare.getStreet());
    }
}
