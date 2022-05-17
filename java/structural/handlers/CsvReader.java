package structural.handlers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A public class which handles extracting information from csv formatted files
 * 
 * class contains 4 private parameters and 5 methods, 
 * all associated with reading and extracting csv files
 * @author gianp
 *
 */
public class CsvReader {
	private String filePath;
	private FileReader fileReader;
	private BufferedReader lineReader;
	private String splitBy = ",";

	/**
	 * public method used for extracting the filepath where the csv is located
	 * @param filePath
	 */
	public CsvReader(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * public method which creates and return a lineReader object
	 * and return said object
	 * @return
	 */
	private BufferedReader getLinesReader() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader lineReader = new BufferedReader(fileReader);

		return lineReader;
	}

	/**
	 * public method which extracts and returns the lines from a csv in list of strings format.
	 * @return
	 */
	public String[] getLinesFromCsv() {

		BufferedReader lineReader = getLinesReader();
		int numberLines = getNumberOfLinesCsv();

		String[] lines = new String[numberLines];
		try {
			for (int i_line = 0; i_line < numberLines; i_line++) {
				String line = lineReader.readLine();

				lines[i_line] = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			lineReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	/**
	 * public method which loops over a number of lines in the csv, then splits line by commas into elements
	 * returns a arrayList of strings containing elements from the line
	 * @return
	 */
	public List<String[]> getElementsFromCsv() {

		BufferedReader lineReader = getLinesReader();
		int numberLines = getNumberOfLinesCsv();

		List<String[]> elements = new ArrayList<String[]>();

		try {
			for (int i_line = 0; i_line < numberLines; i_line++) {
				String line = lineReader.readLine();
				String[] element = line.split(splitBy);

				elements.add(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			lineReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return elements;
	}
	/**
	 * private method whihc extracts the number of lines from a csv into a integer
	 * @return
	 */
	private int getNumberOfLinesCsv() {
		BufferedReader lineReaderCounter = getLinesReader();
		int numberLines = 0;
		try {
			while (lineReaderCounter.readLine() != null) {
				numberLines++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			lineReaderCounter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numberLines;
	}

}
