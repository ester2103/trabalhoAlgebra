public class Main {
    public static void main(String[] args) {
        // Cria uma instância da classe LinearAlgebra
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        // Exemplo com matrizes
        int[] matrixAElements = {1, 2, 3, 4, 5, 6};
        Matrix matrixA = new Matrix(2, 3, matrixAElements);

        int[] matrixBElements = {7, 8, 9, 10, 11, 12};
        Matrix matrixB = new Matrix(2, 3, matrixBElements);

        System.out.println("=== Matrizes ===");
        System.out.println("Matriz A:");
        matrixA.printMatrix();

        System.out.println("Matriz B:");
        matrixB.printMatrix();

        // Soma das matrizes
        Matrix sumMatrices = (Matrix) linearAlgebra.sum(matrixA, matrixB);
        System.out.println("Soma das matrizes (A + B):");
        sumMatrices.printMatrix();

        // Transposição da Matriz A
        Matrix transposedMatrixA = (Matrix) linearAlgebra.transpose(matrixA);
        System.out.println("Transposta da Matriz A:");
        transposedMatrixA.printMatrix();

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

        // Soma dos vetores
        Vector sumVectors = (Vector) linearAlgebra.sum(vectorA, vectorB);
        System.out.println("Soma dos vetores (A + B):");
        sumVectors.show();

        // Transposição do Vetor A
        Matrix transposedVectorA = (Matrix) linearAlgebra.transpose(vectorA);
        System.out.println("Transposta do Vetor A (transformado em matriz coluna):");
        transposedVectorA.printMatrix();
    }
}