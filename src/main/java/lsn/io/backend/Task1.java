package lsn.io.backend;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import java.lang.RuntimeException;

public class Task1 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString())) {
			try (final Scanner inputScaner = new Scanner(inputStream, StandardCharsets.UTF_8.toString())) {
				while (inputScaner.hasNextLine()) {
					try (final IntStream intStream = Arrays.stream(inputScaner.nextLine().split(" ")).filter(item -> {
						try {
							Integer.parseInt(item);
						} catch (Exception ex) {
							return false;
						}
						return true;
					}).mapToInt(Integer::parseInt)) {
						final int count[] = { 0 };
						try (IntStream distinctSortedIntStream = intStream.filter(item -> {
							return ++count[0] > 0;
						}).distinct().sorted()) {
							final int distinct[] = { 0 };
							final int min[] = { Integer.MAX_VALUE };
							final int max[] = { Integer.MIN_VALUE };
							distinctSortedIntStream.boxed().forEach(item -> {
								if (item < min[0]) {
									min[0] = item;
								}
								if (item > max[0]) {
									max[0] = item;
								}
								if (++distinct[0] > 1) {
									printStream.print(' ');
								}
								printStream.print(item);
							});
							if (count[0] > 0) {
								printStream.print("\n");
								printStream.print("count: " + count[0]+"\n");
								printStream.print("distinct: " + distinct[0]+"\n");
								printStream.print("min: " + min[0]+"\n");
								printStream.print("max: " + max[0]+"\n");
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
