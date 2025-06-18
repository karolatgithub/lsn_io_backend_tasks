package lsn.io.backend;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Utils {

	
	protected static void processLine(final String line, final PrintStream printStream, final IAnalyzer analyzer) {
		try (final IntStream intStream = Arrays.stream(line.split(" ")).filter(Utils::isInteger)
				.mapToInt(Integer::parseInt)) {

			analyzer.analyzeStream(intStream, printStream);

		}
	}

	protected static boolean isInteger(final String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}