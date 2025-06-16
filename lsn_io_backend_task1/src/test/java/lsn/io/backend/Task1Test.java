package lsn.io.backend;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

	@Test
	@DisplayName("Test Task1")
	void testCalculateInputToOutputStreams() throws FileNotFoundException, IOException {
		testCalculateInputToOutputStreams("src/test/resources/inputs/inputTask1.txt",
				"src/test/resources/inputs/outputTask1.txt");
	}

	@Test
	@DisplayName("Long Test Task1")
	void longTestCalculateInputToOutputStreams() throws FileNotFoundException, IOException {
		testCalculateInputToOutputStreams("src/test/resources/inputs/longInputTask1.txt",
				"src/test/resources/inputs/longOutputTask1.txt");
	}

	private static void testCalculateInputToOutputStreams(final String inputFileName, final String outputFileName)
			throws FileNotFoundException, IOException {
		try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			try (final InputStream inputStream = new FileInputStream(new File(inputFileName))) {
				Task1.calculateInputToOutputStreams(inputStream, outputStream);
			}
			Assertions.assertArrayEquals(Files.readAllBytes(Paths.get(outputFileName)), outputStream.toByteArray());
		}
	}
}