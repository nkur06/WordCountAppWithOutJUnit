package com.ankur.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.ankur.job.Job;
import com.ankur.repo.Summary;

/**
 * @author nkur06
 *
 */
public class ParallelProcess {

	/**
	 * @param listOfSplittedFiles
	 */
	public void startParallelProcessing(List<String> listOfSplittedFiles) {
		ExecutorService executorService = Executors.newFixedThreadPool(listOfSplittedFiles.size());

		List<Callable<Map<String, Integer>>> tasks = new ArrayList<Callable<Map<String, Integer>>>();
		for (int i = 0; i < listOfSplittedFiles.size(); i++) {
			tasks.add(new Job(listOfSplittedFiles.get(i)));
		}

		for (Callable<Map<String, Integer>> task : tasks) {
			Future<Map<String, Integer>> future = executorService.submit(task);
			if (future != null) {
				try {
					Map<String, Integer> tempWordAndCount = (HashMap<String, Integer>) future.get();
					Summary.deepCopyHashMap(tempWordAndCount);
				} catch (InterruptedException | ExecutionException e) {
					System.out.println("Sorry, some  exception occured. Exception:" + e.getMessage());
					e.printStackTrace();
				}
			}

		}

		try {
			executorService.shutdown();
			executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			System.out.println("Sorry, some  exception occured. Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
