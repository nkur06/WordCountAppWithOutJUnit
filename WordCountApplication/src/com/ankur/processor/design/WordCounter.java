package com.ankur.processor.design;

/**
 * @author nkur06
 *
 */
public interface WordCounter {
	/**
	 * @param filePath
	 * @param maxNoOfLines
	 */
	void startCounting(String filePath, int maxNoOfLines);
}
