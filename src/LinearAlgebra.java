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
            double[] transposedElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    transposedElements[j * rows + i] = matrix.getElement(i, j);
                }
            }

            return new Matrix(cols, rows, transposedElements);
        } else if (object instanceof Vector) {
            // Transpor um vetor (transformar em matriz coluna)
            Vector vector = (Vector) object;
            double[] elements = new double[vector.size()];
            for (int i = 0; i < vector.size(); i++) {
                elements[i] = vector.getElement(i);
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
            double[] sumElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sumElements[i * cols + j] = matrixA.getElement(i, j) + matrixB.getElement(i, j);
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

            double[] sumElements = new double[vectorA.size()];
            for (int i = 0; i < vectorA.size(); i++) {
                sumElements[i] = vectorA.getElement(i) + vectorB.getElement(i);
            }

            return new Vector(sumElements);
        } else {
            throw new IllegalArgumentException("Tipos incompatíveis. Use Matrix ou Vector.");
        }
    }

    /**
     * Multiplica um escalar com uma matriz ou vetor.
     *
     * @param scalar O escalar.
     * @param object A matriz ou vetor.
     * @return A matriz ou vetor resultante da multiplicação.
     * @throws IllegalArgumentException Se o objeto não for uma matriz ou vetor.
     */
    public Object times(double scalar, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if (object instanceof Matrix) {
            // Multiplicar uma matriz pelo escalar
            Matrix matrix = (Matrix) object;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            double[] resultElements = new double[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultElements[i * cols + j] = scalar * matrix.getElement(i, j);
                }
            }
            return new Matrix(rows, cols, resultElements);

        } else if (object instanceof Vector) {
            // Multiplicar um vetor pelo escalar
            Vector vector = (Vector) object;
            double[] resultElements = new double[vector.size()];

            for (int i = 0; i < vector.size(); i++) {
                resultElements[i] = scalar * vector.getElement(i);
            }
            return new Vector(resultElements);

        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    /**
     * Multiplica duas matrizes.
     *
     * @param matrixA A primeira matriz.
     * @param matrixB A segunda matriz.
     * @return O produto das matrizes.
     * @throws IllegalArgumentException Se as matrizes não puderem ser multiplicadas.
     */
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

        double[] resultElements = new double[rowsA * colsB];

        // Multiplicação de matrizes (produto matricial)
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrixA.getElement(i, k) * matrixB.getElement(k, j);
                }
                resultElements[i * colsB + j] = sum;
            }
        }

        return new Matrix(rowsA, colsB, resultElements);
    }

    /**
     * Realiza a eliminação gaussiana em uma matriz.
     *
     * @param a A matriz a ser escalonada.
     * @return A matriz escalonada.
     * @throws IllegalArgumentException Se a matriz for singular.
     */
    public Matrix gauss(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();
        double[][] elements = new double[rows][cols];

        // Copiando os elementos da matriz original
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = a.getElement(i, j);
            }
        }

        // Aplicação da eliminação gaussiana
        for (int i = 0; i < rows; i++) {
            // Pivô
            if (elements[i][i] == 0) {
                // Procurar uma linha abaixo para trocar
                boolean found = false;
                for (int k = i + 1; k < rows; k++) {
                    if (elements[k][i] != 0) {
                        double[] temp = elements[i];
                        elements[i] = elements[k];
                        elements[k] = temp;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new IllegalArgumentException("A matriz é singular (não invertível).");
                }
            }

            // Normalizar a linha do pivô
            double pivot = elements[i][i];
            for (int j = i; j < cols; j++) {
                elements[i][j] /= pivot;
            }

            // Eliminar os elementos abaixo do pivô
            for (int j = i + 1; j < rows; j++) {
                double factor = elements[j][i];
                for (int k = i; k < cols; k++) {
                    elements[j][k] -= factor * elements[i][k];
                }
            }
        }

        // Substituição retroativa para obter a forma escalonada reduzida
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double factor = elements[j][i];
                for (int k = i; k < cols; k++) {
                    elements[j][k] -= factor * elements[i][k];
                }
            }
        }

        // Convertendo a matriz reduzida para o formato Matrix
        double[] flatElements = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatElements[i * cols + j] = elements[i][j]; // Sem arredondamento
            }
        }

        return new Matrix(rows, cols, flatElements);
    }

    /**
     * Resolve um sistema de equações lineares por eliminação gaussiana.
     *
     * @param a A matriz aumentada do sistema.
     * @return A matriz com as soluções do sistema.
     * @throws IllegalArgumentException Se o sistema não tiver solução única.
     */
    public Matrix solve(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();

        Matrix gaussMatrix = gauss(a);
        double[][] elements = new double[rows][cols];

        // Copiando os elementos da matriz escalonada
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = gaussMatrix.getElement(i, j);
            }
        }

        double[] solutions = new double[rows];

        // Substituição retroativa
        for (int i = rows - 1; i >= 0; i--) {
            solutions[i] = elements[i][cols - 1]; // Valor da coluna de termos independentes
            for (int j = i + 1; j < cols - 1; j++) {
                solutions[i] -= elements[i][j] * solutions[j];
            }
            solutions[i] /= elements[i][i]; // Dividir pelo coeficiente da variável
        }

        // Convertendo as soluções para double (sem arredondamento)
        double[] doubleSolutions = new double[rows];
        for (int i = 0; i < rows; i++) {
            doubleSolutions[i] = solutions[i];
        }

        return new Matrix(rows, 1, doubleSolutions);
    }
}