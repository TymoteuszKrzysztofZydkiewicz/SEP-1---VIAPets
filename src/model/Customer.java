package model;

import java.io.Serializable;

/**
 * A class representing a customer.
 * This class includes attributes for the customer's name, address, and phone number.
 * It provides methods to get and set these attributes, as well as utility methods
 * for comparing customer objects and representing them as strings.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class Customer implements Serializable {
  private String name; // Customer's name
  private Address address; // Customer's address
  private String phoneNumber; // Customer's phone number

  /**
   * 3 argument Constructor for creating pet objects.
   * Parameters:
   *
   * @param name        the name of the customer
   * @param address     the address of the customer
   * @param phoneNumber the phone number of the customer
   */
  public Customer(String name, Address address, String phoneNumber) {
    this.setAddress(address);
    this.setName(name);
    this.setPhoneNumber(phoneNumber);
  }

  // GETTERS

  /**
   * Gets the name of the customer.
   *
   * @return the customer's name
   */

  public String getName() {
    return name;
  }

  /**
   * Gets the address of the customer.
   *
   * @return the customer's address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Gets the phone number of the customer.
   *
   * @return the customer's phone number
   */
  public String getPostalCode()
  {
    return address.getPostalCode();
  }
  public String getCity()
  {
     return address.getCity();
  }

  public String getStreet()
  {
    return address.getStreet();
  }
  public String getHouseName()
  {
    return address.getHouseNumber();
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  // SETTERS

  /**
   * Sets the name of the customer.
   *
   * @param name the new name of the customer
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the address of the customer.
   *
   * @param address the new address of the customer
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Sets the phone number of the customer.
   *
   * @param phoneNumber the new phone number of the customer
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Override toString method returns a string representation of the object.
   * This method provides a formatted string containing the customer's name,
   * address, and phone number.
   *
   * @return a formatted string representation of the customer
   */


  @Override
  public String toString() {
    return "\nName: " + this.getName() +
            "\nAddress: " + this.getAddress() +
            "\nPhone Number: " + this.getPhoneNumber();
  }

  /**
   * Compares this customer object with another object for equality.
   * This method checks if all attributes (name, phone number, and address)
   * of both customers are equal.
   *
   * @param obj the object to compare with
   * @return true if the customers are equal, otherwise false
   */
  @Override
  public boolean equals(Object obj) {
    // Check if the object is null or of a different class
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    // makes a copy
    Customer customer = (Customer) obj;
    // compare instance variables for equality
    return this.getName().equals(customer.getName()) &&
            this.getPhoneNumber().equals(customer.getPhoneNumber()) &&
            this.getAddress().equals(customer.getAddress());
  }
}
