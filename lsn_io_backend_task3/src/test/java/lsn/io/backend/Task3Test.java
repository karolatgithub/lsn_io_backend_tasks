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

public class Task3Test {

	@Test
	@DisplayName("Test Task3")
	void testCalculateInputToOutputStreams() throws FileNotFoundException, IOException {
		try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			try (final InputStream inputStream = new FileInputStream(
					new File("src/test/resources/inputs/inputTask3.txt"))) {
				Task3.calculateInputToOutputStreams(inputStream, outputStream);
			}
			Assertions.assertArrayEquals(Files.readAllBytes(Paths.get("src/test/resources/inputs/outputTask3.txt")),
					outputStream.toByteArray());
		}
	}
}
