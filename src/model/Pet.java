package model;

import java.io.Serializable;
import java.util.Objects;
/**
 * abstract class representing a pet
 *
 * @author  Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0- December 2024
 **/

public abstract class Pet implements Serializable
{
    // instance variables for a pet
    private String color;// pet's color
    private int age;// pet's age
    private String gender;// pet's gender
    private String name;// pet's name
    private String comment;// pet's comment
    private boolean type;// pet's type (if the pet is from kennel or for sale)
    private double price;// pet's price (if it is for sale)
    private boolean available;// pet's available (only for pets for sale)
    /**
     * 10 argument Constructor for creating pet objects.
     * Parameters:
     *
     * @param color       - pet color,
     * @param age         - pet age,
     * @param gender      - pet gender,
     * @param name        - pet name,
     * @param comment     - extra information about pet
     * @param type        - is pet from kennel or is for sale
     * @param price       - the price for the pet for sale,
     * @param available   - is pet for sale available,
     */
    public Pet (String color, int age, String gender, String name, String comment,
        boolean type, double price, boolean available)
    {
        // initialize instance variables with provided values
        this.setColor(color);
        this.setAge(age);
        this.setGender(gender);
        this.setName(name);
        this.setComment(comment);
        this.setType(type);
        this.setPrice(price);
        this.setAvailable(available);
    }
    /** Setter method for pet color
     * @param color */
    public void setColor(String color)
    {
        this.color = color;
    }
    /** Setter method for pet age
     * @param age */
    public void setAge(int age)
    {
        this.age = age;
    }
    /** Setter method for pet availability
     * @param available */
    public void setAvailable(boolean available)
    {
        this.available = available;
    }
    /** Setter method for pet comment
     * @param comment*/
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    /** Setter method for pet gender
     * @param gender */
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    /** Setter method for pet name
     * @param name */
    public void setName(String name)
    {
        this.name = name;
    }
    /** Setter method for pet price
     * @param price */
    public void setPrice(double price)
    {
        this.price = price;
    }
    /** Setter method for pet type
     * @param type */
    public void setType(boolean type)
    {
        this.type = type;
    }
    /** Getter method for pet name
     * @return name */
    public String getName()
    {
        return name;
    }
    /** Getter method for pet price
     * @return price */
    public double getPrice()
    {
        return price;
    }
    /** Getter method for pet age
     * @return age */
    public int getAge()
    {
        return age;
    }
    /** Getter method for pet color
     * @return color */
    public String getColor()
    {
        return color;
    }
    /** Getter method for pet comment
     * @return comment */
    public String getComment()
    {
        return comment;
    }
    /** Getter method for pet gender
     * @return gender */
    public String getGender()
    {
        return gender;
    }
    /** Getter method for pet availability
     * @return available */
    public String getAvailable()
    {
        if(available)
        {
            return "Yes";
        }
        return "No";
    }

    /** Getter method for pet availability
     * @return available as a boolean */
    public boolean getAvailableBoolean()
    {
        return available;
    }
    /** Getter method for pet type
     * @return type as a String */
    public String getType()
    {
        if(type)
        {
            return "Kennel";
        }
        return "For sale";
    }

    /** Getter method for pet type
     * @return type as a boolean */
    public Boolean getTypeBoolean()
    {
        return type;
    }

    /** Method which returns the simple className
     * @return simple className */
    public String getPetClassName()
    {
        return this.getClass().getSimpleName();
    }
    /**
     * toString method from Pet class to provide a string representation of the pet object
     *
     * @return A formatted string of the pet's information.
     */

    public String toString() {
        return " color='" + color + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", available=" + available;
    }
    /**
     * Equals method to compare pets for equality.
     *
     * @param object Object to compare.
     * @return True if the pets are equal, false if they're not.
     */
    public boolean equals(Object object) {
        // Check if the object is null or of a different class
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        // makes a copy
        Pet pet = (Pet) object;
        // compare instance variables for equality
        return age == pet.age && type == pet.type && Double.compare(price, pet.price) == 0 && available == pet.available && Objects.equals(color, pet.color) && Objects.equals(gender, pet.gender) && Objects.equals(name, pet.name) && Objects.equals(comment, pet.comment);
    }
    /**
     * Abstract method to be implemented by subclasses, creates a copy of this pet object.
     *
     * @return a new pet object with the same attributes
     */
    public abstract Pet copy();


}
