package lsn.io.backend;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task1 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString())) {
			try (final Scanner inputScaner = new Scanner(inputStream, StandardCharsets.UTF_8.toString())) {
				while (inputScaner.hasNextLine()) {
					try (final IntStream intStream = Arrays.stream(inputScaner.nextLine().split(" ")).filter(i -> {
						try {
							Integer.parseInt(i);
						} catch (Exception ex) {
							return false;
						}
						return true;
					}).mapToInt(Integer::parseInt)) {
						final int count[] = { 0 };
						try (IntStream distinctSortedIntStream = intStream.filter(i -> {
							return ++count[0] > 0;
						}).distinct().sorted()) {
							final int distinct[] = { 0 };
							final int min[] = { Integer.MAX_VALUE };
							final int max[] = { Integer.MIN_VALUE };
							distinctSortedIntStream.boxed().forEach(i -> {
								if (i < min[0]) {
									min[0] = i;
								}
								if (i > max[0]) {
									max[0] = i;
								}
								if (++distinct[0] > 1) {
									printStream.print(' ');
								}
								printStream.print(i);
							});
							if (count[0] > 0) {
								printStream.println();
								printStream.println("count: " + count[0]);
								printStream.println("distinct: " + distinct[0]);
								printStream.println("min: " + min[0]);
								printStream.println("max: " + max[0]);
							}
						}
					}
				}
			}
			printStream.flush();
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}
}
