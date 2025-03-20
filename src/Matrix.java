package src;

public class Matrix {
    private int rows;
    private int cols;
    private int[] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = elements;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Retorna o elemento na posição (i, j)
    public int getElement(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            throw new IllegalArgumentException("Índices fora dos limites da matriz.");
        }
        return elements[i * cols + j];
    }

    // Define o elemento na posição (i, j)
    public void setElement(int i, int j, int value) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            throw new IllegalArgumentException("Índices fora dos limites da matriz.");
        }
        elements[i * cols + j] = value;
    }

    // Imprime a matriz
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(elements[i * cols + j] + " ");
            }
            System.out.println();
        }
    }
}
