package src;

public class LinearAlgebra {

    // Métodos de transposição, soma, multiplicação e multiplicação escalar
    // São iguais aos que você já postou, portanto não repetirei.

    /**
     * Método para realizar a eliminação gaussiana
     */
    public Matrix gauss(Matrix a) {
        int rows = a.getRows();
        int cols = a.getCols();
        double[][] elements = new double[rows][cols];

        // Copiando os elementos da matriz original
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = a.get(i, j);
            }
        }

        // Aplicação da eliminação gaussiana
        for (int i = 0; i < rows; i++) {
            // Pivô
            if (elements[i][i] == 0) {
                // Tentando encontrar uma linha com pivô não-nulo
                boolean swapped = false;
                for (int k = i + 1; k < rows; k++) {
                    if (elements[k][i] != 0) {
                        // Troca as linhas i e k
                        double[] temp = elements[i];
                        elements[i] = elements[k];
                        elements[k] = temp;
                        swapped = true;
                        break;
                    }
                }

                // Se não foi possível encontrar um pivô não-nulo, continua para a próxima coluna
                if (!swapped) {
                    continue; // Pivô é zero, então vai para a próxima coluna
                }
            }

            // Normalizando o pivô para 1
            double pivot = elements[i][i];
            for (int j = 0; j < cols; j++) {
                elements[i][j] /= pivot;
            }

            // Eliminação dos elementos abaixo do pivô
            for (int j = i + 1; j < rows; j++) {
                if (elements[j][i] != 0) {
                    double factor = elements[j][i];
                    for (int k = 0; k < cols; k++) {
                        elements[j][k] -= factor * elements[i][k];
                    }
                }
            }
        }

        // Convertendo a matriz reduzida para o formato Matrix
        int[] flatElements = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatElements[i * cols + j] = (int) elements[i][j]; // Convertendo de double para int
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
