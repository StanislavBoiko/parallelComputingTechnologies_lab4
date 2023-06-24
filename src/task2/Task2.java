package task2;


import java.util.Random;

public class Task2 {

    public static void main(String[] args) {
        int matrixSize = 5000;
        int blockSize = 200;
        int[][] A = generateRandomMatrix(matrixSize);
        int[][] B = generateRandomMatrix(matrixSize);

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        int[][] C1 = MatrixMultiplication.multiplyMatrixParallel(A, B);
        endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        int[][] C2 = FoxAlgorithm.multiplyMatrixUsingForkJoin(A, B, blockSize);
        endTime = System.currentTimeMillis();
        long forkJoinTime = endTime - startTime;

        double acceleration = (double) parallelTime / forkJoinTime;
        System.out.println("Parallel time: " + parallelTime + " ms");
        System.out.println("ForkJoin time: " + forkJoinTime + " ms");
        System.out.println("Speedup: " + acceleration);
    }

    private static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }
}



