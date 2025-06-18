package lsn.io.backend;

import java.io.PrintStream;
import java.util.stream.IntStream;

public interface IAnalyzer {

	void analyzeStream(IntStream intStream, PrintStream outputStream);

}