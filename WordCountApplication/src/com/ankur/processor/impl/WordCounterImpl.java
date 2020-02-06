package com.ankur.processor.impl;

import java.io.IOException;
import java.util.List;

import com.ankur.file.FileCleaner;
import com.ankur.file.FileSplitterByLine;
import com.ankur.printer.Printer;
import com.ankur.processor.design.WordCounter;
import com.ankur.repo.Summary;

/**
 * @author nkur06
 *
 */
public class WordCounterImpl implements WordCounter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ankur.processor.WordCounter#startCounting(java.lang.String, int)
	 */
	public void startCounting(String filePath, int maxNoOfLines) {

		try {

			List<String> listOfSplittedFiles = FileSplitterByLine.splitFile(filePath, maxNoOfLines);
			ParallelProcess parallerProcessing = new ParallelProcess();
			parallerProcessing.startParallelProcessing(listOfSplittedFiles);
			Printer print = new Printer();
			print.printSummary(Summary.getConsolidatedWordAndCount());
			FileCleaner.cleanDisk();

		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Sorry, some  input output exception occured. Exception:" + e.getMessage());
			e.printStackTrace();
		}

	}

}
