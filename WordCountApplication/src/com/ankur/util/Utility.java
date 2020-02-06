package com.ankur.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ankur.config.Configuration;

import java.util.Map.Entry;

/**
 * @author nkur06
 *
 */
public class Utility {
	/**
	 * @param wordAndCount
	 * @return
	 */
	public List<Entry<String, Integer>> getSortedList(Map<String, Integer> wordAndCount) {
		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		};
		if (wordAndCount != null) {
			Set<Entry<String, Integer>> entries = wordAndCount.entrySet();
			List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
			Collections.sort(listOfEntries, valueComparator);
			return listOfEntries;
		}
		return null;
	}

	public String[] extractWordsFromALine(String line) {
		line = line.toLowerCase();
		String[] words = line.split(Configuration.WORD_SPLIT_REGEX);
		return words;
	}
}
