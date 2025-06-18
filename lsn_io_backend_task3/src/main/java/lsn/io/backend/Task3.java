package lsn.io.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task3 {

	public static void main(String[] args) {
		calculateInputToOutputStreams(System.in, System.out);
	}

	protected static void calculateInputToOutputStreams(final InputStream inputStream,
			final OutputStream outputStream) {
		try (final PrintStream printStream = new PrintStream(outputStream, false, StandardCharsets.UTF_8.toString());
				final BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				final int size = processGraphInput(bufferedReader, Integer.parseInt(line));
				printStream.println(size);
			}

			printStream.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@SuppressWarnings("serial")
	private static int processGraphInput(final BufferedReader bufferedReader, int n) throws IOException {
		final List<Set<Integer>> graphs = new ArrayList<Set<Integer>>();
		while (--n >= 0) {
			final String[] pair = bufferedReader.readLine().split(" ");
			final Integer first = Integer.parseInt(pair[0]);
			final Integer second = Integer.parseInt(pair[1]);

			final Set<Integer> prev = graphs.stream().filter(set -> set.contains(first) || set.contains(second))
					.findFirst().orElse(null);

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
		return mergeGraphs(graphs);
	}

	private static int mergeGraphs(final List<Set<Integer>> graphs) {
		for (int i = graphs.size() - 1; i > 0; i--) {
			final Set<Integer> last = graphs.get(i);

			for (int j = i - 1; j >= 0; j--) {
				final Set<Integer> first = graphs.get(j);
				if (first.stream().anyMatch(last::contains)) {
					first.addAll(last);
					graphs.remove(i);
					break;
				}
			}
		}
		return graphs.size();
	}
}
