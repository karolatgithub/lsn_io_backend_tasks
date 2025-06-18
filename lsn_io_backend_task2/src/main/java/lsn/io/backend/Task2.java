package lsn.io.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.stream.IntStream;

public class Task2 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString());
				final BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			bufferedReader.lines().map(Task2::parseLineToIntegers).forEach(list -> {
				findSortedStreamPairsWithSum13(list).forEach(i -> printStream.println(i[0] + " " + i[1]));
			});

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static List<Integer> parseLineToIntegers(final String line) {
		return Arrays.stream(line.split(" ")).filter(Utils::isInteger).map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	private static Stream<Integer[]> findSortedStreamPairsWithSum13(final List<Integer> numbers) {
		final List<Integer[]> pairs = new ArrayList<Integer[]>();
		for (int i = 0; i < numbers.size(); i++) {
			final Integer first = numbers.get(i);
			for (int j = i + 1; j < numbers.size(); j++) {
				final Integer second = numbers.get(j);
				if (first + second == 13) {
					if (first < second) {
						pairs.add(new Integer[] { first, second });
					} else {
						pairs.add(new Integer[] { second, first });
					}
				}
			}
		}
		return pairs.stream().sorted((i, j) -> i[0].compareTo(j[0]));
	}
}
