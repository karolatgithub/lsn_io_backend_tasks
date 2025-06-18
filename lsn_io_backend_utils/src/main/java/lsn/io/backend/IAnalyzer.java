package lsn.io.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

public interface IAnalyzer {

	void analyzeStream(IntStream intStream, PrintStream printStream);

}