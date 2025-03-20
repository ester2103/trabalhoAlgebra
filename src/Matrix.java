package src;

public class Matrix {
    private int rows;
    private int cols;
    private int[] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = elements;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int get(int i, int j) {
        return elements[i * cols + j];
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(elements[i * cols + j] + " ");
            }
            System.out.println();
        }
    }
}