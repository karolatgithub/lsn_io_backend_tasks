package lsn.io.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString());
				final BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			bufferedReader.lines().map(Task2::parseLineToIntegers).forEach(numbers -> {
				findSortedStreamOfPairsWithSum13(numbers, printStream);
			});

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static List<Integer> parseLineToIntegers(final String line) {
		return Stream.of(line.split(" ", -1)).filter(Utils::isInteger).map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	private static void findSortedStreamOfPairsWithSum13(final List<Integer> numbers, final PrintStream printStream) {
		final Collection<Integer[]> pairs = new ArrayList<Integer[]>();
		int index = -1;
		while (++index < numbers.size()) {
			final Integer first = numbers.get(index);
			numbers.stream().skip(index + 1).filter(i -> (i + first == 13)).forEach(i -> {
				if (first < i) {
					pairs.add(new Integer[] { first, i });
				} else {
					pairs.add(new Integer[] { i, first });
				}
			});
		}
		pairs.stream().sorted((i, j) -> i[0].compareTo(j[0])).forEach(i -> {
			printStream.println(new StringBuilder().append(i[0]).append(" ").append(i[1]).toString());
		});
	}
}
