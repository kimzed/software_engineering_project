package structural.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * public class used for utility in other classes
 * used for creating paths, deleting elements from csv, obtaining indexes,
 * creating files, directories and deleting files
 * @author gianp
 *
 */
public class Utility {
	/**
	 * public static method which deletes specific elements from a csv
	 * @param filePath
	 * @param element
	 */
	public static void deleteElementFromCsv(String filePath, String[] element) {
		CsvReader csvReader;
		List<String[]> rows = null;

		csvReader = new CsvReader(filePath);
		rows = csvReader.getElementsFromCsv();

		int rowNumber = getIndexfromElement(rows, element);

		rows.remove(rowNumber);
		boolean appendingMode = false;
		CsvWriter writer;

		writer = new CsvWriter(filePath, appendingMode);
		writer.writeEntriesToFile(rows);

	}
	/**
	 * public static method which checks if an element is already present in an object
	 * returns a boolean value indicating presence of element
	 * @param <T>
	 * @param element
	 * @param elements
	 * @return
	 */
	public static <T> boolean elementAlreadyExists(StringInformationObject element,
			List<? extends StringInformationObject> elements) {

		for (int i_element = 0; i_element < elements.size(); i_element++) {
			StringInformationObject elementFromList = elements.get(i_element);
			String[] Input = element.getInformationAsArray();
			String[] Reference = elementFromList.getInformationAsArray();
			boolean membersAreSame = Arrays.equals(Input, Reference);
			if (membersAreSame) {
				return true;
			}
		}
		return false;
	}
	/**
	 * public static method which returns indices from an element
	 * @param <T>
	 * @param elements
	 * @param element
	 * @return
	 */
	public static <T> int getIndexfromElement(List<T[]> elements, T[] element) {

		for (int i_element = 0; i_element < elements.size(); i_element++) {
			T[] element_list = elements.get(i_element);
			boolean elementsAreSame = Arrays.equals(element_list, element);

			if (elementsAreSame) {
				return i_element;
			}
		}
		return -1;
	}
	
	public static <T> int getIndexfromElement(List<? extends StringInformationObject> elements,
			String[] element) {

		for (int i_element = 0; i_element < elements.size(); i_element++) {
			StringInformationObject elementList = elements.get(i_element);
			boolean elementsAreSame = Arrays.equals(elementList.getInformationAsArray(), element);

			if (elementsAreSame) {
				return i_element;
			}
		}
		return -1;
	}
	
	
	/**
	 * public static method which checks the amount of rows in a csv
	 * returns a integer indicating the row count
	 * @param filePath
	 * @return
	 */
	public static int getNumberOfRowsCsv(String filePath) {
		CsvReader csvReader = null;
		csvReader = new CsvReader(filePath);
		List<String[]> rows = null;
		rows = csvReader.getElementsFromCsv();
		int numberOfRows = rows.size();
		return numberOfRows;

	}
	/**
	 * public static method which adds given rows to a csv
	 * @param row
	 * @param participantsListPath
	 */
	public static void addRowToCsv(String[] row, String csvFile) {
		boolean appendToFile = true;
		CsvWriter csvwriter;
		csvwriter = new CsvWriter(csvFile, appendToFile);
		csvwriter.writeEntryToFile(row);

	}
	/**
	 * public static method which checks if a row is in a csv
	 * returns boolean value indicating the presence of the row
	 * @param <T>
	 * @param row
	 * @param filePath
	 * @return
	 */
	public static <T> boolean rowIsInCsv(String[] row, String filePath) {

		CsvReader csvReader;
		List<String[]> rows = null;

		csvReader = new CsvReader(filePath);
		rows = csvReader.getElementsFromCsv();

		if (getIndexfromElement(rows, row) == -1) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * public static method which creates a path
	 * @param pathRaw
	 */
	public static void createPath(String pathRaw) {
		File path = new File(pathRaw);

		if (path.isDirectory()) {
			createDirectory(pathRaw);
		} else {
			createFile(pathRaw);
		}

	}
	/**
	 * public static method which creates a file
	 * @param filePath
	 */
	public static void createFile(String filePath) {
		File file = new File(filePath);
		// this create the folders needed
		file.getParentFile().mkdirs();
		try {
			FileWriter writer = new FileWriter(file);
			writer.close();
		} catch (IOException e) {
			System.out.println("file path is not correct");
			e.printStackTrace();
		}

	}
	/**
	 * public static method which creates a directory
	 * @param directoryPath
	 */
	public static void createDirectory(String directoryPath) {
		File directory = new File(directoryPath);

		if (directory.mkdir() != true) {
			System.out.println("Directory cannot be created:"+directoryPath);
		}
	}
	/**
	 * public static method which deletes a file from given file path
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
		} else {
			System.out.println("Failed to delete the file:"+file.toString());
		}
	}
	/**
	 * public static method which deletes a file from given file
	 * @param file
	 */
	public static void deleteFile(File file) {

		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
		} else {
			System.out.println("Failed to delete the file:"+file.toString());
		}
	}
	/**
	 * public static method which deletes a directory
	 * @param directoryPath
	 */
	public static void deleteFilesFromDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		File[] listFiles = directory.listFiles();

		for (File file : listFiles) {
			deleteFile(file);
		}

	}

}
