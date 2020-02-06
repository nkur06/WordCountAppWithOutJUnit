package com.ankur.app;

import com.ankur.config.Configuration;
import com.ankur.processor.design.WordCounter;
import com.ankur.processor.impl.WordCounterImpl;

/**
 * @author nkur06
 *
 */
public class WordCountApp {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordCounter wordCounter = new WordCounterImpl();
		wordCounter.startCounting(Configuration.FILE_PATH, Configuration.MAX_LINES_PERMITTED_PER_FILE);
	}
}
