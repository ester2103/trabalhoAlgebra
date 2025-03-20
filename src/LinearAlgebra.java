package src;

public class LinearAlgebra {

    public Object transpose(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if (object instanceof Matrix) {
            Matrix matrix = (Matrix) object;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            double[] transposedElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    transposedElements[j * rows + i] = matrix.get(i, j);
                }
            }

            return new Matrix(cols, rows, transposedElements);
        } else if (object instanceof Vector) {
            Vector vector = (Vector) object;
            double[] elements = new double[vector.size()];
            for (int i = 0; i < vector.size(); i++) {
                elements[i] = vector.get(i);
            }
            return new Matrix(elements.length, 1, elements);
        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    public Object sum(Object objectA, Object objectB) {
        if (objectA == null || objectB == null) {
            throw new IllegalArgumentException("Os objetos não podem ser nulos.");
        }

        if (objectA instanceof Matrix && objectB instanceof Matrix) {
            Matrix matrixA = (Matrix) objectA;
            Matrix matrixB = (Matrix) objectB;

            if (matrixA.getRows() != matrixB.getRows() || matrixA.getCols() != matrixB.getCols()) {
                throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões para soma.");
            }

            int rows = matrixA.getRows();
            int cols = matrixA.getCols();
            double[] sumElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sumElements[i * cols + j] = matrixA.get(i, j) + matrixB.get(i, j);
                }
            }

            return new Matrix(rows, cols, sumElements);
        } else if (objectA instanceof Vector && objectB instanceof Vector) {
            Vector vectorA = (Vector) objectA;
            Vector vectorB = (Vector) objectB;

            if (vectorA.size() != vectorB.size()) {
                throw new IllegalArgumentException("Os vetores devem ter o mesmo tamanho para soma.");
            }

            double[] sumElements = new double[vectorA.size()];
            for (int i = 0; i < vectorA.size(); i++) {
                sumElements[i] = vectorA.get(i) + vectorB.get(i);
            }

            return new Vector(sumElements);
        } else {
            throw new IllegalArgumentException("Tipos incompatíveis. Use Matrix ou Vector.");
        }
    }

    public Object times(int scalar, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if (object instanceof Matrix) {
            Matrix matrix = (Matrix) object;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            double[] resultElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultElements[i * cols + j] = scalar * matrix.get(i, j);
                }
            }
            return new Matrix(rows, cols, resultElements);

        } else if (object instanceof Vector) {
            Vector vector = (Vector) object;
            double[] resultElements = new double[vector.size()];

            for (int i = 0; i < vector.size(); i++) {
                resultElements[i] = scalar * vector.get(i);
            }
            return new Vector(resultElements);

        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    public Matrix dot(Matrix matrixA, Matrix matrixB) {
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("As matrizes não podem ser nulas.");
        }

        int rowsA = matrixA.getRows();
        int colsA = matrixA.getCols();
        int rowsB = matrixB.getRows();
        int colsB = matrixB.getCols();

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Número de colunas de A deve ser igual ao número de linhas de B.");
        }

        double[] resultElements = new double[rowsA * colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrixA.get(i, k) * matrixB.get(k, j);
                }
                resultElements[i * colsB + j] = sum;
            }
        }

        return new Matrix(rowsA, colsB, resultElements);
    }

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

        return new Matrix(rows, cols, elements);
    }
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
