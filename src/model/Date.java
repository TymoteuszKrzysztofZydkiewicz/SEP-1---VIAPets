package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class representing a date with day, month, and year attributes.
 * This class includes functionality for creating, copying, comparing,
 * and manipulating dates.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class Date implements Serializable {
  private int day;   // Day of the month
  private int month; // Month of the year
  private int year;  // Year

  /**
   * 3 argument Constructor for creating date objects.
   * Parameters:
   *
   * @param day   the day of the month
   * @param month the month of the year
   * @param year  the year
   */
  public Date(int day, int month, int year) {
    this.setDay(day);
    this.setMonth(month);
    this.setYear(year);
  }

  /**
   * Gets the day of the date.
   *
   * @return day
   */
  public int getDay() {
    return day;
  }

  /**
   * Gets the month of the date.
   *
   * @return month
   */
  public int getMonth() {
    return month;
  }

  /**
   * Gets the year of the date.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets the day of the date.
   *
   * @param day
   */
  public void setDay(int day) {
    this.day = day;
  }

  /**
   * Sets the month of the date.
   *
   * @param month
   */
  public void setMonth(int month) {
    this.month = month;
  }

  /**
   * Sets the year of the date.
   *
   * @param year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Override toString method Returns a string representation of the object.
   *
   * @return a string in the format "Date{day=DD, month=MM, year=YYYY}"
   */
  @Override
  public String toString() {
    return day + "." + month + "." + year;
  }

  /**
   * Override equals method to compare dates for equality.
   *
   * @param object the object to compare with
   * @return true if the dates are equal, otherwise false
   */
  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Date date = (Date) object;
    return this.getDay() == date.getDay() &&
            this.getMonth() == date.getMonth() &&
            this.getYear() == date.getYear();
  }

  /**
   * Creates a Date object representing the current date.
   *
   * @return a new Date object with today's day, month, and year
   */
  public static Date today() {
    LocalDate currentDate = LocalDate.now();
    return new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
  }

  /**
   * Creates a copy of this Date object.
   *
   * @return a new Date object with the same day, month, and year
   */
  public Date copy() {
    return new Date(this.getDay(), this.getMonth(), this.getYear());
  }

  /**
   * Adds one day to this Date object.
   * The method handles month and year transitions correctly using LocalDate.
   */
  public void addDay() {
    LocalDate currentLocalDate = LocalDate.of(year, month, day);
    currentLocalDate = currentLocalDate.plusDays(1);
    this.day = currentLocalDate.getDayOfMonth();
    this.month = currentLocalDate.getMonthValue();
    this.year = currentLocalDate.getYear();
  }

  /**
   * Checks if this Date is between two other dates, inclusive.
   *
   * @param startDate the start date of the range
   * @param endDate   the end date of the range
   * @return true if this date is between startDate and  endDate, otherwise  false
   */
  public boolean isDateBetween(Date startDate, Date endDate) {
    Date startDateCopy = startDate.copy();
    while (!startDateCopy.equals(endDate)) {
      if (this.equals(startDateCopy)) {
        return true;
      }
      startDateCopy.addDay();
    }
    return false;
  }
  /**
   * Determines if this Date occurs before another date.
   *
   * @param date2 the date to compare against
   * @return true if this Date is before date2, false otherwise
   */
  public boolean isBefore (Date date2)
  {
    if (this.year < date2.year)
    {
      return true;
    }
    if (this.year == date2.year)
    {
      if (this.month < date2.month)
      {
        return true;
      }
      else if (this.month == date2.month)
      {
          return this.day < date2.day;
      }
    }
    return false;
  }


}
