package src;
public class Matrix {
    private int rows;
    private int cols;
    private double[][] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new double[rows][cols];

        for (int i = 0; i < elements.length; i++) {
            this.elements[i / cols][i % cols] = elements[i];
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double get(int i, int j) {
        return elements[i][j];
    }

    public void set(int i, int j, double value) {
        elements[i][j] = value;
    }

    public void swapRows(int row1, int row2) {
        double[] temp = elements[row1];
        elements[row1] = elements[row2];
        elements[row2] = temp;
    }

    public void normalizeRow(int row, double factor) {
        for (int j = 0; j < cols; j++) {
            elements[row][j] /= factor;
        }
    }

    public void subtractRows(int targetRow, int sourceRow, double factor) {
        for (int j = 0; j < cols; j++) {
            elements[targetRow][j] -= elements[sourceRow][j] * factor;
        }
    }

    public void printMatrix() {
        for (double[] row : elements) {
            for (double elem : row) {
                System.out.printf("%8.3f ", elem);
            }
            System.out.println();
        }
    }
}
