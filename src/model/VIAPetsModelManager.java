package model;

import utils.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
* A class containing a VIAPets object and the methods necessary to read and write its elements to files
* @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
* @version 1.0
*/
public abstract class VIAPetsModelManager {
    private static VIAPets viaPets;

/**
 * The static code below reads all the data stored in files and puts it into the VIAPets object
 */
    static
    {
        viaPets = new VIAPets();
        try{
            Object pets = FileManager.readFromBinaryFile("savedInformation/pets.bin");
            if (pets != null && pets.getClass() == viaPets.getPetList().getClass()) {
                PetList petsCopy = (PetList) pets;
                viaPets.setPetList(petsCopy);
                updatePetsXML();
            }
        }catch(IOException | ClassNotFoundException error){
            System.out.println("Error reading file");
        }

        try{
            Object sales = FileManager.readFromBinaryFile("savedInformation/sales.bin");
            if (sales != null && sales.getClass() == viaPets.getSaleList().getClass()) {
                SaleList salesCopy = (SaleList) sales;
                viaPets.setSaleList(salesCopy);
            }
        }catch(IOException | ClassNotFoundException error){
            System.out.println("Error reading file");
        }

        try{
            Object bookings = FileManager.readFromBinaryFile("savedInformation/bookings.bin");
            if (bookings != null && bookings.getClass() == viaPets.getBookingList().getClass()) {
                BookingList bookingsCopy = (BookingList) bookings;
                viaPets.setBookingList(bookingsCopy);
            }
        }catch(IOException | ClassNotFoundException error){
            System.out.println("Error reading file");
        }

        try{
            Object customers = FileManager.readFromBinaryFile("savedInformation/customers.bin");
            if (customers != null && customers.getClass() == viaPets.getCustomerList().getClass()) {
                CustomerList customersCopy = (CustomerList) customers;
                viaPets.setCustomerList(customersCopy);
            }
        }catch(IOException | ClassNotFoundException error){
            System.out.println("Error reading file");
        }

        try {
            updateGetFreeSpaceToday();
        } catch (IOException error) {
            System.out.println("Error writing to file");
        }
    }


/**
 * Updates the pets.bin file with the pet data currently stored in viaPets
 * @throws IOException
 */
    public static void updatePetsFile() throws IOException {
        try {
            FileManager.writeToBinaryFile("savedInformation/pets.bin", viaPets.getPetList());
            updatePetsXML();
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

/**
 * Updates the sales.bin file with the sales data currently stored in viaPets
 * @throws IOException
 */
    public static void updateSalesFile() throws IOException {
        try {
            FileManager.writeToBinaryFile("savedInformation/sales.bin", viaPets.getSaleList());
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

/**
 * Updates the customers.bin file with the customer data currently stored in viaPets
 * @throws IOException
 */
    public static void updateCustomersFile() throws IOException {
        try {
            FileManager.writeToBinaryFile("savedInformation/customers.bin", viaPets.getCustomerList());
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

/**
 * Updates the bookings.bin file with the booking data currently stored in viaPets
 * @throws IOException
 */
    public static void updateBookingsFile() throws IOException {
        try {
            FileManager.writeToBinaryFile("savedInformation/bookings.bin", viaPets.getBookingList());
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }


    /**
     * Updates the getFreeSpace.txt file with the information about free space in kennel booking for today.
     * @throws IOException
     */


    public static void updateGetFreeSpaceToday() throws IOException
    {
        try{
            FileManager.writeToTextFile("savedInformation/getFreeSpace.txt",Integer.toString(viaPets.getBookingList().getFreeSpaceToday()));
        } catch (IOException error)
        {
            throw new RuntimeException(error);
        }
    }
    /**
     * Generates an XML file with details of the pets available for sale and updates the file.
     *
     * @throws IOException
     */

    public static void updatePetsXML() throws IOException
    {
        int numberOfIterations = 8;
        ArrayList<Pet> petList = PetList.filterAvailablePets(PetList.filterPets(viaPets.getPetList().getAllPets(), "For sale"));

        String textToWrite = "";
        if(petList.size() < numberOfIterations)
        {
            numberOfIterations = petList.size();
        }
        textToWrite += ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        textToWrite += ("<pets>\n");
        for(int i = 0; i < numberOfIterations; i++)
        {
            Pet currentPet = petList.get(i);
            String species = switch (currentPet.getPetClassName()) {
                case "Cat" -> ((Cat) currentPet).getBreed();
                case "Dog" -> ((Dog) currentPet).getBreed();
                case "Rodent" -> ((Rodent) currentPet).getSpecies();
                case "Bird" -> ((Bird) currentPet).getSpecies();
                case "Fish" -> ((Fish) currentPet).getSpecies();
                default -> "";
            };
            textToWrite += ("<" + currentPet.getPetClassName() +">\n");
            textToWrite += ("\t<species>" + species + "</species>\n");
            textToWrite += ("\t<name>" + currentPet.getName() + "</name>\n");
            textToWrite += ("\t<age>" + currentPet.getAge() + "</age>\n");
            textToWrite += ("\t<color>" + currentPet.getColor() + "</color>\n");
            textToWrite += ("\t<gender>" + currentPet.getGender() + "</gender>\n");
            textToWrite += ("\t<price>" + currentPet.getPrice() + "</price>\n");
            textToWrite += ("</" + currentPet.getPetClassName() + ">\n");
        }
        textToWrite += ("</pets>\n");

        FileManager.writeToTextFile("savedInformation/petsForWebsite.xml", textToWrite);
    }
    /**
     * Getter method for ViaPets.
     *
     * @return viaPets.
     */
    public static VIAPets getViaPets() {
        return viaPets;
    }
}
