package model;

/**
 * A class representing a rodent, extending the Pet class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */

public class Rodent extends Pet
{
    // Private instance variables for rodent
    private boolean doTheyBite;// if the rodent bites or not
    private String species;// name of the rodent species
    /**
     * 10 argument Constructor for creating rodent objects.
     * Parameters:
     *
     * @param color       - rodent color,
     * @param age         - rodent age,
     * @param gender      - rodent gender,
     * @param name        - rodent name,
     * @param comment     - extra information about rodent
     * @param type        - is rodent from kennel or is for sale
     * @param price       - the price for the rodent for sale,
     * @param available   - is rodent for sale available,
     * @param doTheyBite  - is the rodent biting
     * @param species     - name of the species of the rodent.
     */

    public Rodent (String color, int age, String gender, String name, String comment,
                   boolean type, double price,  boolean available, String species, boolean doTheyBite)
    {
        // Call the constructor of the superclass (Pet) with specified parameters.
        super(color, age, gender, name, comment, type, price, available);
        // Set spieces of the rodent
        this.setSpieces(species);
        // Set if they bite
        this.setDoTheyBite(doTheyBite);
    }

    /**
     * Setter method if the fish is biting.
     *
     * @param doTheyBite
     */

    public void setDoTheyBite(boolean doTheyBite) {
        this.doTheyBite = doTheyBite;
    }
    /**
     * Setter method for species of the rodent.
     *
     * @param species
     */
    public void setSpieces(String species) {
        this.species = species;
    }
    /**
     * Getter method for species of the rodent
     *
     * @return species.
     */
    public String getSpecies() {
        return species;
    }
    /**
     * Getter method for doTheyBite
     *
     * @return doTheyBite as a String
     */
    public String getDoTheyBite()
    {
        if(doTheyBite)
        {
            return "Yes";
        }
        return "No";
    }

    /**
     * Getter method for doTheyBite
     *
     * @return doTheyBite as a boolean
     */
    public boolean getDoTheyBiteBoolean()
    {
        return doTheyBite;
    }
    /**
     * Creates a copy of this rodent object.
     *
     * @return a new rodent object with the same attributes
     */

    public Rodent copy() {
        return new Rodent (getColor(), getAge(), getGender(), getName(), getComment(), getTypeBoolean(), getPrice(), getAvailableBoolean(), getSpecies(), getDoTheyBiteBoolean());
    }

    /**
     * Equals method inheriting from Pet class to compare if rodents objects are the same.
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
        Rodent newRodent = (Rodent) object;
        // compare instance variables for equality
        return super.equals(newRodent) && doTheyBite == newRodent.doTheyBite && this.species.equals(newRodent.getSpecies());
    }

    /**
         * Override toString method inheriting from Pet class to provide a string representation of the rodent object
         *
         * @return A formatted string of the information of the rodent.
         */

    @Override
    public String toString() {
        return "Rodent{" +
                "doTheyBite=" + doTheyBite +
                ", species='" + species + '\'' +
                "} " + super.toString();
    }
}
