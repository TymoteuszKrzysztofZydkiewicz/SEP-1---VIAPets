package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of pets.
 * This class uses an ArrayList to store and manage Pet objects.
 * It provides methods for adding, removing, retrieving, and editing pets in the list.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class PetList implements Serializable {
    private ArrayList<Pet> pets; //ArrayList to store all pets

    /**
     * Constructs an empty PetList.
     */
    public PetList() {
        this.pets = new ArrayList<Pet>();
    }

    /**
     * Constructs a PetList with an existing list of pets.
     *
     * @param pets the initial list of pets
     */
    public PetList(ArrayList<Pet> pets) {
        this.pets = new ArrayList<Pet>(pets);
    }

    /**
     * Adds a pet to the list.
     *
     * @param pet the pet to be added
     */
    public void addPet(Pet pet) {
        if(!pets.contains(pet)) {
            pets.add(pet);
        }
    }

    /**
     * Removes a pet from the list.
     *
     * @param pet the pet to be removed
     */
    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    /**
     * Gets the index of a specific pet in the list.
     *
     * @param pet the pet whose index is to be retrieved
     * @return the index of the pet in the list, or  "-1" if not found
     */
    public int getIndex(Pet pet) {
        return pets.indexOf(pet);
    }

    /**
     * Edits an existing pet by replacing it with a new pet.
     *
     * @param petToEdit the pet to be edited
     * @param newPet    the new pet to replace the old one
     */
    public void editPet(Pet petToEdit, Pet newPet) {
        pets.set(this.getIndex(petToEdit), newPet);
    }

    /**
     * Gets the list of all pets.
     *
     * @return an ArrayList of Pet objects
     */
    public ArrayList<Pet> getAllPets() {
        return pets;
    }

    /**
     * Gets list of all pets with entered className.
     *
     * @param className the class name to filter pets by
     * @return an ArrayList of Pet objects with entered className
     */
    public ArrayList<Pet> getPetsByClassName(String className) {
        ArrayList<Pet> petsWithClass = new ArrayList<Pet>();
        for(Pet pet : pets) {
            if(pet.getPetClassName().equals(className)) {
                petsWithClass.add(pet);
            }
        }
        return petsWithClass;
    }

    /**
     * Filters pets by a specific type from a list.
     *
     * @param petsList the list of pets to filter
     * @param type     the type of pets to retain
     * @return an ArrayList of Pet objects that match the specified type
     */
    public static ArrayList<Pet> filterPets(ArrayList<Pet> petsList, String type)
    {
        ArrayList<Pet> petsListCopy = new ArrayList<Pet>(petsList);
        petsListCopy.removeIf(pet -> !pet.getType().equals(type));
        return petsListCopy;
    }

    /**
     * Retrieves a pet by its name from a  list.
     *
     * @param petsList the list of pets to search
     * @param name     the name of the pet to find
     * @return the Pet object with the specified name, or null if not found
     */
    public static Pet getPetByName(ArrayList<Pet> petsList, String name) {
        for(Pet pet : petsList) {
            if(pet.getName().equals(name)) {
                return pet;
            }
        }
        return null;
    }

    /**
     * Filters pets that are marked as available from a list.
     *
     * @param petsList the list of pets to filter
     * @return an ArrayList of Pet objects that are available
     */
    public static ArrayList<Pet> filterAvailablePets(ArrayList<Pet> petsList) {
        ArrayList<Pet> availablePets = new ArrayList<Pet>();
        for(Pet pet : petsList) {
            if(pet.getAvailableBoolean())
            {
                availablePets.add(pet);
            }
        }
        return availablePets;
    }
}
