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
				printStream.println(processGraphInput(bufferedReader, Integer.parseInt(line)));
			}

			printStream.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@SuppressWarnings("serial")
	private static int processGraphInput(final BufferedReader bufferedReader, int n) throws IOException {
		final List<Set<Integer>> graphs = new ArrayList<Set<Integer>>();
		boolean founded = false;
		String line;
		while (--n >= 0 && ((line = bufferedReader.readLine()) != null)) {
			final String[] pair = line.split(" ");
			final Integer first = Integer.parseInt(pair[0]);
			final Integer second = Integer.parseInt(pair[1]);
			final Set<Integer> prev = graphs.stream().filter(g -> g.contains(first) || g.contains(second)).findFirst()
					.orElse(null);
			if (prev != null) {
				prev.add(first);
				prev.add(second);
				founded = true;
			} else {
				graphs.add(new HashSet<Integer>() {
					{
						add(first);
						add(second);
					}
				});
			}
		}
		if (founded) {
			mergeGraphs(graphs);
		}
		return graphs.size();
	}

	private static void mergeGraphs(final List<Set<Integer>> graphs) {
		int index = graphs.size();
		while (--index > 0) {
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
	}
}
