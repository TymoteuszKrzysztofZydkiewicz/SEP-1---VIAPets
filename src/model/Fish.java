package model;

/**
 * A class representing a Fish, extending the Pet class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */

public class Fish extends Pet
{
    // Private instance variables for fish
    private boolean isPredator; //Is the fish a predator.
    private String habitat;// Name of the habitat the fish live in.
    private String species;// name of the fish species.
    /**
     * 10 argument Constructor for creating fish objects.
     * Parameters:
     *
     * @param color       - fish color,
     * @param age         - fish age,
     * @param gender      - fish gender,
     * @param name        - fish name,
     * @param comment     - extra information about fish
     * @param type        - is fish from kennel or is for sale
     * @param price       - the price for the fish for sale,
     * @param available   - is fish for sale available,
     * @param isPredator  - is the fish a predator,
     * @param habitat     - name of the habitat the fish live in,
     * @param species     - name of the species of the fish.
     */
    public  Fish (String color, int age, String gender, String name, String comment,
                  boolean type, double price, boolean available, boolean isPredator, String habitat, String species)
    {
        // Call the constructor of the superclass (Pet) with specified parameters.
        super(color, age, gender, name, comment, type, price,  available);
        // Set Habitat of the fish
        this.setHabitat(habitat);
        // Set if the fish is a predator
        this.setIsPredator(isPredator);
        // Set species of the fish
        this.setSpecies(species);
    }


    /**
     * Setter method for species of the fish.
     *
     * @param species
     */
    public void setSpecies(String species) {
        this.species = species;
    }
    /**
     * Setter method for habitat of the fish.
     *
     * @param habitat
     */

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    /**
     * Setter method for is predator.
     *
     * @param isPredator
     */

    public void setIsPredator(boolean isPredator) {
        this.isPredator = isPredator;
    }
    /**
     * Creates a copy of this fish object.
     *
     * @return a new fish object with the same attributes
     */

    public Fish copy() {
        return new Fish(getColor(), getAge(), getGender(), getName(), getComment(), getTypeBoolean(), getPrice(),  getAvailableBoolean(), this.isPredator, this.habitat, this.species);

    }
    /**
     * Getter method for habitat
     *
     * @return Habitat
     */

    public String getHabitat() {
        return habitat;
    }
    /**
     * Getter method for species
     *
     * @return species
     */
    public String getSpecies() {
        return species;
    }
    /**
     * Getter method for is predator
     *
     * @return isPredator as a String
     */
    public String getIsPredator()
    {
        if(isPredator)
        {
            return "Yes";
        }
        return "No";
    }
    /**
     * Getter method for is predator
     *
     * @return isPredator as a boolean
     */
    public boolean getIsPredatorBoolean()
    {
        return isPredator;
    }
    /**
     * Override toString method inheriting from Pet class to provide a string representation of the fish object
     *
     * @return A formatted string of the information of the fish.
     */

    @Override
    public String toString() {
        return "Fish{" +
                "isPredator=" + isPredator +
                ", habitat='" + habitat + '\'' +
                ", species='" + species + '\'' +
                "} " + super.toString();
    }

    /**
     * Equals method inheriting from Pet class to compare if fishes objects are the same.
     * @param object Object to compare.
     * @return True if the rodents are the same, false if they're not.
     */
    public boolean equals(Object object)
    {
        // Check if the object is null or of a different class
        if (object==null || getClass() != object.getClass())
        {
            return false;
        }
        // makes a copy
        Fish newFish = (Fish) object;
        // compare instance variables for equality
        return super.equals(newFish) && isPredator == newFish.isPredator && this.species.equals(newFish.species) && this.habitat.equals(newFish.habitat);
    }
}

