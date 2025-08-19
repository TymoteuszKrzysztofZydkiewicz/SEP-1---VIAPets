package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of kennel bookings.
 * This class manages a list of  KennelBooking objects and provides functionality
 * to add, remove, edit bookings, and check kennel availability.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class BookingList implements Serializable {
    private ArrayList<KennelBooking> bookings; //ArrayList to store all sales

    /**
     * Constructs an empty BookingList.
     */
    public BookingList() {
        this.bookings = new ArrayList<KennelBooking>();
    }

    /**
     * Constructs a BookingList with an existing list of bookings.
     *
     * @param bookings the initial list of bookings
     */
    public BookingList(ArrayList<KennelBooking> bookings) {
        this.bookings = new ArrayList<KennelBooking>(bookings);
    }

    /**
     * Gets the list of bookings.
     *
     * @return an ArrayList of KennelBooking objects
     */
    public ArrayList<KennelBooking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to the list if there is available space during the specified dates.
     *
     * @param booking the booking to be added
     * @return true if the booking was successfully added, otherwise false
     */
    public boolean addBooking(KennelBooking booking) {
        if (isKennelFree(booking.getStartDate(), booking.getEndDate())) {
            bookings.add(booking);
            return true;
        }
        return false;
    }

    /**
     * Removes a booking from the list.
     *
     * @param booking the booking to be removed
     */
    public void removeBooking(KennelBooking booking) {
        bookings.remove(booking);
    }

    /**
     * Edits an existing booking by replacing it with a new booking.
     * If the new booking's dates conflict with other bookings, the edit will fail.
     *
     * @param bookingToBeChanged the booking to be edited
     * @param bookingToChangeTo  the new booking to replace the old one
     * @return true if the edit was successful, otherwise false
     */
    public boolean editBooking(KennelBooking bookingToBeChanged, KennelBooking bookingToChangeTo) {
        BookingList bookingListCopy = new BookingList(this.getBookings());
        if (!bookingToBeChanged.getStartDate().equals(bookingToChangeTo.getStartDate()) ||
                !bookingToBeChanged.getEndDate().equals(bookingToChangeTo.getEndDate())) {
            bookingListCopy.removeBooking(bookingToBeChanged);
            if(bookingListCopy.isKennelFree(bookingToChangeTo.getStartDate(), bookingToChangeTo.getEndDate())) {
                bookings.set(this.getIndex(bookingToBeChanged), bookingToChangeTo);
                return true;
            }
            else
            {
                return false;
            }
        }
        bookings.set(this.getIndex(bookingToBeChanged), bookingToChangeTo);
        return true;
    }

    /**
     * Gets the index of a specific booking in the list.
     *
     * @param booking the booking whose index is to be retrieved
     * @return the index of the booking in the list, or "-1" if not found
     */
    public int getIndex(KennelBooking booking) {
        return bookings.indexOf(booking);
    }

    /**
     * Gets the number of free spaces available on a given date.
     *
     * @param date the date to check for free spaces
     * @return the number of free spaces available on the given date
     */
    public int getFreeSpace(Date date) {
        int freeSpace = 10; //number of all free spaces
        for (KennelBooking booking : bookings) //loop is checking every booking from booking list
        {
            if (date.isDateBetween(booking.getStartDate(), booking.getEndDate())) //checking the date is between the dates of booking
            {
                freeSpace -= 1; // it subtracts one kennel booking
            }
        }
        return freeSpace;
    }

    /**
     * Gets the number of free spaces available today.
     *
     * @return the number of free spaces available today
     */
    public int getFreeSpaceToday() {
        return getFreeSpace(Date.today());
    }

    /**
     * Checks if the kennel is free during the specified date range.
     *
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return true if there is available space for all dates in the range, otherwise false
     */
    public boolean isKennelFree(Date startDate, Date endDate)
    {
        Date startDateCopy = startDate.copy(); //make a copy of startDate
        while (!startDateCopy.equals(endDate)) //loop is checking every date between startDate and endDate
        {
            if (getFreeSpace(startDateCopy) == 0) //if during one of the days number of free spaces is 0 function return false
            {
                return false;
            }
            startDateCopy.addDay();
        }
        return true; // method return true if there is a free space and the loop finishes without returning false
    }
}
