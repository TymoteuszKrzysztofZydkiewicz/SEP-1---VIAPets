package model;


/**
 * A class representing a dog, extending the Pet class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */



public class Dog extends Pet {
    // Private instance variables for dog
    private String breederName; //Name of the breeder.
    private String breed; //Name of the breed.


    /**
     * 10 argument Constructor for creating dog objects.
     * Parameters:
     *
     * @param color       - dog color,
     * @param age         - dog age,
     * @param gender      - dog gender,
     * @param name        - dog name,
     * @param comment     - extra information about dog
     * @param type        - is dog from kennel or is for sale
     * @param price       - the price for the dog for sale,
     * @param available   - is dog for sale available,
     * @param breederName - Name of the breeder.
     * @param breed       - Name of the breed.
     */

    public Dog(String color, int age, String gender, String name, String comment,
               boolean type, double price, boolean available, String breederName, String breed) {
        // Call the constructor of the superclass (Pet) with specified parameters.
        super(color, age, gender, name, comment, type, price, available);
        //Set Breed of the dog.
        this.setBreed(breed);
        //Set Breeder name of the dog.
        this.setBreederName(breederName);

    }

    /**
     * Setter method for dog's breed
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
     * @return breederName.
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
     * Creates a copy of this dog object.
     *
     * @return a new dog object with the same attributes
     */

    public Dog copy() {
        return new Dog(getColor(), getAge(), getGender(), getName(), getComment(), getTypeBoolean(), getPrice(), getAvailableBoolean(), getBreederName(), getBreed());
    }

    /**
     * Override toString method inheriting from Pet class to provide a string representation of the dog object
     *
     * @return A formatted string of the dog's information.
     */

    @Override
    public String toString() {
        // Return a formatted string with details about the bird object
        return "Dog{" +
                "breederName='" + breederName + '\'' +
                ", breed='" + breed + '\'' +
                "} " + super.toString();
    }




    /**
     * Equals method inheriting from Pet class to compare if dogs objects are the same.
     * @param object Object to compare.
     * @return True if the dogs are the same, false if they're not.
     */

    public boolean equals(Object object)
    {
        // Check if the object is null or of a different class
        if (object==null || getClass() != object.getClass())
        {
            return false;
        }
        // makes a copy
        Dog newDog = (Dog) object;
        // compare instance variables for equality
        return super.equals(newDog) && breed.equals(newDog.breed) && this.breederName.equals(newDog.getBreederName());

    }


}