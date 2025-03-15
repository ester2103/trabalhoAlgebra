package trabalhoAlgebra;

public class Matrix {
    int rows;
    int cols;
    int[][] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new int[rows][cols];
        
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < elements.length) {
                    this.elements[i][j] = elements[index];
                    index++;
                }
            }
        }
    }

    public int get(int i, int j) {
        if (i >= 0 && i < rows && j >= 0 && j < cols) {
            return elements[i][j];
        }
        
    }

    public void set(int i, int j, int value) {
        if (i >= 0 && i < rows && j >= 0 && j < cols) {
            elements[i][j] = value;
            return;
        }
       
    }
//para imprimir a matriz 
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println();
        }
    }