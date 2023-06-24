package task2;

public class MatrixMultiplication {

    public static int[][] multiplyMatrixParallel(int[][] A, int[][] B) {
        int matrixSize = A.length;
        int[][] C = new int[matrixSize][matrixSize];
        Thread[] threads = new Thread[matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            threads[row] = new MatrixMultiplicationThread(A, B, C, row, matrixSize);
            threads[row].start();
        }

        for (int row = 0; row < matrixSize; row++) {
            try {
                threads[row].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return C;
    }
}
