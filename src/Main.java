package src;
public class Main {

    // Metodo para multiplicar um escalar com uma matriz ou vetor
    public static Object times(int a, Object b) {
        if (b instanceof Matrix) {
            // Multiplicar cada elemento da matriz pelo escalar
            Matrix matrix = (Matrix) b;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            int[] resultElements = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultElements[i * cols + j] = (int)(a * matrix.get(i, j));
                }
            }

            return new Matrix(rows, cols, resultElements);

        } else if (b instanceof Vector) {
            // Multiplicar cada elemento do vetor pelo escalar
            Vector vector = (Vector) b;
            int[] resultElements = new int[vector.size()];

            for (int i = 0; i < vector.size(); i++) {
                resultElements[i] = a * vector.get(i);
            }

            return new Vector(resultElements);

        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado. Use Matrix ou Vector.");
        }
    }

    // Metodo para multiplicar duas matrizes (multiplicação de matrizes)
    public static Matrix dot(Matrix a, Matrix b) {
        // Verifica se as dimensões das matrizes são compatíveis para multiplicação
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("O número de colunas de A deve ser igual ao número de linhas de B.");
        }

        // Definindo as dimensões da matriz resultante
        int rows = a.getRows();
        int cols = b.getCols();

        // Matriz de resultado (multiplicação de matrizes)
        int[] resultElements = new int[rows * cols];

        // Realizando a multiplicação de matrizes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < a.getCols(); k++) {
                    sum += a.get(i, k) * b.get(k, j); // Produto escalar das linhas e colunas
                }
                resultElements[i * cols + j] = sum; // Coloca o valor do produto na posição correta da matriz resultante
            }
        }

        // Retorna a matriz resultante
        return new Matrix(rows, cols, resultElements);
    }

    public static void main(String[] args) {
        // Cria uma instância da classe LinearAlgebra
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        // Exemplo com matrizes
        int[] matrixAElements = {1, 2, 3, 4, 5, 6};
        Matrix matrixA = new Matrix(2, 3, matrixAElements); // Matriz 2x3

        int[] matrixBElements = {7, 8, 9, 10, 11, 12};
        Matrix matrixB = new Matrix(3, 2, matrixBElements); // Matriz 3x2 (agora compatível para multiplicação com A)

        System.out.println("=== Matrizes ===");
        System.out.println("Matriz A:");
        matrixA.printMatrix();

        System.out.println("Matriz B:");
        matrixB.printMatrix();

        // Soma das matrizes (já implementado em LinearAlgebra)
        // Lembre-se que a soma de matrizes requer que ambas tenham o mesmo tamanho
        // (nesse caso, elas não têm o mesmo tamanho, então não podemos somá-las)
        // Matrix sumMatrices = (Matrix) linearAlgebra.sum(matrixA, matrixB);
        // System.out.println("Soma das matrizes (A + B):");
        // sumMatrices.printMatrix();

        // Transposição da Matriz A
        Matrix transposedMatrixA = (Matrix) linearAlgebra.transpose(matrixA);
        System.out.println("Transposta da Matriz A:");
        transposedMatrixA.printMatrix();

        // Testando o metodo times com escalar e matriz
        int scalar = 2;
        System.out.println("Matriz A multiplicada por escalar " + scalar + ":");
        Matrix scaledMatrix = (Matrix) times(scalar, matrixA);
        scaledMatrix.printMatrix();

        // Testando o metodo dot (multiplicação de matrizes)
        System.out.println("Produto das Matrizes A e B (A * B):");
        Matrix productMatrix = dot(matrixA, matrixB);
        productMatrix.printMatrix();

        // Exemplo com vetores
        int[] vectorAElements = {1, 2, 3};
        Vector vectorA = new Vector(vectorAElements);

        int[] vectorBElements = {4, 5, 6};
        Vector vectorB = new Vector(vectorBElements);

        System.out.println("\n=== Vetores ===");
        System.out.println("Vetor A:");
        vectorA.show();

        System.out.println("Vetor B:");
        vectorB.show();

        // Soma dos vetores (já implementado em LinearAlgebra)
        Vector sumVectors = (Vector) linearAlgebra.sum(vectorA, vectorB);
        System.out.println("Soma dos vetores (A + B):");
        sumVectors.show();

        // Transposição do Vetor A
        Matrix transposedVectorA = (Matrix) linearAlgebra.transpose(vectorA);
        System.out.println("Transposta do Vetor A (transformado em matriz coluna):");
        transposedVectorA.printMatrix();

        // Testando o metodo times com vetor e escalar
        System.out.println("Vetor A multiplicado por escalar " + scalar + ":");
        Vector scaledVector = (Vector) times(scalar, vectorA);
        scaledVector.show();


        System.out.println("\n=== Teste de Eliminação Gaussiana ===");
        int[] gaussMatrixElements = {2, 1, -1, 8, -3, -1, 2, -11, -2, 1, 2, -3};
        Matrix gaussMatrix = new Matrix(3, 4, gaussMatrixElements);

        System.out.println("Matriz aumentada antes da eliminação gaussiana:");
        gaussMatrix.printMatrix();

        Matrix gaussResult = linearAlgebra.gauss(gaussMatrix);
        System.out.println("Matriz após eliminação gaussiana:");
        gaussResult.printMatrix();

        System.out.println("\n=== Teste de Resolução de Sistema Linear ===");
        Matrix solution = linearAlgebra.solve(gaussMatrix);
        System.out.println("Solução do sistema:");
        solution.printMatrix();
    }
}
