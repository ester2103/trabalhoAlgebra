package src;
public class LinearAlgebra {
    /**
     * Transpõe uma matriz ou vetor.
     *
     * @param object A matriz ou vetor a ser transposto.
     * @return A matriz ou vetor transposto.
     * @throws IllegalArgumentException Se o objeto não for uma matriz ou vetor.
     */
    public Object transpose(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if (object instanceof Matrix) {
            // Transpor uma matriz
            Matrix matrix = (Matrix) object;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            int[] transposedElements = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    transposedElements[j * rows + i] = (int) matrix.get(i, j);
                }
            }

            return new Matrix(cols, rows, transposedElements);
        } else if (object instanceof Vector) {
            // Transpor um vetor (transformar em matriz coluna)
            Vector vector = (Vector) object;
            int[] elements = new int[vector.size()];
            for (int i = 0; i < vector.size(); i++) {
                elements[i] = vector.get(i);
            }
            return new Matrix(elements.length, 1, elements);
        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    /**
     * Soma duas matrizes ou dois vetores.
     *
     * @param objectA A primeira matriz ou vetor.
     * @param objectB A segunda matriz ou vetor.
     * @return A matriz ou vetor resultante da soma.
     * @throws IllegalArgumentException Se os objetos não forem do mesmo tipo ou tiverem dimensões incompatíveis.
     */
    public Object sum(Object objectA, Object objectB) {
        if (objectA == null || objectB == null) {
            throw new IllegalArgumentException("Os objetos não podem ser nulos.");
        }

        if (objectA instanceof Matrix && objectB instanceof Matrix) {
            // Soma de matrizes
            Matrix matrixA = (Matrix) objectA;
            Matrix matrixB = (Matrix) objectB;

            if (matrixA.getRows() != matrixB.getRows() || matrixA.getCols() != matrixB.getCols()) {
                throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões para soma.");
            }

            int rows = matrixA.getRows();
            int cols = matrixA.getCols();
            int[] sumElements = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sumElements[i * cols + j] = (int) (matrixA.get(i, j) + matrixB.get(i, j));
                }
            }

            return new Matrix(rows, cols, sumElements);
        } else if (objectA instanceof Vector && objectB instanceof Vector) {
            // Soma de vetores
            Vector vectorA = (Vector) objectA;
            Vector vectorB = (Vector) objectB;

            if (vectorA.size() != vectorB.size()) {
                throw new IllegalArgumentException("Os vetores devem ter o mesmo tamanho para soma.");
            }

            int[] sumElements = new int[vectorA.size()];
            for (int i = 0; i < vectorA.size(); i++) {
                sumElements[i] = vectorA.get(i) + vectorB.get(i);
            }

            return new Vector(sumElements);
        } else {
            throw new IllegalArgumentException("Tipos incompatíveis. Use Matrix ou Vector.");
        }
    }
    // Metodo para multiplicar um escalar com uma matriz ou vetor
    public Object times(int scalar, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if (object instanceof Matrix) {
            // Multiplicar uma matriz pelo escalar
            Matrix matrix = (Matrix) object;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            int[] resultElements = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultElements[i * cols + j] = (int) (scalar * matrix.get(i, j));
                }
            }
            return new Matrix(rows, cols, resultElements);

        } else if (object instanceof Vector) {
            // Multiplicar um vetor pelo escalar
            Vector vector = (Vector) object;
            int[] resultElements = new int[vector.size()];

            for (int i = 0; i < vector.size(); i++) {
                resultElements[i] = scalar * vector.get(i);
            }
            return new Vector(resultElements);

        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    // Metodo para multiplicar duas matrizes
    public Matrix dot(Matrix matrixA, Matrix matrixB) {
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("As matrizes não podem ser nulas.");
        }

        int rowsA = matrixA.getRows();
        int colsA = matrixA.getCols();
        int rowsB = matrixB.getRows();
        int colsB = matrixB.getCols();

        // Verifica se a multiplicação de matrizes é possível
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Número de colunas de A deve ser igual ao número de linhas de B.");
        }

        int[] resultElements = new int[rowsA * colsB];

        // Multiplicação de matrizes (produto matricial)
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrixA.get(i, k) * matrixB.get(k, j);
                }
                resultElements[i * colsB + j] = sum;
            }
        }

        return new Matrix(rowsA, colsB, resultElements);
    }


    // Metodo para realizar a eliminação gaussiana
    public Matrix gauss(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();
        int[][] elements = new int[rows][cols];

        // Copiando os elementos da matriz original
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = (int) a.get(i, j);
            }
        }

        // Aplicação da eliminação gaussiana
        for (int i = 0; i < rows; i++) {
            // Pivô
            if (elements[i][i] == 0) {
                for (int k = i + 1; k < rows; k++) {
                    if (elements[k][i] != 0) {
                        int[] temp = elements[i];
                        elements[i] = elements[k];
                        elements[k] = temp;
                        break;
                    }
                }
            }

            for (int j = i + 1; j < rows; j++) {
                if (elements[j][i] != 0) {
                    int factor = elements[j][i] / elements[i][i];
                    for (int k = i; k < cols; k++) {
                        elements[j][k] -= factor * elements[i][k];
                    }
                }
            }
        }

        // Convertendo a matriz reduzida para o formato Matrix
        int[] flatElements = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatElements[i * cols + j] = elements[i][j];
            }
        }

        return new Matrix(rows, cols, flatElements);
    }

    // Método para resolver um sistema de equações lineares por eliminação gaussiana
    public Matrix solve(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();

        Matrix gaussMatrix = gauss(a);
        int[][] elements = new int[rows][cols];

        // Copiando os elementos da matriz escalonada
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = (int) gaussMatrix.get(i, j);
            }
        }

        int[] solutions = new int[rows];

        // Substituição retroativa
        for (int i = rows - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = i + 1; j < cols - 1; j++) {
                sum += elements[i][j] * solutions[j];
            }
            solutions[i] = (elements[i][cols - 1] - sum) / elements[i][i];
        }

        return new Matrix(rows, 1, solutions);
    }
}
