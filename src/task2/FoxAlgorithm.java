package task2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class FoxAlgorithm {

    public static int[][] multiplyMatrixUsingForkJoin(int[][] A, int[][] B, int blockSize) {
        int matrixSize = A.length;
        int[][] C = new int[matrixSize][matrixSize];

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        for (int row = 0; row < matrixSize / blockSize; row++) {
            for (int col = 0; col < matrixSize / blockSize; col++) {
                ForkJoinTask<?> task = new FoxAlgorithmTask(A, B, C, blockSize, row, col);
                forkJoinPool.execute(task);
            }
        }
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return C;
    }
}

