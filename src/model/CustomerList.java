package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of customers.
 * This class uses an ArrayList to store and manage customer objects.
 * It provides methods for adding, removing, retrieving, and editing customers in the list.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class CustomerList implements Serializable {
  private ArrayList<Customer> customers; //ArrayList to store all sales

  /**
   * Constructs an empty customer list.
   */
  public CustomerList() {
    customers = new ArrayList<Customer>();
  }

  /**
   * Constructs a customer list with an existing list of customers.
   *
   * @param customers the initial list of customers
   */
  public CustomerList(ArrayList<Customer> customers) {
    this.customers = new ArrayList<Customer>(customers);
  }

  /**
   * Gets the list of customers.
   *
   * @return an ArrayList of customer objects
   */
  public ArrayList<Customer> getCustomers() {
    return customers;
  }

  /**
   * Adds a customer to the list.
   *
   * @param customer the customer to be added
   */
  public boolean addCustomer(Customer customer) {
    if(getCustomerByName(customer.getName()) == null) {
      customers.add(customer);
      return true;
    }
    return false;
  }
  /**
   * Gets the customer from the list by name
   *
   * @param name
   * @return customer
   */
  public Customer getCustomerByName(String name)
  {
    for(Customer customer : customers) {
      if(customer.getName().equals(name)) {
        return customer;
      }
    }
    return null;
  }

  /**
   * Removes a customer from the list.
   *
   * @param customer the customer to be removed
   */
  public void removeCustomer(Customer customer) {
    customers.remove(customer);
  }

  /**
   * Gets the index of a specific customer in the list.
   *
   * @param customer the customer whose index is to be retrieved
   * @return the index of the customer in the list, or "-1" if not found
   */
  public int getIndex(Customer customer) {
    return customers.indexOf(customer);
  }

  /**
   * Edits an existing customer by replacing it with a new customer.
   *
   * @param customerToEdit      the customer to be edited
   * @param customerToChangeTo  the new customer to replace the old one
   */
  public boolean editCustomer(Customer customerToEdit, Customer customerToChangeTo) {
    CustomerList listCopy = new CustomerList(this.customers);
    listCopy.removeCustomer(customerToEdit);
    if(listCopy.getCustomerByName(customerToChangeTo.getName()) == null) {
      customers.set(this.getIndex(customerToEdit), customerToChangeTo);
      return true;
    }
    return false;
  }
}
