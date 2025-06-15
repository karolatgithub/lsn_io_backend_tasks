package lsn.io.backend;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString())) {
			try (final Scanner inputScaner = new Scanner(inputStream, StandardCharsets.UTF_8.toString())) {
				while (inputScaner.hasNextLine()) {
					final Collection<int[]> results = new ArrayList<int[]>();
					try (final IntStream intStream = Arrays.stream(inputScaner.nextLine().split(" ")).filter(i -> {
						try {
							Integer.parseInt(i);
						} catch (Exception ex) {
							return false;
						}
						return true;
					}).mapToInt(Integer::parseInt)) {
						final List<Integer> list = intStream.mapToObj(i -> i).collect(Collectors.toList());
						int index = -1;
						while (++index < list.size()) {
							final Integer first = list.get(index);
							list.subList(index + 1, list.size()).stream().filter(i -> {
								return i + first == 13;
							}).forEach(i -> {
								if (first < i) {
									results.add(new int[] { first, i });
								} else {
									results.add(new int[] { i, first });
								}
							});
						}
					}
					results.stream().sorted((i, j) -> Integer.compare(i[0], j[0])).forEach(i -> {
						printStream.println(i[0] + " " + i[1]);
					});
				}
			}
			printStream.flush();
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}
}
