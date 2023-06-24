package task2;

import java.util.concurrent.RecursiveAction;

public class FoxAlgorithmTask extends RecursiveAction {
    private final int[][] A, B, C;
    private final int blockSize, row, col;

    public FoxAlgorithmTask(int[][] A, int[][] B, int[][] C, int blockSize, int row, int col) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.blockSize = blockSize;
        this.row = row;
        this.col = col;
    }

    @Override
    protected void compute() {
        for (int k = 0; k < blockSize; k++) {
            for (int i = 0; i < blockSize; i++) {
                for (int j = 0; j < blockSize; j++) {
                    C[row * blockSize + i][col * blockSize + j] += A[row * blockSize + i][k] * B[k][col * blockSize + j];
                }
            }
        }
    }
}

