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

public class Task2 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString())) {
			try (final Scanner inputScaner = new Scanner(inputStream, StandardCharsets.UTF_8.toString())) {
				while (inputScaner.hasNextLine()) {
					final List<Integer> list = Arrays.stream(inputScaner.nextLine().split(" ")).filter(i -> {
						try {
							Integer.parseInt(i);
						} catch (Exception ex) {
							return false;
						}
						return true;
					}).mapToInt(Integer::parseInt).collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);
					final Collection<Integer[]> results = new ArrayList<Integer[]>();
					int index = -1;
					while (++index < list.size()) {
						final Integer first = list.get(index);
						list.stream().skip(index + 1).filter(i -> (i + first == 13)).forEach(i -> {
							if (first < i) {
								results.add(new Integer[] { first, i });
							} else {
								results.add(new Integer[] { i, first });
							}
						});
					}
					results.stream().sorted((i, j) -> i[0].compareTo(j[0])).forEach(i -> {
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
