package utils;

import java.io.*;

/**
 * A utility class for handling file operations, including writing and reading text and binary files.
 * This class provides static methods for writing to and reading from files, making file I/O operations easier.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class FileManager {

    /**
     * Writes the given string to a text file.
     * If the file already exists, it will be overwritten.
     *
     * @param fileName the name of the file to write to
     * @param str      the string to write to the file
     * @throws FileNotFoundException
     */
    public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, false);
            writeToFile = new PrintWriter(fileOutStream);
            writeToFile.println(str);
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    /**
     * Writes the given object to a binary file.
     *
     * @param fileName the name of the file to write to
     * @param obj      the object to be written to the file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    /**
     * Reads the first object from a binary file and returns it.
     * The caller must cast the returned object to its actual type.
     *
     * @param fileName the name of the file to read from
     * @return the object read from the file, or  null if the file is empty
     * @throws FileNotFoundException   if the file does not exist
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try {
                obj = readFromFile.readObject();
            } catch (EOFException eof) {
                // End of file reached
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }
}
