public class Matrix {
    private int rows;
    private int cols;
    private int[][] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new int[rows][cols];
        
        for (int i = 0; i < elements.length; i++) {
            this.elements[i / cols][i % cols] = elements[i];
        }
    }

    // Método para obter o número de linhas da matriz
    public int getRows() {
        return rows;
    }

    // Método para obter o número de colunas da matriz
    public int getCols() {
        return cols;
    }

    // Método para obter o valor em uma posição específica da matriz
    public int get(int i, int j) {
        return elements[i][j];
    }

    // Método para definir o valor em uma posição específica da matriz
    public void set(int i, int j, int value) {
        elements[i][j] = value;
    }

    // Método para imprimir a matriz
    public void printMatrix() {
        for (int[] row : elements) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}