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
            int[][] transposedElements = new int[cols][rows];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    transposedElements[j][i] = matrix.get(i, j);
                }
            }

            // Converte o array bidimensional em unidimensional
            int[] flattenedElements = new int[cols * rows];
            int index = 0;
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    flattenedElements[index] = transposedElements[i][j];
                    index++;
                }
            }

            return new Matrix(cols, rows, flattenedElements);
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
            int[][] sumElements = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sumElements[i][j] = matrixA.get(i, j) + matrixB.get(i, j);
                }
            }

            // Converte o array bidimensional em unidimensional
            int[] flattenedElements = new int[rows * cols];
            int index = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    flattenedElements[index] = sumElements[i][j];
                    index++;
                }
            }

            return new Matrix(rows, cols, flattenedElements);
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
}