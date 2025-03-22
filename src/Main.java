package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        // Matrizes e vetores pré-definidos (usando double)
        double[] matrixAElements = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        Matrix matrixA = new Matrix(2, 3, matrixAElements);

        double[] matrixBElements = {7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
        Matrix matrixB = new Matrix(3, 2, matrixBElements);

        double[] vectorAElements = {1.0, 2.0, 3.0};
        Vector vectorA = new Vector(vectorAElements);

        double[] vectorBElements = {4.0, 5.0, 6.0};
        Vector vectorB = new Vector(vectorBElements);

        double[] gaussMatrixElements = {2.0, 1.0, -1.0, 8.0, -3.0, -1.0, 2.0, -11.0, -2.0, 1.0, 2.0, -3.0};
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
                menuOperacoes(matrixA, matrixB, vectorA, vectorB, gaussMatrix, linearAlgebra, scanner);
            } else if (choice == 2) {
                // Inserir matrizes/vetores manualmente
                System.out.println("\n=== Inserir Matrizes/Vetores Manualmente ===");
                Matrix customMatrixA = criarMatriz(scanner);
                Matrix customMatrixB = criarMatriz(scanner);
                Vector customVectorA = criarVetor(scanner);
                Vector customVectorB = criarVetor(scanner);
                Matrix customGaussMatrix = criarMatriz(scanner);

                menuOperacoes(customMatrixA, customMatrixB, customVectorA, customVectorB, customGaussMatrix, linearAlgebra, scanner);
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
        double[] elements = new double[rows * cols];

        System.out.println("Digite os elementos da matriz (linha por linha):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Elemento a[%d][%d]: ", i, j); // Mostra o índice (i, j)
                elements[i * cols + j] = scanner.nextDouble(); // Armazena o valor na posição correta
            }
        }

        return new Matrix(rows, cols, elements);
    }

    // Método para criar um vetor manualmente
    private static Vector criarVetor(Scanner scanner) {
        System.out.print("Digite o tamanho do vetor: ");
        int size = scanner.nextInt();
        double[] elements = new double[size];

        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < size; i++) {
            System.out.printf("Elemento v[%d]: ", i); // Mostra o índice (i)
            elements[i] = scanner.nextDouble();
        }

        return new Vector(elements);
    }

    // Método para exibir o menu de operações
    private static void menuOperacoes(Matrix matrixA, Matrix matrixB, Vector vectorA, Vector vectorB, Matrix gaussMatrix, LinearAlgebra linearAlgebra, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu de Operações ===");
            System.out.println("1. Transpor Matriz/Vetor");
            System.out.println("2. Somar Matrizes/Vetores");
            System.out.println("3. Multiplicar por Escalar");
            System.out.println("4. Multiplicar Matrizes");
            System.out.println("5. Eliminação Gaussiana");
            System.out.println("6. Resolver Sistema Linear");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.println("8. Retornar valor de uma posição da Matriz/Vetor");
            System.out.println("9. Definir valor em uma posição da Matriz/Vetor");
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
                    double scalar = scanner.nextDouble();
                    System.out.println("1. Multiplicar Matriz A por Escalar");
                    System.out.println("2. Multiplicar Vetor A por Escalar");
                    System.out.print("Escolha uma opção: ");
                    int scalarChoice = scanner.nextInt();

                    if (scalarChoice == 1) {
                        Matrix scaledMatrix = (Matrix) linearAlgebra.times(scalar, matrixA);
                        System.out.println("Matriz A multiplicada por " + scalar + ":");
                        scaledMatrix.printMatrix();
                    } else if (scalarChoice == 2) {
                        Vector scaledVector = (Vector) linearAlgebra.times(scalar, vectorA);
                        System.out.println("Vetor A multiplicado por " + scalar + ":");
                        scaledVector.show();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 4:
                    System.out.println("\n=== Multiplicação de Matrizes ===");
                    try {
                        Matrix productMatrix = linearAlgebra.dot(matrixA, matrixB);
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

                case 8:
                    System.out.println("\n=== Retornar valor de uma posição ===");
                    System.out.println("1. Retornar valor de uma posição da Matriz A");
                    System.out.println("2. Retornar valor de uma posição da Matriz B");
                    System.out.println("3. Retornar valor de uma posição do Vetor A");
                    System.out.println("4. Retornar valor de uma posição do Vetor B");
                    System.out.print("Escolha uma opção: ");
                    int getChoice = scanner.nextInt();

                    switch (getChoice) {
                        case 1:
                            retornarValorMatriz(matrixA, scanner);
                            break;
                        case 2:
                            retornarValorMatriz(matrixB, scanner);
                            break;
                        case 3:
                            retornarValorVetor(vectorA, scanner);
                            break;
                        case 4:
                            retornarValorVetor(vectorB, scanner);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 9:
                    System.out.println("\n=== Definir valor em uma posição ===");
                    System.out.println("1. Definir valor em uma posição da Matriz A");
                    System.out.println("2. Definir valor em uma posição da Matriz B");
                    System.out.println("3. Definir valor em uma posição do Vetor A");
                    System.out.println("4. Definir valor em uma posição do Vetor B");
                    System.out.print("Escolha uma opção: ");
                    int setChoice = scanner.nextInt();

                    switch (setChoice) {
                        case 1:
                            definirValorMatriz(matrixA, scanner);
                            break;
                        case 2:
                            definirValorMatriz(matrixB, scanner);
                            break;
                        case 3:
                            definirValorVetor(vectorA, scanner);
                            break;
                        case 4:
                            definirValorVetor(vectorB, scanner);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para retornar o valor de uma posição específica da matriz
    private static void retornarValorMatriz(Matrix matriz, Scanner scanner) {
        System.out.print("Digite o índice da linha: ");
        int row = scanner.nextInt();
        System.out.print("Digite o índice da coluna: ");
        int col = scanner.nextInt();

        try {
            double valor = matriz.getElement(row, col);
            System.out.printf("Valor na posição [%d][%d]: %.3f\n", row, col, valor);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para retornar o valor de uma posição específica do vetor
    private static void retornarValorVetor(Vector vetor, Scanner scanner) {
        System.out.print("Digite o índice do vetor: ");
        int index = scanner.nextInt();

        try {
            double valor = vetor.getElement(index);
            System.out.printf("Valor na posição [%d]: %.3f\n", index, valor);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para definir um valor em uma posição específica da matriz
    private static void definirValorMatriz(Matrix matriz, Scanner scanner) {
        System.out.print("Digite o índice da linha: ");
        int row = scanner.nextInt();
        System.out.print("Digite o índice da coluna: ");
        int col = scanner.nextInt();
        System.out.print("Digite o valor a ser definido: ");
        double valor = scanner.nextDouble();

        try {
            matriz.setElement(row, col, valor);
            System.out.printf("Valor definido na posição [%d][%d]: %.3f\n", row, col, valor);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para definir um valor em uma posição específica do vetor
    private static void definirValorVetor(Vector vetor, Scanner scanner) {
        System.out.print("Digite o índice do vetor: ");
        int index = scanner.nextInt();
        System.out.print("Digite o valor a ser definido: ");
        double valor = scanner.nextDouble();

        try {
            vetor.setElement(index, valor);
            System.out.printf("Valor definido na posição [%d]: %.3f\n", index, valor);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}