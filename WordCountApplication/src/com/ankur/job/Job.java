package com.ankur.job;

import java.util.Map;
import java.util.concurrent.Callable;

import com.ankur.file.ExtractWordCountFromFile;

/**
 * @author nkur06
 *
 */
public class Job implements Callable<Map<String, Integer>> {

	private String filePath;

	/**
	 * @param filePath
	 */
	public Job(String filePath) {
		this.filePath = filePath;

	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Map<String, Integer> call() throws Exception {
		ExtractWordCountFromFile extractWordCountFromFile = new ExtractWordCountFromFile();
		Map<String, Integer> hashMap = extractWordCountFromFile.extractWordCount(filePath);
		return hashMap;
	}

}
