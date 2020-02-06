package com.ankur.repo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nkur06
 *
 */
public class Summary {
	private static int totNumOfWords;
	private static final Map<String, Integer> consolidatedWordCount = new HashMap<String, Integer>();

	/**
	 * @return
	 */
	public static Map<String, Integer> getConsolidatedWordAndCount() {
		return consolidatedWordCount;
	}

	public static int getTotNumOfWords() {
		return totNumOfWords;
	}

	/**
	 * @param numOfWords
	 */
	public static void updateTotNumOfWords(int numOfWords) {
		totNumOfWords = totNumOfWords + numOfWords;
	}

	/**
	 * @param tempWordAndCount
	 */
	public static void deepCopyHashMap(Map<String, Integer> tempWordAndCount) {
		tempWordAndCount.forEach((key, value) -> {
			Integer freq = consolidatedWordCount.get(key);
			if (freq == null) {
				consolidatedWordCount.put(key, value);
			} else {
				consolidatedWordCount.put(key, freq + value);
			}

		});

	}
}
