package task1;

import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

class WordAnalyzer extends RecursiveTask<double[]> {
    private static final int THRESHOLD = 100;
    private static final Pattern WORD_PATTERN = Pattern.compile("\\s+");
    private final String[] words;
    private final int start;
    private final int end;

    public WordAnalyzer(String[] words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }

    @Override
    protected double[] compute() {
        if (end - start <= THRESHOLD) {
            return analyzeWords();
        } else {
            int middle = (start + end) / 2;
            WordAnalyzer leftTask = new WordAnalyzer(words, start, middle);
            WordAnalyzer rightTask = new WordAnalyzer(words, middle, end);
            invokeAll(leftTask, rightTask);

            double[] leftResult = leftTask.join();
            double[] rightResult = rightTask.join();

            int totalCount = (int) (leftResult[0] + rightResult[0]);
            int totalLength = (int) (leftResult[1] + rightResult[1]);
            double totalSquaredLength = leftResult[2] + rightResult[2];

            return new double[]{totalCount, totalLength, totalSquaredLength};
        }
    }

    private double[] analyzeWords() {
        int totalCount = 0;
        int totalLength = 0;
        double totalSquaredLength = 0;

        for (int i = start; i < end; i++) {
            String word = words[i];
            totalCount++;
            totalLength += word.length();
            totalSquaredLength += Math.pow(word.length(), 2);
        }

        return new double[]{totalCount, totalLength, totalSquaredLength};
    }
}
