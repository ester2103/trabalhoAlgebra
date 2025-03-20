package src;

public class LinearAlgebra {

    public Matrix gauss(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();
        double[][] elements = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = a.get(i, j);
            }
        }

        for (int i = 0; i < rows; i++) {
            int maxRow = i;
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(elements[k][i]) > Math.abs(elements[maxRow][i])) {
                    maxRow = k;
                }
            }

            double[] temp = elements[i];
            elements[i] = elements[maxRow];
            elements[maxRow] = temp;

            if (elements[i][i] == 0) {
                throw new ArithmeticException("Matriz singular. Não é possível resolver.");
            }

            double pivot = elements[i][i];
            for (int j = 0; j < cols; j++) {
                elements[i][j] /= pivot;
            }

            for (int j = i + 1; j < rows; j++) {
                double factor = elements[j][i];
                for (int k = 0; k < cols; k++) {
                    elements[j][k] -= factor * elements[i][k];
                }
            }
        }

        double[] flatElements = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatElements[i * cols + j] = elements[i][j];
            }
        }

        return new Matrix(rows, cols, flatElements);
    }

    public Matrix solve(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();

        if (cols != rows + 1) {
            throw new IllegalArgumentException("A matriz deve ser aumentada (n x n+1) para resolver o sistema.");
        }

        Matrix gaussMatrix = gauss(a);
        double[][] elements = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = gaussMatrix.get(i, j);
            }
        }

        double[] solutions = new double[rows];

        for (int i = rows - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < cols - 1; j++) {
                sum += elements[i][j] * solutions[j];
            }
            solutions[i] = elements[i][cols - 1] - sum;
        }

        return new Matrix(rows, 1, solutions);
    }
}
