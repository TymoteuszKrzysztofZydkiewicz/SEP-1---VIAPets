package model;

/**
 *  A class containing all the list objects needed in the application.
 *  This class serves as a central container for managing instances of BookingList,  CustomerList,
 *  PetList, and  SaleList, making it easier to manage all lists within the application.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0
 */
public class VIAPets {
    private BookingList bookingList; //List of bookings
    private CustomerList customerList; //list of customers
    private PetList petList; //List of pets
    private SaleList saleList; //List of sales

/**
 * No-argument constructor which creates empty list objects
 */
    public VIAPets() {
        bookingList = new BookingList();
        customerList = new CustomerList();
        petList = new PetList();
        saleList = new SaleList();
    }

    /**
    * Changes the booking list to a different object
    *
    * @param bookingList the new object
    */
    public void setBookingList(BookingList bookingList) {
        this.bookingList = bookingList;
    }

    /**
    * Changes the customer list to a different object
    *
    * @param customerList the new object
    */
    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }
    /**
     * Changes the pet list to a different object.
     *
     * @param petList the new object
     */
    public void setPetList(PetList petList) {
        this.petList = petList;
    }
    /**
     * Changes the pet list to a different object.
     *
     * @param saleList the new object
     */
    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }
    /**
     * Gets the current booking list.
     *
     * @return the BookingList object
     */
    public BookingList getBookingList() {
        return bookingList;
    }
    /**
     * Gets the current customer list.
     *
     * @return the customerList object
     */

    public CustomerList getCustomerList() {
        return customerList;
    }
    /**
     * Gets the current pet list.
     *
     * @return the PetList object
     */

    public PetList getPetList()
    {
        return petList;
    }
    /**
     * Gets the current sale list.
     *
     * @return the SaleList object
     */

    public SaleList getSaleList() {
        return saleList;
    }

}
