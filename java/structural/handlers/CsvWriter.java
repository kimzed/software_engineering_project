package structural.handlers;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * public class which uses created objects and writes into a csv formatted file.
 * it contains 2 private parameters and 3 methods
 * 
 * @author gianp
 *
 */
public class CsvWriter {

	private CSVWriter writer;
	private String filePath;

	/**
	 * public method which takes a filePath and boolean value to find and write to a
	 * csv file if boolean value is false, the file will be created, if boolean
	 * value is true, the info will be appended to the file
	 * 
	 * @param filePath
	 * @param appendToFile
	 */
	public CsvWriter(String filePath, boolean appendToFile) {
		this.filePath = filePath;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filePath, appendToFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
		CSVWriter csvWriter = new CSVWriter(outputStreamWriter, CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		this.writer = csvWriter;
	}

	/**
	 * public method which uses a list of strings containing a single entrie, and
	 * writes these to a csv file
	 * 
	 * @param entries
	 */
	public void writeEntryToFile(String[] entries) {
		writer.writeNext(entries);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * public method which uses a list of string arrays containing multiple entries,
	 * and writes these to a csv file
	 * 
	 * @param entries
	 */
	public void writeEntriesToFile(List<String[]> entries) {
		writer.writeAll(entries);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
