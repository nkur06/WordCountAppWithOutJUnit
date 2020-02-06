package com.ankur.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ankur.app.design.WordCountContract;
import com.ankur.repo.Summary;
import com.ankur.util.Utility;

/**
 * @author nkur06
 *
 */
public class ExtractWordCountFromFile implements WordCountContract {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ankur.file.WordCountContract#extractWordCount(java.lang.String)
	 */
	public Map<String, Integer> extractWordCount(String filePath) {

		Map<String, Integer> wordAndCount = new HashMap<String, Integer>();
		Utility utility = new Utility();

		File file = new File(filePath);

		if (!file.exists()) {
			return wordAndCount;
		}
		try (FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.trim().isEmpty()) {

					String[] words = utility.extractWordsFromALine(line);
					for (int i = 0; i < words.length; i++) {

						if (!words[i].isEmpty()) {
							String word = words[i];
							Integer freq = wordAndCount.get(word);
							if (freq == null) {
								wordAndCount.put(word, 1);
							} else {
								wordAndCount.put(word, freq + 1);
							}
						}
					}
				}

			}

			if (wordAndCount.size() > 0) {

				int totNumOfWords = wordAndCount.values().stream().mapToInt(Integer::valueOf).sum();
				Summary.updateTotNumOfWords(totNumOfWords);
				System.out.println(
						"Total number of words in part:" + Thread.currentThread().getName() + " are:" + totNumOfWords);

				String max_key = Collections.max(wordAndCount.entrySet(), Map.Entry.comparingByValue()).getKey();
				System.out.println("Max used word in this part:" + Thread.currentThread().getName() + " is:" + max_key
						+ " and the count is:" + wordAndCount.get(max_key));
			}

			return wordAndCount;

		} catch (FileNotFoundException e) {
			System.out.println("Sorry, file not found at path:" + filePath + " Exception:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(
					"Sorry, Input Output Exception Occured at path:" + filePath + " Exception:" + e.getMessage());
			e.printStackTrace();
		}

		return null;

	}

}
