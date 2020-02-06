package com.ankur.app.design;

import java.util.Map;

/**
 * @author nkur06
 *
 */
public interface WordCountContract {
	/**
	 * @param filePath
	 * @return
	 */
	public Map<String, Integer> extractWordCount(String filePath);
}
