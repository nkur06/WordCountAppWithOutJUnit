package com.ankur.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.ankur.config.Configuration;

public class FileCleaner {
	public static void cleanDisk() {
		Path filePath = Paths.get(Configuration.DIR);

		try {
			Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			System.out.println(
					"Sorry, some input output exception occured  at path:" + filePath + " Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
