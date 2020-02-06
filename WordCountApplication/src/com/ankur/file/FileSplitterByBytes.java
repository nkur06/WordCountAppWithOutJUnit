package com.ankur.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ankur.config.Configuration;

/**
 * @author nkur06
 *
 */

/*
 * This implementation can be used when the file size are too large, as it reads
 * by Channel in the form of bytes. It is very fast as it uses java.nio package,
 * compare to other implementations. But using this implementation we will
 * getting one case where a word can be broken in to two parts as it just check
 * the bytes. It does not bother about word, hald of the word can be taken in
 * one single file other half can be taken in other file. So files breaking by
 * the lines is good solution here.
 */

public class FileSplitterByBytes {

	/**
	 * Split a file into multiples files.
	 *
	 * @param fileName   Name of file to be split.
	 * @param mBperSplit maximum number of MB per file.
	 * @throws IOException
	 */

	public static List<Path> splitFile(final String fileName, final int mBperSplit) throws IOException {

		if (mBperSplit <= 0) {
			throw new IllegalArgumentException("mBperSplit must be more than zero");
		}

		List<Path> partFiles = new ArrayList<>();
		final long sourceSize = Files.size(Paths.get(fileName));
		final long bytesPerSplit = 1024L * 1024L * mBperSplit;
		final long numSplits = sourceSize / bytesPerSplit;
		final long remainingBytes = sourceSize % bytesPerSplit;
		int position = 0;

		try (RandomAccessFile sourceFile = new RandomAccessFile(fileName, "r");
				FileChannel sourceChannel = sourceFile.getChannel()) {

			for (; position < numSplits; position++) {

				writePartToFile(bytesPerSplit, position * bytesPerSplit, sourceChannel, partFiles);
			}

			if (remainingBytes > 0) {
				writePartToFile(remainingBytes, position * bytesPerSplit, sourceChannel, partFiles);
			}
		}
		return partFiles;
	}

	/**
	 * @param byteSize
	 * @param position
	 * @param sourceChannel
	 * @param partFiles
	 * @throws IOException
	 */
	private static void writePartToFile(long byteSize, long position, FileChannel sourceChannel, List<Path> partFiles)
			throws IOException {
		Path fileName = Paths.get(Configuration.DIR + UUID.randomUUID() + Configuration.SPLIT);
		try (RandomAccessFile toFile = new RandomAccessFile(fileName.toFile(), "rw");
				FileChannel toChannel = toFile.getChannel()) {
			sourceChannel.position(position);
			toChannel.transferFrom(sourceChannel, 0, byteSize);
		}
		partFiles.add(fileName);
	}
}
