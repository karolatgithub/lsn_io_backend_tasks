package lsn.io.backend;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task1 {

	public static void main(String[] args) {
		try (final Scanner input = new Scanner(System.in)) {
			while (input.hasNextLine()) {
				try (final IntStream intStream = Arrays.stream(input.nextLine().split(" ")).filter(item -> {
					try {
						Integer.parseInt(item);
					} catch (Exception ex) {
						return false;
					}
					return true;
				}).mapToInt(Integer::parseInt)) {
					final long count[] = { 0 };
					try (IntStream distinctSortedIntStream = intStream.filter(item -> {
						return ++count[0] > 0;
					}).distinct().sorted()) {
						final long distinct[] = { 0 };
						final long min[] = { Long.MAX_VALUE };
						final long max[] = { Long.MIN_VALUE };
						distinctSortedIntStream.boxed().forEach(item -> {
							if (++distinct[0] > 1) {
								System.out.print(' ');
							}
							if (item < min[0]) {
								min[0] = item;
							}
							if (item > max[0]) {
								max[0] = item;
							}
							System.out.print(item);
						});
						System.out.println("count: " + count[0]);
						System.out.println("distinct: " + distinct[0]);
						if (count[0] > 0) {
							System.out.println("min: " + min[0]);
							System.out.println("max: " + max[0]);
						}
					}
				}
			}
		}
	}
}
