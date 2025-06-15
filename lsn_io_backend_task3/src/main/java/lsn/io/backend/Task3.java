package lsn.io.backend;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Task3 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	@SuppressWarnings("serial")
	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString())) {
			try (final Scanner inputScaner = new Scanner(inputStream, StandardCharsets.UTF_8.toString())) {
				while (inputScaner.hasNextLine()) {
					final List<Set<Integer>> graphs = new ArrayList<Set<Integer>>();
					int n = Integer.parseInt(inputScaner.nextLine());
					while (--n >= 0 && inputScaner.hasNextLine()) {
						final String[] pair = inputScaner.nextLine().split(" ");
						final Integer first = Integer.parseInt(pair[0]);
						final Integer second = Integer.parseInt(pair[1]);
						final Set<Integer> prev = graphs.stream()
								.filter(set -> set.contains(first) || set.contains(second)).findFirst().orElse(null);
						if (prev != null) {
							prev.add(first);
							prev.add(second);
						} else {
							graphs.add(new HashSet<Integer>() {
								{
									add(first);
									add(second);
								}
							});
						}
					}
					int index = graphs.size();
					while (--index >= 1) {
						final Set<Integer> last = graphs.get(index);
						int j = index;
						while (--j >= 0) {
							final Set<Integer> first = graphs.get(j);
							if (first.stream().filter(i -> last.contains(i)).findFirst().isPresent()) {
								first.addAll(last);
								graphs.remove(index);
							}
						}
					}
					printStream.println(graphs.size());
				}
			}
			printStream.flush();
		} catch (

		UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}
}
