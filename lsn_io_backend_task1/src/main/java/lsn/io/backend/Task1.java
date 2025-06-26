package lsn.io.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;
import java.lang.StringBuilder;

public class Task1 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString());
				final BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, StandardCharsets.UTF_8.toString()))) {

			bufferedReader.lines().forEach(line -> Utils.processLine(line, printStream, new IAnalyzer() {
				@Override
				public void analyzeStream(final IntStream intStream, final PrintStream printStream) {
					Task1.analyzeStream(intStream, printStream);
				}
			}));

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static void analyzeStream(final IntStream intStream, final PrintStream printStream) {
		final int[] count = { 0 };
		try (final IntStream distinctSortedIntStream = intStream.filter(i -> ++count[0] > 0).distinct().sorted()) {

			final int[] distinct = { 0 };
			final int[] min = { Integer.MAX_VALUE };
			final int[] max = { Integer.MIN_VALUE };

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
				printStream.println(new StringBuilder().append("count: ").append(count[0]).toString());
				printStream.println(new StringBuilder().append("distinct: ").append(distinct[0]).toString());
				printStream.println(new StringBuilder().append("min: ").append(min[0]).toString());
				printStream.println(new StringBuilder().append("max: ").append(max[0]).toString());
			}
		}
	}
}