package model;

/**
 * A class representing a bird, extending the Pet class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class Bird extends Pet

{
    // Private instance variables for bird
    private String preferredFood; //food preferred by Pet
    private String species; //Pet species

    /**
     * 10 argument Constructor for creating bird objects.
     * Parameters:
     *
     * @param color       - bird color,
     * @param age         - bird age,
     * @param gender      - bird gender,
     * @param name        - bird name,
     * @param comment     - extra information about bird
     * @param type        - is bird from kennel or is for sale
     * @param price       - the price for the bird for sale,
     * @param available   - is bird for sale available,
     * @param species - Name of species of the bird.
     * @param preferredFood      - Name of the preferred food by the bird.
     */

    public Bird (String color, int age, String gender, String name, String comment,
                 boolean type, double price,  boolean available, String preferredFood, String species)
    {
        // Call the constructor of the superclass (Pet) with specified parameters.
        super(color, age, gender, name, comment, type, price,  available);
        // set the species of the bird.
        this.setSpecies(species);
        // set the preferred food by the bird.
        this.setPrefferedFood(preferredFood);
    }
    /**
     * Setter method for food preferred by bird
     *
     * @param preferredFood
     */

    public void setPrefferedFood(String preferredFood) {
        this.preferredFood = preferredFood;
    }
    /**
     * Setter method for bird's species
     *
     * @param species
     */

    public void setSpecies(String species) {
        this.species = species;
    }
    /**
     * Creates a copy of this bird object.
     *
     * @return a new bird object with the same attributes
     */

    public Bird copy() {
        return new Bird (getColor(), getAge(), getGender(), getName(), getComment(), getTypeBoolean(), getPrice(), getAvailableBoolean(), getPreferredFood(), getSpecies());
    }

    /**
     * Getter method for species of the bird.
     *
     * @return species
     */
    public String getSpecies()
    {
        return species;
    }
    /**
     * Getter method for preferred food by bird.
     *
     * @return preferredFood
     */

    public String getPreferredFood()
    {
        return preferredFood;
    }
    /**
     * Override equals method inheriting from Pet class to compare dogs for equality.
     * @param object Object to compare.
     * @return True if the birds are the same, false if they're not.
     */

    public boolean equals(Object object)
    {
        // Check if the object is null or of a different class
        if (object==null || getClass() != object.getClass())
        {
            return false;
        }
        // makes a copy
        Bird newBird = (Bird) object;
        // compare instance variables for equality
        return super.equals(newBird) && preferredFood.equals(newBird.preferredFood) && this.species.equals(newBird.getSpecies());
    }


    /**
     * Override toString method inheriting from Pet class to provide a string representation of the bird object
     *
     * @return A formatted string of the bird's information.
     */

    @Override
    public String toString() {
        // Return a formatted string with details about the bird object
        return "Bird{" +
                "preferredFood='" + preferredFood + '\'' +
                ", species='" + species + '\'' +
                "} " + super.toString();
    }
}
