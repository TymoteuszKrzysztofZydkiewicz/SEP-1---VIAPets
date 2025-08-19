package model;

public class Cat extends Pet
/**
 * A class representing a cat, extending the Pet class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
{
    // Private instance variables for cat
    private String breederName;//Name of the breeder.
    private String breed; //Name of the breed.
    /**
     * 10 argument Constructor for creating cat objects.
     * Parameters:
     *
     * @param color       - cat color,
     * @param age         - cat age,
     * @param gender      - cat gender,
     * @param name        - cat name,
     * @param comment     - extra information about cat
     * @param type        - is cat from kennel or is for sale
     * @param price       - the price for the cat for sale,
     * @param available   - is cat for sale available,
     * @param breederName - Name of the breeder.
     * @param breed       - Name of the breed.
     */

    public Cat (String color, int age, String gender, String name, String comment,
                boolean type, double price,  boolean available, String breederName, String breed)
    {
        // Call the constructor of the superclass (Pet) with specified parameters.
        super(color, age, gender, name, comment, type, price, available);
        //Set Breed of the cat.
        this.setBreed(breed);
        //Set Breeder Name the cat
        this.setBreederName(breederName);
    }

    /**
     * Setter method for cat's breed
     *
     * @param breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }
    /**
     * Setter method breeder name
     *
     * @param breederName
     */
    public void setBreederName(String breederName) {
        this.breederName = breederName;
    }
    /**
     * Getter method for breeder name
     *
     * @return breederName
     */
    public String getBreederName() {
        return breederName;
    }
    /**
     * Getter method for Breed.
     *
     * @return breed
     */
    public String getBreed() {
        return breed;
    }
    /**
     * Creates a copy of this cat object.
     *
     * @return a new fish object with the same attributes
     */

    public Cat copy() {
        return new Cat(getColor(), getAge(), getGender(), getName(), getComment(), getTypeBoolean(), getPrice(),  getAvailableBoolean(), getBreederName(), getBreed());
    }
    /**
     * Override toString method inheriting from Pet class to provide a string representation of the cat object
     *
     * @return A formatted string of the cat's information.
     */
    @Override
    public String toString() {
        return "Cat{" +
                "breederName='" + breederName + '\'' +
                ", breed='" + breed + '\'' +
                "} " + super.toString();
    }
    /**
     * Equals method inheriting from Pet class to compare if cats objects are the same.
     * @param object Object to compare.
     * @return True if the cats are the same, false if they're not.
     */

    public boolean equals(Object object)
    {
        // Check if the object is null or of a different class
        if (object==null || getClass() != object.getClass())
        {
            return false;
        }
        // makes a copy
        Cat newCat = (Cat) object;
 
        // compare instance variables for equality

        return super.equals(newCat) && breed.equals(newCat.breed) && this.breederName.equals(newCat.getBreederName());

    }

}
