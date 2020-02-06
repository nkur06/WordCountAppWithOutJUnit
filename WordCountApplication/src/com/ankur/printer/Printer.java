package com.ankur.printer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ankur.repo.Summary;
import com.ankur.util.Utility;

/**
 * @author nkur06
 *
 */
public class Printer {

	/**
	 * @param wordAndCount
	 */
	public void printSummary(Map<String, Integer> wordAndCount) {
		if (wordAndCount != null) {
			Utility utility = new Utility();
			List<Entry<String, Integer>> listOfEntries = utility.getSortedList(wordAndCount);
			if (listOfEntries.size() > 0) {
				System.out.println("--------------- Summary Report ----------------");

				System.out.println("Total number of words are:" + Summary.getTotNumOfWords());

				System.out.println("Maximum used word is below:");

				System.out.println(
						"Word:" + listOfEntries.get(0).getKey() + " -- Count:" + listOfEntries.get(0).getValue());
				System.out.println("-----------------------------------------------");
				System.out.println("List of Top 5 used words is below.");
				for (int i = 0; i < 5; i++) {
					Entry<String, Integer> entry = listOfEntries.get(i);
					System.out.println("Word:" + entry.getKey() + " -- Count:" + entry.getValue());
				}
			} else {
				System.out.println("Sorry, as total no of words are 0. There are not top 5 words available !!!");
			}
		}

	}

}
