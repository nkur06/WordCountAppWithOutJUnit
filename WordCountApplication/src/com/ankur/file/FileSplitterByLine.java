package com.ankur.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ankur.config.Configuration;

/**
 * @author nkur06
 *
 */
public class FileSplitterByLine {

	/**
	 * Split a file into multiples files.
	 *
	 * @param fileName     Name of file to be split.
	 * @param maxNoOfLines maximum number of lines to be created per file.
	 * @throws IOException
	 */
	public static List<String> splitFile(final String filePath, final int maxNoOfLines)
			throws IOException, IllegalArgumentException {
		File inputFile = new File(filePath);
		if (maxNoOfLines <= 0) {
			throw new IllegalArgumentException("maxNoOfLines must be more than zero");
		}

		else if (!inputFile.exists()) {

			throw new IllegalArgumentException("File:" + filePath + " is not present");

		}

		List<String> partFiles = new ArrayList<>();
		long linesWritten = 0;
		int count = 1;

		try {

			InputStream inputFileStream = new BufferedInputStream(new FileInputStream(inputFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileStream));

			String line = reader.readLine();

			String fileName = inputFile.getName();
			Files.createDirectories(Paths.get(Configuration.DIR));
			String outfileName = Configuration.DIR + File.separator + fileName;

			while (line != null) {

				File outFile = new File(outfileName + "_" + count + Configuration.SPLIT);

				partFiles.add(outFile.getAbsolutePath());
				Writer writer = new OutputStreamWriter(new FileOutputStream(outFile));

				while (line != null && linesWritten < maxNoOfLines) {
					writer.write(line);
					writer.write(System.lineSeparator());
					line = reader.readLine();
					linesWritten++;
				}

				writer.close();
				linesWritten = 0;
				count++;

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("Sorry, some exception occured  at path:" + filePath + " Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return partFiles;

	}

}
