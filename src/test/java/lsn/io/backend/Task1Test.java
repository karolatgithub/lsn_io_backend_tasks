package lsn.io.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task1Test {

	@Test
	void testCalculateInputToOutputStreams() throws FileNotFoundException, IOException {
		try(final ByteArrayOutputStream outputStream=new ByteArrayOutputStream()) {
			try(final InputStream inputStream = new FileInputStream(new File("inputs/input1.txt"))) {
				Task1.calculateInputToOutputStreams(inputStream, outputStream);
			}
			Assertions.assertArrayEquals(Files.readAllBytes(Paths.get("inputs/output1.txt")),
				outputStream.toByteArray());
		}
	}
}