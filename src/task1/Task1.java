package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Pattern;

public class Task1 {
    private static void runSequentialAlgorithm(String[] words) {
        int totalCount = 0;
        int totalLength = 0;
        double totalSquaredLength = 0;

        for (String word : words) {
            totalCount++;
            totalLength += word.length();
            totalSquaredLength += Math.pow(word.length(), 2);
        }

        double averageLength = (double) totalLength / totalCount;
        double variance = totalSquaredLength / totalCount - Math.pow(averageLength, 2);

        System.out.println("Sequential algorithm:");
        System.out.println("Words: " + totalCount);
        System.out.println("Total length: " + totalLength);
        System.out.println("Average word length: " + averageLength);
        System.out.println("Dispersion: " + variance);
    }
    public static void main(String[] args) {
        String fileName=System.getProperty("user.dir").concat("\\src\\text.txt");
        System.out.println(fileName);
        String text;
        try {
            text = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
            return;
        }
        String[] words = Pattern.compile("\s+").split(text);
        long startTime = System.currentTimeMillis();
        runSequentialAlgorithm(words);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential algorithm time: " + (endTime - startTime) + " мс\n");

        ForkJoinPool pool = new ForkJoinPool();
        WordAnalyzer task = new WordAnalyzer(words, 0, words.length);
        startTime = System.currentTimeMillis();
        double[] result = pool.invoke(task);
        endTime = System.currentTimeMillis();

        int totalCount = (int) result[0];
        int totalLength = (int) result[1];
        double totalSquaredLength = result[2];
        double averageLength = totalLength / (double) totalCount;
        double variance = totalSquaredLength / totalCount - Math.pow(averageLength, 2);

        System.out.println("Parallel algorithm:");
        System.out.println("Words: " + totalCount);
        System.out.println("Total length: " + totalLength);
        System.out.println("Average word length: " + averageLength);
        System.out.println("Dispersion: " + variance);
        System.out.println("Parallel algorithm time: " + (endTime - startTime) + " мс");
    }
}
