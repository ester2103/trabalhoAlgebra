package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        // Matrizes e vetores pré-definidos
        int[] matrixAElements = {1, 2, 3, 4, 5, 6};
        Matrix matrixA = new Matrix(2, 3, matrixAElements); // Matriz 2x3

        int[] matrixBElements = {7, 8, 9, 10, 11, 12};
        Matrix matrixB = new Matrix(3, 2, matrixBElements); // Matriz 3x2

        int[] vectorAElements = {1, 2, 3};
        Vector vectorA = new Vector(vectorAElements);

        int[] vectorBElements = {4, 5, 6};
        Vector vectorB = new Vector(vectorBElements);

        int[] gaussMatrixElements = {2, 1, -1, 8, -3, -1, 2, -11, -2, 1, 2, -3};
        Matrix gaussMatrix = new Matrix(3, 4, gaussMatrixElements);

        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Usar matrizes/vetores pré-definidos");
            System.out.println("2. Inserir matrizes/vetores manualmente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Usar matrizes/vetores pré-definidos
                System.out.println("\n=== Usando Matrizes/Vetores Pré-definidos ===");
                printAll(matrixA, matrixB, vectorA, vectorB, gaussMatrix); // Imprime tudo de uma vez
                menuOperacoes(matrixA, matrixB, vectorA, vectorB, gaussMatrix, linearAlgebra);
            } else if (choice == 2) {
                // Inserir matrizes/vetores manualmente
                System.out.println("\n=== Inserir Matrizes/Vetores Manualmente ===");
                Matrix customMatrixA = criarMatriz(scanner);
                Matrix customMatrixB = criarMatriz(scanner);
                Vector customVectorA = criarVetor(scanner);
                Vector customVectorB = criarVetor(scanner);
                Matrix customGaussMatrix = criarMatriz(scanner);

                menuOperacoes(customMatrixA, customMatrixB, customVectorA, customVectorB, customGaussMatrix, linearAlgebra);
            } else if (choice == 3) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // Método para imprimir todas as matrizes e vetores pré-definidos
    private static void printAll(Matrix matrixA, Matrix matrixB, Vector vectorA, Vector vectorB, Matrix gaussMatrix) {
        System.out.println("\n=== Matrizes e Vetores Pré-definidos ===");
        System.out.println("Matriz A:");
        matrixA.printMatrix();

        System.out.println("\nMatriz B:");
        matrixB.printMatrix();

        System.out.println("\nVetor A:");
        vectorA.show();

        System.out.println("\nVetor B:");
        vectorB.show();

        System.out.println("\nMatriz para Eliminação Gaussiana:");
        gaussMatrix.printMatrix();
    }

    // Método para criar uma matriz manualmente
    private static Matrix criarMatriz(Scanner scanner) {
        System.out.print("Digite o número de linhas da matriz: ");
        int rows = scanner.nextInt();
        System.out.print("Digite o número de colunas da matriz: ");
        int cols = scanner.nextInt();
        int[] elements = new int[rows * cols];

        System.out.println("Digite os elementos da matriz (linha por linha):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Elemento a[%d][%d]: ", i, j); // Mostra o índice (i, j)
                elements[i * cols + j] = scanner.nextInt(); // Armazena o valor na posição correta
            }
        }

        return new Matrix(rows, cols, elements);
    }

    // Método para criar um vetor manualmente
    private static Vector criarVetor(Scanner scanner) {
        System.out.print("Digite o tamanho do vetor: ");
        int size = scanner.nextInt();
        int[] elements = new int[size];

        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < size; i++) {
            System.out.printf("Elemento v[%d]: ", i); // Mostra o índice (i)
            elements[i] = scanner.nextInt();
        }

        return new Vector(elements);
    }

    // Método para exibir o menu de operações
    private static void menuOperacoes(Matrix matrixA, Matrix matrixB, Vector vectorA, Vector vectorB, Matrix gaussMatrix, LinearAlgebra linearAlgebra) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu de Operações ===");
            System.out.println("1. Transpor Matriz/Vetor");
            System.out.println("2. Somar Matrizes/Vetores");
            System.out.println("3. Multiplicar por Escalar");
            System.out.println("4. Multiplicar Matrizes");
            System.out.println("5. Eliminação Gaussiana");
            System.out.println("6. Resolver Sistema Linear");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Transposição ===");
                    System.out.println("1. Transpor Matriz A");
                    System.out.println("2. Transpor Vetor A");
                    System.out.print("Escolha uma opção: ");
                    int transChoice = scanner.nextInt();

                    if (transChoice == 1) {
                        Matrix transposedMatrixA = (Matrix) linearAlgebra.transpose(matrixA);
                        System.out.println("Transposta da Matriz A:");
                        transposedMatrixA.printMatrix();
                    } else if (transChoice == 2) {
                        Matrix transposedVectorA = (Matrix) linearAlgebra.transpose(vectorA);
                        System.out.println("Transposta do Vetor A:");
                        transposedVectorA.printMatrix();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 2:
                    System.out.println("\n=== Soma ===");
                    System.out.println("1. Somar Matrizes A e B");
                    System.out.println("2. Somar Vetores A e B");
                    System.out.print("Escolha uma opção: ");
                    int sumChoice = scanner.nextInt();

                    if (sumChoice == 1) {
                        try {
                            Matrix sumMatrices = (Matrix) linearAlgebra.sum(matrixA, matrixB);
                            System.out.println("Soma das Matrizes A e B:");
                            sumMatrices.printMatrix();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    } else if (sumChoice == 2) {
                        try {
                            Vector sumVectors = (Vector) linearAlgebra.sum(vectorA, vectorB);
                            System.out.println("Soma dos Vetores A e B:");
                            sumVectors.show();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 3:
                    System.out.println("\n=== Multiplicação por Escalar ===");
                    System.out.print("Digite o escalar: ");
                    int scalar = scanner.nextInt();
                    System.out.println("1. Multiplicar Matriz A por Escalar");
                    System.out.println("2. Multiplicar Vetor A por Escalar");
                    System.out.print("Escolha uma opção: ");
                    int scalarChoice = scanner.nextInt();

                    if (scalarChoice == 1) {
                        Matrix scaledMatrix = (Matrix) times(scalar, matrixA);
                        System.out.println("Matriz A multiplicada por " + scalar + ":");
                        scaledMatrix.printMatrix();
                    } else if (scalarChoice == 2) {
                        Vector scaledVector = (Vector) times(scalar, vectorA);
                        System.out.println("Vetor A multiplicado por " + scalar + ":");
                        scaledVector.show();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 4:
                    System.out.println("\n=== Multiplicação de Matrizes ===");
                    try {
                        Matrix productMatrix = dot(matrixA, matrixB);
                        System.out.println("Produto das Matrizes A e B:");
                        productMatrix.printMatrix();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n=== Eliminação Gaussiana ===");
                    System.out.println("Matriz antes da eliminação gaussiana:");
                    gaussMatrix.printMatrix();
                    Matrix gaussResult = linearAlgebra.gauss(gaussMatrix);
                    System.out.println("Matriz após eliminação gaussiana:");
                    gaussResult.printMatrix();
                    break;

                case 6:
                    System.out.println("\n=== Resolução de Sistema Linear ===");
                    Matrix solution = linearAlgebra.solve(gaussMatrix);
                    System.out.println("Solução do sistema:");
                    solution.printMatrix();
                    break;

                case 7:
                    return; // Voltar ao menu principal

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para multiplicar um escalar com uma matriz ou vetor
    public static Object times(int a, Object b) {
        if (b instanceof Matrix) {
            Matrix matrix = (Matrix) b;
            int rows = matrix.getRows();
            int cols = matrix.getCols();
            int[] resultElements = new int[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultElements[i * cols + j] = (int) (a * matrix.get(i, j));
                }
            }

            return new Matrix(rows, cols, resultElements);
        } else if (b instanceof Vector) {
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

    // Método para multiplicar duas matrizes
    public static Matrix dot(Matrix a, Matrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("O número de colunas de A deve ser igual ao número de linhas de B.");
        }

        int rows = a.getRows();
        int cols = b.getCols();
        int[] resultElements = new int[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < a.getCols(); k++) {
                    sum += a.get(i, k) * b.get(k, j);
                }
                resultElements[i * cols + j] = sum;
            }
        }

        return new Matrix(rows, cols, resultElements);
    }
}