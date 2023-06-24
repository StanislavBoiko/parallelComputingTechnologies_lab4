package task2;

public class MatrixMultiplicationThread extends Thread {
    private final int[][] A, B, C;
    private final int row, matrixSize;

    public MatrixMultiplicationThread(int[][] A, int[][] B, int[][] C, int row, int matrixSize) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.row = row;
        this.matrixSize = matrixSize;
    }

    @Override
    public void run() {
        for (int col = 0; col < matrixSize; col++) {
            for (int k = 0; k < matrixSize; k++) {
                C[row][col] += A[row][k] * B[k][col];
            }
        }
    }
}

